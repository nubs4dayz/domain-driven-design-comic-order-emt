package mk.ukim.finki.emt.comiccatalog.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class ComicId extends DomainObjectId {

    private ComicId() {
        super(ComicId.randomId(ComicId.class).getId());
    }

    public ComicId(@NonNull String uuid) {
        super(uuid);
    }

    public static ComicId of(String uuid) {
        return new ComicId(uuid);
    }

}
