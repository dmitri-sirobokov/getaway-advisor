package io.getawayadvisor.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotesRepository extends CrudRepository<VoteRecord, VoteRecord.Id> {
    Iterable<VoteRecord> findByIdSuggestionId(UUID suggestionId);
}
