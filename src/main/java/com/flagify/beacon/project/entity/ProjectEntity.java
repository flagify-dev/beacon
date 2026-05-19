package com.flagify.beacon.project.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.flagify.beacon.organization.entity.OrganizationEntity;
import com.flagify.beacon.shared.entity.BaseEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "projects", 
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_project_org_id_slug", columnNames = {"org_id", "slug"})
    }
)
public class ProjectEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false, foreignKey = @ForeignKey(name = "fk_project_org_id"))
    private OrganizationEntity organization;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "description")
    private String description;

    // Constructors
    public ProjectEntity() {}

    public ProjectEntity(UUID id, OrganizationEntity organization, String name, String slug, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.organization = organization;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    // Getters
    public OrganizationEntity getOrganization() {
        return organization;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + super.getId() +
                ", org_id=" + (organization != null ? organization.getId() : null) +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}'; 
    }
}
