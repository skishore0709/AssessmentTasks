package com.kishore.readingDataJSONFormat.repository;

import com.kishore.readingDataJSONFormat.models.Scheme;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Long> {
    <T>Slice<T> findAllSchemesByCode(byte status, long schemeId, Class<T> projection);

    <T>Slice<T> findSchemeByCodeId(byte status, long schemeId, Class<T> projection);
}
