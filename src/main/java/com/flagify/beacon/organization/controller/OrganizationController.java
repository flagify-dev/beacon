package com.flagify.beacon.organization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

import com.flagify.beacon.organization.dto.CreateOrganizationRequest;
import com.flagify.beacon.organization.dto.OrganizationDto;
import com.flagify.beacon.organization.service.OrganizationService;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationDto createOrganization(@RequestBody CreateOrganizationRequest request) {
        return organizationService.createOrganization(request.name());
    }

    @GetMapping("/{slug}")
    public OrganizationDto getOrganizationBySlug(@PathVariable String slug) {
        return organizationService.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }
}
