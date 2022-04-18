package academy.devdojo.springbootessentials2.repository;

import academy.devdojo.springbootessentials2.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AnimeRepository extends JpaRepository<Anime, Long> {

}