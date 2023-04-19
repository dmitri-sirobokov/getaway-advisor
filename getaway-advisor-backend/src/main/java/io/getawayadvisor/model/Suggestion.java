package io.getawayadvisor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Suggestion {
    private UUID id;
    private String name;
    private String comments;
    private Boolean isIndoor;
    private Boolean isOutdoor;
}
