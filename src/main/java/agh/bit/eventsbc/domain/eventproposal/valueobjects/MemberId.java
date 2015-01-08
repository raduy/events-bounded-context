package agh.bit.eventsbc.domain.eventproposal.valueobjects;

import agh.bit.eventsbc.domain.common.DomainIdentifier;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Created by novy on 08.01.15.
 */

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public class MemberId implements DomainIdentifier {

    private final String id;
}
