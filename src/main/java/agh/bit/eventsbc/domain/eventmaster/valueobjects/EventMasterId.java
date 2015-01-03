package agh.bit.eventsbc.domain.eventmaster.valueobjects;

import lombok.Value;

/**
 * Created by novy on 03.01.15.
 */

@Value(staticConstructor = "of")
public class EventMasterId {

    private final String id;
}
