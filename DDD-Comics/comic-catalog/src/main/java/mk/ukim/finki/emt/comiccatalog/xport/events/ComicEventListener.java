package mk.ukim.finki.emt.comiccatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.comiccatalog.domain.models.ComicId;
import mk.ukim.finki.emt.comiccatalog.services.ComicService;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.ComicInOrderCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.ComicInOrderRemoved;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComicEventListener {

    private final ComicService comicService;

    @KafkaListener(topics = TopicHolder.TOPIC_COMIC_IN_ORDER_CREATED, groupId = "comicCatalog")
    public void consumeComicInOrderCreatedEvent(String jsonMessage) {
        try {
            ComicInOrderCreated event = DomainEvent.fromJson(jsonMessage, ComicInOrderCreated.class);
            comicService.ComicInOrderCreated(ComicId.of(event.getComicId()), event.getQuantity());
        } catch (Exception e){

        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_COMIC_IN_ORDER_REMOVED, groupId = "comicCatalog")
    public void consumeComicInOrderRemovedEvent(String jsonMessage) {
        try {
            ComicInOrderRemoved event = DomainEvent.fromJson(jsonMessage, ComicInOrderRemoved.class);
            comicService.ComicInOrderRemoved(ComicId.of(event.getComicId()), event.getQuantity());
        } catch (Exception e){

        }
    }
}
