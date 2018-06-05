package com.truck.api.transit.repository;

import com.truck.api.transit.model.Transit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransitRepository extends PagingAndSortingRepository<Transit, Long> {
    List<Transit> findByDateBetween(LocalDate start, LocalDate till);
}
