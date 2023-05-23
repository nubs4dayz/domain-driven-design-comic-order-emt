package mk.ukim.finki.emt.comiccatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.comiccatalog.domain.models.Comic;
import mk.ukim.finki.emt.comiccatalog.services.ComicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comic")
@AllArgsConstructor
public class ComicResource {

    private final ComicService comicService;

    @GetMapping
    public List<Comic> getAll(){
        return comicService.getAll();
    }
}
