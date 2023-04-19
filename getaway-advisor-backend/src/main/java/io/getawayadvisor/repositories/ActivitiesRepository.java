package io.getawayadvisor.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActivitiesRepository extends CrudRepository<ActivityRecord, UUID> {
}
