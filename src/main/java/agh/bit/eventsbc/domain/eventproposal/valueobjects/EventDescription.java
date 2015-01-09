package agh.bit.eventsbc.domain.eventproposal.valueobjects;

import lombok.Value;

/**
 * Created by novy on 03.01.15.
 */
@Value(staticConstructor = "of")
public class EventDescription {

    private final String description;

}