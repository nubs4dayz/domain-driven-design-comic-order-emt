package mk.ukim.finki.emt.ordermanagement.domain.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.ComicId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "comic_in_order")
@Getter
public class ComicInOrder extends AbstractEntity<ComicInOrderId> {

    private Money itemPrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "comic_id", nullable = false))
    private ComicId comicId;

    private ComicInOrder() {
        super(DomainObjectId.randomId(ComicInOrderId.class));
    }

    public ComicInOrder(@NonNull ComicId comicId, @NonNull Money itemPrice, int qty) {
        super(DomainObjectId.randomId(ComicInOrderId.class));
        this.comicId = comicId;
        this.itemPrice = itemPrice;
        this.quantity = qty;
    }

    public Money subtotal(){
        return itemPrice.multiply(quantity);
    }
}
