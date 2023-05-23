package mk.ukim.finki.emt.comiccatalog.domain.repositories;

import mk.ukim.finki.emt.comiccatalog.domain.models.Comic;
import mk.ukim.finki.emt.comiccatalog.domain.models.ComicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic, ComicId> {
}
