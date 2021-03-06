package agh.bit.eventsbc.domain.common;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;

/**
 * Created by novy on 08.01.15.
 */

public abstract class IdentifiedDomainEntity<ID extends DomainIdentifier>
        extends AbstractAnnotatedEntity implements IdentifiedObject<ID> {

    private final ID id;

    protected IdentifiedDomainEntity(ID id) {
        this.id = id;
    }

    @Override
    public ID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !this.getClass().isInstance(o)) return false;

        IdentifiedDomainEntity that = (IdentifiedDomainEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
