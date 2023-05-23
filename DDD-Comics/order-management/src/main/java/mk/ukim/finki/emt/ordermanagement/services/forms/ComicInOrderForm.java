package mk.ukim.finki.emt.ordermanagement.services.forms;

import lombok.Data;
import mk.ukim.finki.emt.ordermanagement.domain.valueobjects.Comic;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ComicInOrderForm {

    @NotNull
    private Comic comic;

    @Min(1)
    private int quantity = 1;
}
