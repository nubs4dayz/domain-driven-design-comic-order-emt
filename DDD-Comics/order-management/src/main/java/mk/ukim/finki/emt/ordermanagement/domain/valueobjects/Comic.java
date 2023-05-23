package mk.ukim.finki.emt.ordermanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Comic implements ValueObject {

    private final ComicId id;

    private final String name;

    private final Money price;

    private final int sales;

    private Comic() {
        this.id = ComicId.randomId(ComicId.class);
        this.name = "";
        this.price = Money.valueOf(Currency.MKD, 0);
        this.sales = 0;
    }

    @JsonCreator
    public Comic(@JsonProperty("id") ComicId id,
                 @JsonProperty("comicName") String name,
                 @JsonProperty("price") Money price,
                 @JsonProperty("sales") int sales) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
    }
}
