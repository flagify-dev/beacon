package com.flagify.beacon.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flagify.beacon.project.entity.ProjectEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID> {
    Optional<ProjectEntity> findByOrganization_IdAndSlug(UUID organizationId, String slug);
    boolean existsByOrganization_IdAndSlug(UUID organizationId, String slug);
    @Query("""
        SELECT MAX(
            CASE
                WHEN p.slug = :baseSlug THEN 0
                ELSE CAST(SUBSTRING(p.slug, LENGTH(:baseSlug) + 2) AS int)
            END
        )
        FROM ProjectEntity p
        WHERE p.organization.id = :organizationId
        AND (p.slug = :baseSlug OR p.slug LIKE CONCAT(:baseSlug, '-%'))
    """)
    Optional<Integer> findMaxSlugIndexByOrganization_IdAndBaseSlug(
        @Param("organizationId") UUID organizationId,
        @Param("baseSlug") String baseSlug
    );
}
