package mk.ukim.finki.emt.comiccatalog.services;

import mk.ukim.finki.emt.comiccatalog.domain.models.Comic;
import mk.ukim.finki.emt.comiccatalog.domain.models.ComicId;
import mk.ukim.finki.emt.comiccatalog.services.forms.ComicForm;

import java.util.List;

public interface ComicService {
    Comic findById(ComicId id);
    Comic createComic(ComicForm form);
    Comic ComicInOrderCreated(ComicId comicId, int quantity);
    Comic ComicInOrderRemoved(ComicId comicId, int quantity);
    List<Comic> getAll();
}
