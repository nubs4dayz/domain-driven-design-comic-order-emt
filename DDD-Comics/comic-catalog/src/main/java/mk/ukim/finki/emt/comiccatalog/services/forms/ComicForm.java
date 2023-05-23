package mk.ukim.finki.emt.comiccatalog.services.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.validation.constraints.NotNull;

@Data
public class ComicForm {

    @NotNull
    private String comicName;

    @NotNull
    private Money price;

    @NotNull
    private int sales;
}
