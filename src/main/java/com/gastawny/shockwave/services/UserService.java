package com.gastawny.shockwave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.dto.user.UserDTO;
import com.gastawny.shockwave.models.ObjectFormat;
import com.gastawny.shockwave.models.User;
import com.gastawny.shockwave.repositories.PermissionRepository;
import com.gastawny.shockwave.repositories.UserRepository;
import com.gastawny.shockwave.shared.GenericList;
import com.gastawny.shockwave.shared.handlers.Handler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService, Handler<User> {

    private final UserRepository repository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PermissionRepository permissionRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Email " + email + " not found")
        );
    }

    @Override
    public String getType() {
        return "users";
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Object getService() {
        return new UserService(repository, permissionRepository, passwordEncoder);
    }

    @Override
    public List<User> findAll() {
        return repository.findAllWithoutAdmin();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User save(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User();
        UserDTO dto = mapper.convertValue(entity, UserDTO.class);

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        var permissions = permissionRepository.findAll();

        var roles = new java.util.ArrayList<>(permissions.stream()
                .filter(permission -> permission.getDescription().equals("COMMON_USER"))
                .toList());

        if(dto.getIsManager()) {
            roles.add(permissions.stream()
                    .filter(permission -> permission.getDescription().equals("MANAGER"))
                    .findFirst()
                    .orElse(null));
        }

        user.setPermissions(roles);

        return repository.save(user);
    }

    @Override
    public User update(Map<String, Object> entity) {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User();
        UserDTO dto = mapper.convertValue(entity, UserDTO.class);

        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        if(dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        var permissions = permissionRepository.findAll();

        var roles = new java.util.ArrayList<>(permissions.stream()
                .filter(permission -> permission.getDescription().equals("COMMON_USER"))
                .toList());

        if(dto.getIsManager()) {
            roles.add(permissions.stream()
                    .filter(permission -> permission.getDescription().equals("MANAGER"))
                    .findFirst()
                    .orElse(null));
        }

        user.setPermissions(roles);

        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<GenericList> find2Select() {
        return null;
    }

    public User findByEmail(LinkedHashMap<String, Object> req) {
        String email = req.get("email").toString();

        return repository.findByEmail(email).orElse(null);
    }
}