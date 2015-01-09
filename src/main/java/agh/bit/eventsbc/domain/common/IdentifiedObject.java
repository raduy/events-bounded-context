package agh.bit.eventsbc.domain.common;

/**
 * Created by novy on 08.01.15.
 */
public interface IdentifiedObject<ID extends DomainIdentifier> {

    ID id();

    default boolean matchesId(ID otherId) {
        return otherId.equals(id());
    }
}
