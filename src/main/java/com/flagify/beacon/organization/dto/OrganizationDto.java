package com.flagify.beacon.organization.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrganizationDto(UUID id, String slug, String name, LocalDateTime createdAt) {}
