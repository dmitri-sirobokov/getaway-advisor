package io.getawayadvisor.api;

import io.getawayadvisor.model.Activity;
import io.getawayadvisor.model.Suggestion;
import io.getawayadvisor.model.Vote;
import io.getawayadvisor.service.GetawayService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@AllArgsConstructor
public class GetawayController {
    private final GetawayService getawayService;

    @GetMapping(path = "/suggestions")
    public ResponseEntity<List<Suggestion>> getSuggestions() {
        return new ResponseEntity<>(this.getawayService.getSuggestions(), HttpStatus.OK);
    }

    @PutMapping(path = "/suggestions/{id}")
    public ResponseEntity<Suggestion> saveSuggestion(@PathVariable("id") UUID id, @RequestBody Suggestion suggestion) {
        suggestion.setId(id);
        return new ResponseEntity<>(this.getawayService.saveSuggestion(suggestion), HttpStatus.OK);
    }

    @DeleteMapping(path = "/suggestions/{id}")
    public ResponseEntity<Void> deleteSuggestion(@PathVariable("id") UUID id) {
        this.getawayService.deleteSuggestion(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Create new or update existing vote for suggestion.
     * @param vote Object that contains vote properties.
     * @return Return newly created or updated vote.
     */
    @PostMapping("/votes")
    public ResponseEntity<Vote> saveVote(@RequestBody Vote vote) {
        return new ResponseEntity<>(this.getawayService.saveVote(vote), HttpStatus.OK);
    }

    @GetMapping("/votes")
    public ResponseEntity<List<Vote>> getVotes() {
        return new ResponseEntity<>(this.getawayService.getVotes(), HttpStatus.OK);
    }

    @GetMapping("/suggestions/{suggestionId}/votes")
    public ResponseEntity<List<Vote>> getVotesBySuggestion(@PathVariable UUID suggestionId) {
        return new ResponseEntity<>(this.getawayService.getVotesBySuggestion(suggestionId), HttpStatus.OK);
    }

    @PutMapping("/activities/{activityId}")
    public ResponseEntity<Activity> saveActivity(@PathVariable UUID activityId, @RequestBody Activity activity) {
        activity.setId(activityId);
        return new ResponseEntity<>(this.getawayService.saveActivity(activity), HttpStatus.OK);
    }

    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getActivities() {
        return new ResponseEntity<>(this.getawayService.getActivities(), HttpStatus.OK);
    }

    @GetMapping("/activities/{activityId}")
    public ResponseEntity<Activity> getActivityById(@PathVariable UUID activityId) {
        return new ResponseEntity<>(this.getawayService.getActivityById(activityId), HttpStatus.OK);
    }

}
