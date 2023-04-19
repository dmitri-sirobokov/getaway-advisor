package io.getawayadvisor.service;

import io.getawayadvisor.model.Activity;
import io.getawayadvisor.model.Suggestion;
import io.getawayadvisor.model.Vote;
import io.getawayadvisor.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class GetawayService {
    private final SuggestionsRepository suggestionsRepository;
    protected final ActivitiesRepository activitiesRepository;
    private final VotesRepository votesRepository;

    public List<Suggestion> getSuggestions() {
        return StreamSupport.stream(suggestionsRepository.findAll().spliterator(), false)
                .map(this::mapSuggestionRecordToSuggestion)
                .toList();
    }

    public Suggestion saveSuggestion(Suggestion suggestion) {
        var record = this.mapSuggestionToSuggestionRecord(suggestion);
        var savedRecord = suggestionsRepository.save(record);
        return this.mapSuggestionRecordToSuggestion(savedRecord);
    }

    public void deleteSuggestion(UUID id) {
        this.suggestionsRepository.deleteById(id);
    }

    public Vote saveVote(Vote vote) {
        var record = this.mapVoteToVoteRecord(vote);
        var savedRecord = votesRepository.save(record);
        return this.mapVoteRecordToVote(savedRecord);
    }

    public List<Vote> getVotes() {
        return StreamSupport.stream(votesRepository.findAll().spliterator(), false)
                .map(this::mapVoteRecordToVote)
                .toList();
    }

    public List<Vote> getVotesBySuggestion(UUID suggestionId) {
        return StreamSupport.stream(votesRepository.findByIdSuggestionId(suggestionId).spliterator(), false)
                .map(this::mapVoteRecordToVote)
                .toList();
    }

    public Activity saveActivity(Activity activity) {
        var record = this.mapActivityToActivityRecord(activity);
        var savedRecord = this.activitiesRepository.save(record);
        return this.mapActivityRecordToActivity(savedRecord);
    }

    public List<Activity> getActivities() {
        return StreamSupport.stream(activitiesRepository.findAll().spliterator(), false)
                .map(this::mapActivityRecordToActivity)
                .toList();
    }

    public Activity getActivityById(UUID id) {
        var record = activitiesRepository.findById(id).orElse(null);
        if (record == null) {
            return null;
        }

        return mapActivityRecordToActivity(record);
    }

    private Suggestion mapSuggestionRecordToSuggestion(SuggestionRecord record) {
        var suggestion = new Suggestion();
        suggestion.setId(record.getId());
        suggestion.setName(record.getName());
        suggestion.setComments(record.getComments());
        suggestion.setIsIndoor(record.getIsIndoor());
        suggestion.setIsOutdoor(record.getIsOutdoor());
        return suggestion;
    }

    private SuggestionRecord mapSuggestionToSuggestionRecord(Suggestion suggestion) {
        var record = new SuggestionRecord();
        record.setId(suggestion.getId());
        record.setName(suggestion.getName());
        record.setComments(suggestion.getComments());
        record.setIsIndoor(suggestion.getIsIndoor());
        record.setIsOutdoor(suggestion.getIsOutdoor());
        return record;
    }

    private VoteRecord mapVoteToVoteRecord(Vote vote) {
        var record = new VoteRecord();
        var voteId = new VoteRecord.Id();
        voteId.setSuggestionId(vote.getSuggestionId());
        voteId.setEmail(vote.getEmail());
        record.setId(voteId);
        record.setIsYes(vote.getIsYes());
        record.setComments(vote.getComments());
        return record;
    }

    private Vote mapVoteRecordToVote(VoteRecord record) {
        var vote = new Vote();
        vote.setSuggestionId(record.getId().getSuggestionId());
        vote.setEmail(record.getId().getEmail());
        vote.setIsYes(record.getIsYes());
        vote.setComments(record.getComments());
        return vote;
    }

    private ActivityRecord mapActivityToActivityRecord(Activity activity) {
        var record = new ActivityRecord();
        record.setId(activity.getId());
        record.setName(activity.getName());
        record.setDate(activity.getDate());
        var suggestionRecords = this.suggestionsRepository.findAllById(activity.getSuggestions());
        record.setSuggestions(
                StreamSupport.stream(suggestionRecords.spliterator(), false)
                        .toList());
        return record;
    }
    private Activity mapActivityRecordToActivity(ActivityRecord record) {
        var activity = new Activity();
        activity.setId(record.getId());
        activity.setDate(record.getDate());
        activity.setName(record.getName());
        activity.setSuggestions(record.getSuggestions().stream().map(SuggestionRecord::getId).toList());
        return activity;
    }

}
