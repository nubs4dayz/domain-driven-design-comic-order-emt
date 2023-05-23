package mk.ukim.finki.emt.sharedkernel.domain.events.orders;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class ComicInOrderCreated extends DomainEvent {

    private String comicId;

    private int quantity;

    public ComicInOrderCreated(String topic) {
        super(TopicHolder.TOPIC_COMIC_IN_ORDER_CREATED);
    }

    public ComicInOrderCreated(String comicId, int quantity) {
        super(TopicHolder.TOPIC_COMIC_IN_ORDER_CREATED);
        this.comicId = comicId;
        this.quantity = quantity;
    }

}
