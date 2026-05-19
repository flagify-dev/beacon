package com.flagify.beacon.project.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.flagify.beacon.organization.entity.OrganizationEntity;
import com.flagify.beacon.organization.service.OrganizationService;
import com.flagify.beacon.project.dto.ProjectDto;
import com.flagify.beacon.project.entity.ProjectEntity;
import com.flagify.beacon.project.repository.ProjectRepository;
import com.flagify.beacon.shared.util.UuidGenerator;
import com.flagify.beacon.shared.util.SlugUtil;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final OrganizationService organizationService;

    public ProjectService(ProjectRepository projectRepository, OrganizationService organizationService) {
        this.projectRepository = projectRepository;
        this.organizationService = organizationService;
    }

    public ProjectDto createProject(String organizationSlug, String name, String description) {
        OrganizationEntity organization = organizationService.findBySlugEntity(organizationSlug)
            .orElseThrow(() -> new RuntimeException("Organization not found"));
        // Continue with project creation logic
        String baseSlug = SlugUtil.generateSlug(name);
        Integer maxIndex = projectRepository.findMaxSlugIndexByOrganization_IdAndBaseSlug(organization.getId(), baseSlug).orElse(null);
        String slug = maxIndex == null ? baseSlug : baseSlug + "-" + (maxIndex + 1);

        if (projectRepository.existsByOrganization_IdAndSlug(organization.getId(), slug)) {
            throw new RuntimeException("Slug already exists, please try again.");
        }

        ProjectEntity project = new ProjectEntity();
        project.setId(UuidGenerator.generateV7Uuid());
        project.setSlug(slug);
        project.setOrganization(organization);
        project.setName(name);
        project.setDescription(description);
        // save project to db
        ProjectEntity savedProject = projectRepository.save(project);
        return toDto(savedProject);
    }

    public Optional<ProjectDto> findById(String id) {
        return projectRepository.findById(UUID.fromString(id)).map(this::toDto);
    }

    public Optional<ProjectDto> findBySlug(String organizationSlug, String slug) {
        OrganizationEntity organization = organizationService.findBySlugEntity(organizationSlug)
            .orElseThrow(() -> new RuntimeException("Organization not found"));
        return projectRepository.findByOrganization_IdAndSlug(organization.getId(), slug).map(this::toDto);
    }

    private ProjectDto toDto(ProjectEntity entity) {
        return new ProjectDto(entity.getSlug(), entity.getName(), entity.getCreatedAt());
    }
}
