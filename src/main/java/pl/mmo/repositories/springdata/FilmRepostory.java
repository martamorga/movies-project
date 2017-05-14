package pl.mmo.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mmo.entities.Film;


@Repository
public interface FilmRepostory extends JpaRepository<Film, Long>{
    public Film findById(Long id);
}
