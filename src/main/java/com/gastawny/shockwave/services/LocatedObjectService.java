package com.gastawny.shockwave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gastawny.shockwave.shared.handlers.Handler;
import com.gastawny.shockwave.models.*;
import com.gastawny.shockwave.repositories.*;
import com.gastawny.shockwave.shared.GenericList;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocatedObjectService implements Handler<LocatedObject> {

    private final ExplosiveRepository explosiveRepository;
    private final GroundRepository groundRepository;
    private final LocatedObjectRepository  locatedObjectRepository;
    private final ObjectFormatParameterValueRepository objectFormatParameterValueRepository;
    private final ObjectFormatParameterRepository objectFormatParameterRepository;

    public LocatedObjectService(
            LocatedObjectRepository locatedObjectRepository,
            ExplosiveRepository explosiveRepository,
            GroundRepository groundRepository,
            ObjectFormatParameterValueRepository objectFormatParameterValueRepository,
            ObjectFormatParameterRepository objectFormatParameterRepository
    ) {
        this.locatedObjectRepository = locatedObjectRepository;
        this.explosiveRepository = explosiveRepository;
        this.groundRepository = groundRepository;
        this.objectFormatParameterValueRepository = objectFormatParameterValueRepository;
        this.objectFormatParameterRepository = objectFormatParameterRepository;
    }

    @Override
    public String getType() {
        return "locatedObjects";
    }

    @Override
    public Class<LocatedObject> getEntityClass() {
        return LocatedObject.class;
    }

    @Override
    public Object getService() {
        return new LocatedObjectService(locatedObjectRepository, explosiveRepository, groundRepository, objectFormatParameterValueRepository, objectFormatParameterRepository);
    }

    @Override
    public List<LocatedObject> findAll() {
        return locatedObjectRepository.findAll();
    }

    @Override
    public LocatedObject findById(Long id) {
        return locatedObjectRepository.findById(id).orElse(null);
    }

    @Override
    public LocatedObject save(Map<String, Object> entity) {
        // Garantir que cada "value" tenha o tipo necessário para desserialização polimórfica
        ensureValueTypes(entity);

        ObjectMapper mapper = new ObjectMapper();
        LocatedObject locatedObject = mapper.convertValue(entity, LocatedObject.class);

        locatedObject.getObjectFormatParameterValues()
                .stream()
                .toList()
                .forEach(locatedObject::addObjectFormatParameterValue);

        return locatedObjectRepository.save(locatedObject);
    }


    @Override
    public LocatedObject update(Map<String, Object> entity) {
        // Garantir que cada "value" tenha o tipo necessário para desserialização polimórfica
        ensureValueTypes(entity);

        ObjectMapper mapper = new ObjectMapper();
        LocatedObject lo = mapper.convertValue(entity, LocatedObject.class);
        lo.getObjectFormatParameterValues()
                .stream()
                .toList()
                .forEach(lo::addObjectFormatParameterValue);

        return locatedObjectRepository.save(lo);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<GenericList> find2Select() {
        return locatedObjectRepository.findBy(GenericList.class);
    }

    public Map<String, String> getValues(Long locatedObjectId) {
        var locatedObject = findById(locatedObjectId);
        Map<String, String> values = new HashMap<>(Map.of());

        values.put("tab_k", locatedObject.getGround().getId().toString());
        values.put("dep_volume", locatedObject.getObjectFormat().getId().toString());
        values.put("densidade", explosiveRepository.findValueByParameterSymbol("densidade", locatedObject.getExplosive().getId()).get("value").toString());
        values.put("efeito_relativo_tnt", explosiveRepository.findValueByParameterSymbol("efeito_relativo_tnt", locatedObject.getExplosive().getId()).get("value").toString());

        for (var paramValue : locatedObject.getObjectFormatParameterValues()) {
            values.put(paramValue.getObjectFormatParameter().getParameter().getSymbol(), paramValue.getValue().getValue().toString());
        }
        return values;
    }

    /**
     * Normaliza cada entry de objectFormatParameterValues adicionando o campo "type"
     * quando ausente e garantindo que o valor numérico esteja em "val" (esperado por ValueNum).
     */
    @SuppressWarnings("unchecked")
    private void ensureValueTypes(Map<String, Object> entity) {
        if (entity == null) return;

        Object rawList = entity.get("objectFormatParameterValues");
        if (!(rawList instanceof List)) return;

        List<Object> list = (List<Object>) rawList;
        for (Object item : list) {
            if (!(item instanceof Map)) continue;
            Map<String, Object> itemMap = (Map<String, Object>) item;
            Object valueObj = itemMap.get("value");

            if (valueObj == null) {
                // também checar se já existe um objeto "val"/"raw" diretamente
                Object existingVal = itemMap.get("val");
                if (existingVal == null) existingVal = itemMap.get("raw");
                if (existingVal != null && !itemMap.containsKey("type")) {
                    itemMap.put("type", "number");
                }
                continue;
            }

            if (valueObj instanceof Map) {
                Map<String, Object> valueMap = (Map<String, Object>) valueObj;

                // se existir uma chave "value" aninhada, extrair o número dela
                if (valueMap.containsKey("value")) {
                    Object nested = valueMap.get("value");
                    Object extracted = nested;
                    if (nested instanceof Map) {
                        Map<String, Object> nestedMap = (Map<String, Object>) nested;
                        // caso { "value": { "value": 3 } } ou similar
                        if (nestedMap.containsKey("value")) {
                            extracted = nestedMap.get("value");
                        }
                    }
                    // mover/normalizar para "val" se não existir
                    if (!valueMap.containsKey("val")) {
                        valueMap.put("val", extracted);
                    }
                    valueMap.remove("value");
                }

                // se por algum motivo o mapa ainda tem chave "value" em outro formato, tratar acima
                if (!valueMap.containsKey("type")) {
                    valueMap.put("type", "number");
                }

                // Caso o itemMap tenha apenas "value" (map) e queremos que itemMap.value seja o objeto normalizado,
                // já está normalizado in-place (valueMap foi alterado).
            } else {
                // se for um número/valor primitivo (ex.: 3), converte para map com type + val
                Map<String, Object> newValueMap = new HashMap<>();
                newValueMap.put("type", "number");
                newValueMap.put("val", valueObj);
                itemMap.put("value", newValueMap);
            }
        }
    }
}
