package mk.ukim.finki.emt.comiccatalog.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.comiccatalog.domain.exceptions.ComicNotFoundException;
import mk.ukim.finki.emt.comiccatalog.domain.models.Comic;
import mk.ukim.finki.emt.comiccatalog.domain.models.ComicId;
import mk.ukim.finki.emt.comiccatalog.domain.repositories.ComicRepository;
import mk.ukim.finki.emt.comiccatalog.services.ComicService;
import mk.ukim.finki.emt.comiccatalog.services.forms.ComicForm;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ComicServiceImpl implements ComicService {

    private final ComicRepository comicRepository;

    @Override
    public Comic findById(ComicId id) {
        return comicRepository.findById(id).orElseThrow(ComicNotFoundException::new);
    }

    @Override
    public Comic createComic(ComicForm form) {
        Comic comic = Comic.build(form.getComicName(), form.getPrice(), form.getSales());
        comicRepository.saveAndFlush(comic);
        return comic;
    }

    @Override
    public Comic ComicInOrderCreated(ComicId comicId, int quantity) {
        Comic comic = comicRepository.findById(comicId).orElseThrow(ComicNotFoundException::new);
        comic.addSales(quantity);
        comicRepository.saveAndFlush(comic);
        return comic;
    }

    @Override
    public Comic ComicInOrderRemoved(ComicId comicId, int quantity) {
        Comic comic = comicRepository.findById(comicId).orElseThrow(ComicNotFoundException::new);
        comic.removeSales(quantity);
        comicRepository.saveAndFlush(comic);
        return comic;
    }

    @Override
    public List<Comic> getAll() {
        return comicRepository.findAll();
    }
}
