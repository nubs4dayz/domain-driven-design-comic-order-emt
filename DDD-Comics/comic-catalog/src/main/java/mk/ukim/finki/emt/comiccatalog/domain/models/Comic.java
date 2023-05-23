package mk.ukim.finki.emt.comiccatalog.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Entity
@Table(name = "comic")
@Getter
public class Comic extends AbstractEntity<ComicId> {

    private String comicName;

    private int sales = 0;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money price;

    private Comic() {
        super(ComicId.randomId(ComicId.class));
    }

    public static Comic build(String comicName, Money price, int sales) {
        Comic comic = new Comic();
        comic.price = price;
        comic.comicName = comicName;
        comic.sales = sales;
        return comic;
    }

    public void addSales(int qty) {
        this.sales += qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }
}
