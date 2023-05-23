package mk.ukim.finki.emt.ordermanagement.domain.valueobjects;

import jakarta.persistence.Embeddable;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

@Embeddable
public class ComicId extends DomainObjectId {

    private ComicId() {
        super(ComicId.randomId(ComicId.class).getId());
    }

    public ComicId(String uuid) {
        super(uuid);
    }
}
