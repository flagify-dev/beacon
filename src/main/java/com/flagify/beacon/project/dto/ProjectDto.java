package com.flagify.beacon.project.dto;

import java.time.LocalDateTime;

public record ProjectDto(String slug, String name, LocalDateTime createdAt){}
