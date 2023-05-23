package mk.ukim.finki.emt.sharedkernel.domain.events.orders;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class ComicInOrderRemoved extends DomainEvent {

    private String comicId;

    private int quantity;

    public ComicInOrderRemoved(String topic) {
        super(TopicHolder.TOPIC_COMIC_IN_ORDER_REMOVED);
    }

    public ComicInOrderRemoved(String comicId, int quantity) {
        super(TopicHolder.TOPIC_COMIC_IN_ORDER_REMOVED);
        this.comicId = comicId;
        this.quantity = quantity;
    }

}
