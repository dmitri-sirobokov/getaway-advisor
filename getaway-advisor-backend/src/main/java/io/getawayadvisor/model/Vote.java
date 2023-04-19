package io.getawayadvisor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Vote {
    private UUID suggestionId;

    private String email;

    private Boolean isYes;

    private String comments;
}
