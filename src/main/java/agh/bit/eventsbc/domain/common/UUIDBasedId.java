package agh.bit.eventsbc.domain.common;

import java.util.UUID;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class UUIDBasedId implements DomainIdentifier {
    private final String id;

    public UUIDBasedId() {
        this.id = randomUUIDString();
    }

    private String randomUUIDString() {
        return UUID.randomUUID().toString();
    }

    public String id() {
        return id;
    }
}
