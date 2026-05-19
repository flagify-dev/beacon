package com.flagify.beacon.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flagify.beacon.project.dto.*;
import com.flagify.beacon.project.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto createProject(@RequestBody @Valid CreateProjectRequest request) {
        return projectService.createProject(request.organizationSlug(), request.name(), request.description());
    }

    @GetMapping("/{organization_slug}/{slug}")
    public ProjectDto getProjectBySlug(@PathVariable String organization_slug, @PathVariable String slug) {
        return projectService.findBySlug(organization_slug, slug)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }
}
