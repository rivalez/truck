package com.truck.api.transit.repository;

import com.truck.api.transit.model.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransitRepository extends JpaRepository<Transit, Long> {
    List<Transit> findByDateBetween(LocalDate start, LocalDate till);
}
