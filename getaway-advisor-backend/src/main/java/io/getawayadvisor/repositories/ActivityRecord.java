package io.getawayadvisor.repositories;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "activities")
@Setter @Getter
public class ActivityRecord {
    @Id
    private UUID id;

    @Column
    private LocalDate date;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "activities_suggestions",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "suggestion_id"))
    private List<SuggestionRecord> suggestions;
}
