package com.flagify.beacon.organization.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.flagify.beacon.organization.dto.CreateOrganizationRequest;
import com.flagify.beacon.organization.dto.OrganizationDto;
import com.flagify.beacon.organization.service.OrganizationService;

@RestController
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
}
