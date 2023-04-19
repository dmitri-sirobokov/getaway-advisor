package io.getawayadvisor.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Activity {
    private UUID id;
    private LocalDate date;
    private String name;
    private List<UUID> suggestions;
}
