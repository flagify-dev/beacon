package com.flagify.beacon.organization.dto;

import java.time.LocalDateTime;

public record OrganizationDto(String slug, String name, LocalDateTime createdAt) {}
