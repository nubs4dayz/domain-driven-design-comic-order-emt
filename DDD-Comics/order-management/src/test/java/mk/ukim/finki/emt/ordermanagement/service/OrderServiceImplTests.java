package mk.ukim.finki.emt.ordermanagement.service;

import mk.ukim.finki.emt.ordermanagement.domain.excpetions.OrderIdNotExistException;
import mk.ukim.finki.emt.ordermanagement.domain.models.Order;
import mk.ukim.finki.emt.ordermanagement.domain.models.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.Comic;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.ComicId;
import mk.ukim.finki.emt.ordermanagement.services.OrderService;
import mk.ukim.finki.emt.ordermanagement.services.forms.ComicInOrderForm;
import mk.ukim.finki.emt.ordermanagement.services.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.xport.clients.ComicClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ComicClient comicClient;

    private static Comic newComic(String name, Money price) {
        Comic comic = new Comic(ComicId.randomId(ComicId.class), name, price, 0);
        return comic;
    }

    @Test
    public void testPlaceOrder() {

        ComicInOrderForm oi1 = new ComicInOrderForm();
        oi1.setComic(newComic("Naruto", Money.valueOf(Currency.USD,5)));
        oi1.setQuantity(5);

        ComicInOrderForm oi2 = new ComicInOrderForm();
        oi2.setComic(newComic("Zagor", Money.valueOf(Currency.USD,3)));
        oi2.setQuantity(1);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.USD);
        orderForm.setComics(Arrays.asList(oi1, oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.total(), Money.valueOf(Currency.USD,28));
    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Comic> comicList = comicClient.findAll();
        Comic c1 = comicList.get(0);
        Comic c2 = comicList.get(1);

        ComicInOrderForm oi1 = new ComicInOrderForm();
        oi1.setComic(c1);
        oi1.setQuantity(1);

        ComicInOrderForm oi2 = new ComicInOrderForm();
        oi2.setComic(c2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.USD);
        orderForm.setComics(Arrays.asList(oi1, oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = c1.getPrice().multiply(oi1.getQuantity()).add(c2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total(), outMoney);
    }
}
