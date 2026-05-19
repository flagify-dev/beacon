package com.flagify.beacon.organization.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

import com.flagify.beacon.shared.entity.BaseEntity;

@Entity
@Table(name = "organizations")
public class OrganizationEntity extends BaseEntity {

    @Column(name="slug", nullable = false, unique = true)
    private String slug;

    @Column(name="name", nullable = false)
    private String name;

    public OrganizationEntity() {super();}

    public OrganizationEntity(UUID id, String slug, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.slug = slug;
        this.name = name;
    }

    // Getters
    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }   

    // Setters
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrganizationEntity{" +
                "id=" + super.getId() +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}
