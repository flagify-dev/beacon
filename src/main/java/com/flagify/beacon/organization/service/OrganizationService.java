package com.flagify.beacon.organization.service;

import org.springframework.stereotype.Service;

import com.flagify.beacon.organization.repository.OrganizationRepository;
import com.flagify.beacon.shared.util.UuidGenerator;
import com.flagify.beacon.organization.dto.OrganizationDto;
import com.flagify.beacon.organization.entity.OrganizationEntity;
import com.flagify.beacon.shared.util.SlugUtil;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public OrganizationDto createOrganization(String name) {
        String baseSlug = SlugUtil.generateSlug(name);
        Integer maxIndex = organizationRepository.findMaxSlugIndexByBaseSlug(baseSlug).orElse(null);
        String slug = maxIndex == null ? baseSlug : baseSlug + "-" + (maxIndex + 1);

        if(organizationRepository.existsBySlug(slug)) {
            throw new RuntimeException("Slug already exists, please try again.");
        }

        OrganizationEntity organization = new OrganizationEntity();
        organization.setId(UuidGenerator.generateV7Uuid());
        organization.setName(name);
        organization.setSlug(slug);

        OrganizationEntity savedOrganization = organizationRepository.save(organization);
        return toDto(savedOrganization);
    }

    public Optional<OrganizationDto> findById(String id) {
        return organizationRepository.findById(UUID.fromString(id)).map(this::toDto);
    }

    public Optional<OrganizationDto> findBySlug(String slug) {
        return organizationRepository.findBySlug(slug).map(this::toDto);
    }

    private OrganizationDto toDto(OrganizationEntity entity) {
        return new OrganizationDto(entity.getSlug(), entity.getName(), entity.getCreatedAt());
    }

    public Optional<OrganizationEntity> findBySlugEntity(String slug) {
        return organizationRepository.findBySlug(slug);
    }

}
