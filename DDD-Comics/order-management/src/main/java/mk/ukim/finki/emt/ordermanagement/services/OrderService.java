package mk.ukim.finki.emt.ordermanagement.services;

import mk.ukim.finki.emt.ordermanagement.domain.excpetions.ComicInOrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.excpetions.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.models.ComicInOrderId;
import mk.ukim.finki.emt.ordermanagement.domain.models.Order;
import mk.ukim.finki.emt.ordermanagement.domain.models.OrderId;
import mk.ukim.finki.emt.ordermanagement.services.forms.ComicInOrderForm;
import mk.ukim.finki.emt.ordermanagement.services.forms.OrderForm;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderId placeOrder(OrderForm orderForm);

    List<Order> findAll();

    Optional<Order> findById(OrderId orderId);

    void addComic(OrderId orderId, ComicInOrderForm comicInOrderForm) throws OrderIdNotExistException;

    void deleteComic(OrderId orderId, ComicInOrderId comicInOrderId) throws OrderIdNotExistException, ComicInOrderIdNotExistException;
}
