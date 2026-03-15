package com.flagify.beacon.organization.service;

import org.springframework.stereotype.Service;

import com.flagify.beacon.organization.repository.OrganizationRepository;
import com.flagify.beacon.util.UuidGenerator;
import com.flagify.beacon.organization.dto.OrganizationDto;
import com.flagify.beacon.organization.entity.OrganizationEntity;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UuidGenerator uuidGenerator;

    public OrganizationService(OrganizationRepository organizationRepository, UuidGenerator uuidGenerator) {
        this.organizationRepository = organizationRepository;
        this.uuidGenerator = uuidGenerator;
    }

    public OrganizationDto createOrganization(String name) {
        String baseSlug = generateSlug(name);
        int maxSuffix = organizationRepository.findMaxSuffixByBaseSlug(baseSlug).orElse(0);
        String slug = maxSuffix == 0? baseSlug: baseSlug + "-" + (maxSuffix + 1);

        if(organizationRepository.existsBySlug(slug)) {
            throw new RuntimeException("Slug already exists, please try again.");
        }

        OrganizationEntity organization = new OrganizationEntity();
        organization.setId(uuidGenerator.generateV7Uuid());
        organization.setName(name);
        organization.setSlug(slug);
        organization.setCreatedAt(java.time.LocalDateTime.now());
        organization.setUpdatedAt(java.time.LocalDateTime.now());

        OrganizationEntity savedOrganization = organizationRepository.save(organization);
        return toDto(savedOrganization);
    }

    public Optional<OrganizationDto> findById(String id) {
        return organizationRepository.findById(UUID.fromString(id)).map(this::toDto);
    }

    public Optional<OrganizationDto> findBySlug(String slug) {
        return organizationRepository.findBySlug(slug).map(this::toDto);
    }

    private String generateSlug(String name) {
        return name.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
    }

    private OrganizationDto toDto(OrganizationEntity entity) {
        return new OrganizationDto(entity.getId(), entity.getSlug(), entity.getName(), entity.getCreatedAt());
    }

}
