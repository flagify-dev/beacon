package com.flagify.beacon.organization.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "organizations")
public class OrganizationEntity {
    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="slug", nullable = false, unique = true)
    private String slug;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public OrganizationEntity() {}

    public OrganizationEntity(UUID id, String slug, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters
    public UUID getId() {
        return id;
    }
    
    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }   

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "OrganizationEntity{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationEntity that = (OrganizationEntity) o;
        return id != null? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null? id.hashCode() : 0;
    }
}
