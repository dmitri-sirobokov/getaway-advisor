package io.getawayadvisor.repositories;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "votes")
@Getter @Setter
public class VoteRecord {
    @EmbeddedId
    private Id id;

    @Column(name = "yes_no")
    private Boolean isYes;

    @Column
    private String comments;

    @Embeddable
    @Getter @Setter
    public static class Id implements Serializable {
        @Column(name = "suggestion_id")
        private UUID suggestionId;

        @Column
        private String email;
    }
}
