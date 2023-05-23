package mk.ukim.finki.emt.ordermanagement.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class ComicInOrderId extends DomainObjectId {

    private ComicInOrderId() {
        super(ComicInOrderId.randomId(ComicInOrderId.class).getId());
    }

    public ComicInOrderId(String uuid) {
        super(uuid);
    }
}
