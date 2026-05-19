package com.flagify.beacon.project.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectRequest (@NotBlank String organizationSlug, @NotBlank String name, String description) {}
