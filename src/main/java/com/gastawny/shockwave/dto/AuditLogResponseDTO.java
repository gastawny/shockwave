package com.gastawny.shockwave.dto;

import com.gastawny.shockwave.shared.enums.AuditAction;

import java.time.LocalDateTime;

public record AuditLogResponseDTO(
        Long id,
        AuditAction action,
        String entityType,
        String entityValue,
        Long entityId,
        Long performedById,
        String performedByName,
        LocalDateTime createdAt
) {
}
