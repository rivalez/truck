package com.truck.api.transit.repository;

import com.truck.api.transit.model.Transit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitRepository extends CrudRepository<Transit, Long> {
}
