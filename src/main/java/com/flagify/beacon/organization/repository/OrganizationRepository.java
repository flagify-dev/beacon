package com.flagify.beacon.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.flagify.beacon.organization.entity.OrganizationEntity;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, UUID> {
    Optional<OrganizationEntity> findBySlug(String slug);
    boolean existsBySlug(String slug);
    @Query("""
        SELECT MAX(
            CASE
                WHEN o.slug = :baseSlug THEN 0
                ELSE CAST(SUBSTRING(o.slug, LENGTH(:baseSlug) + 2) AS int)
            END
        )
        FROM OrganizationEntity o
        WHERE o.slug = :baseSlug
        OR o.slug LIKE CONCAT(:baseSlug, '-%')
    """)
    Optional<Integer> findMaxSlugIndexByBaseSlug(@Param("baseSlug") String baseSlug);
}
