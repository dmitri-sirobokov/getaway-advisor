package io.getawayadvisor.repositories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "suggestions")
@Getter @Setter
public class SuggestionRecord {
    @Id
    private UUID id;

    @Column
    private String name;

    @Column(name = "indoor")
    private Boolean isIndoor;

    @Column(name = "outdoor")
    private Boolean isOutdoor;

    /**
     * Rate how active this activity is, for example: 0 - chill, 5 - very active
     */
    @Column(name = "active_rate")
    private byte activeRate;

    /**
     * Location, for example: Rotterdam, Amsterdam, Utrecht, etc.
     */
    private String location;

    @Column(precision = 6, scale = 2)
    private BigDecimal price;

    @Column
    private String comments;
}
