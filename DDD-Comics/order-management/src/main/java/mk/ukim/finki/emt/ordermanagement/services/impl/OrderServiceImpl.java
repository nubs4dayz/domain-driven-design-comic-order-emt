package mk.ukim.finki.emt.ordermanagement.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagement.domain.excpetions.ComicInOrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.excpetions.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.models.ComicInOrderId;
import mk.ukim.finki.emt.ordermanagement.domain.models.Order;
import mk.ukim.finki.emt.ordermanagement.domain.models.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.repositories.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.services.OrderService;
import mk.ukim.finki.emt.ordermanagement.services.forms.ComicInOrderForm;
import mk.ukim.finki.emt.ordermanagement.services.forms.OrderForm;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.ComicInOrderCreated;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final DomainEventPublisher domainEventPublisher;

//    private final Validator validator;

    @Override
    public OrderId placeOrder(@Valid OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "Order must not be null");
//        var constraintViolations = validator.validate(orderForm);
//
//        if(constraintViolations.size() > 0)
//            throw new ConstraintViolationException("The order form is not valid", constraintViolations);

        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));

        newOrder.getComicsInOrderList()
                .forEach(comic -> domainEventPublisher.publish(new ComicInOrderCreated(comic.getComicId().getId(), comic.getQuantity())));

        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void addComic(OrderId orderId, ComicInOrderForm comicInOrderForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addComic(comicInOrderForm.getComic(), comicInOrderForm.getQuantity());
        orderRepository.saveAndFlush(order);

        domainEventPublisher.publish(new ComicInOrderCreated(comicInOrderForm.getComic().getId().getId(), comicInOrderForm.getQuantity()));
    }

    @Override
    public void deleteComic(OrderId orderId, ComicInOrderId comicInOrderId) throws OrderIdNotExistException, ComicInOrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeComic(comicInOrderId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency());
        orderForm.getComics().forEach(item -> order.addComic(item.getComic(), item.getQuantity()));
        return order;
    }
}
