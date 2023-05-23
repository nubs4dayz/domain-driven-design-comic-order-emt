package mk.ukim.finki.emt.comiccatalog.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.comiccatalog.domain.models.Comic;
import mk.ukim.finki.emt.comiccatalog.domain.repositories.ComicRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final ComicRepository comicRepository;

    @PostConstruct
    public void initData() {
        Comic comic1 = Comic.build("Batman: The Killing Joke", Money.valueOf(Currency.USD,30), 20);
        Comic comic2 = Comic.build("The Avengers: The Infinity Gauntlet", Money.valueOf(Currency.USD,25), 30);
        Comic comic3 = Comic.build("Berserk: Volume 1", Money.valueOf(Currency.USD,70), 8);
        Comic comic4 = Comic.build("The Sandman 1", Money.valueOf(Currency.USD,35), 16);

        if (comicRepository.findAll().isEmpty()) {
            comicRepository.saveAll(Arrays.asList(comic1, comic2, comic3, comic4));
        }
    }


}
