package mk.ukim.finki.emt.ordermanagement.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.Comic;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderedOn;

    @Enumerated(value = EnumType.STRING)
    private OrderState orderState;

    @Column(name = "order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ComicInOrder> comicsInOrderList = new HashSet<>();

    private Order() {
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = now;
        this.currency = currency;
    }

    public Money total(){
        return comicsInOrderList.stream()
                .map(ComicInOrder::subtotal)
                .reduce(new Money(currency, 0), Money::add);
    }

    public ComicInOrder addComic(@NonNull Comic comic, int qty) {
        Objects.requireNonNull(comic, "Comic must not be null");
        var item = new ComicInOrder(comic.getId(), comic.getPrice(), qty);
        comicsInOrderList.add(item);
        return item;
    }

    public void removeComic(@NonNull ComicInOrderId comicInOrderId) {
        Objects.requireNonNull(comicInOrderId, "Comic in Order must not be null");
        comicsInOrderList.removeIf(v -> v.getId().equals(comicInOrderId));
    }
}
