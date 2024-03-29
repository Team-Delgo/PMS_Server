package com.pms.repository;

import com.pms.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Integer>, JpaSpecificationExecutor<Place> {
    Optional<Place> findByPlaceId(int placeId);
}
