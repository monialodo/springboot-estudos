package academy.devdojo.springbootessentials2.repository;

import academy.devdojo.springbootessentials2.domain.Anime;
import academy.devdojo.springbootessentials2.util.AnimeCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@DisplayName("Tests for Anime Repository")
@Log4j2
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;


    @Test
    @DisplayName("Save persists anime when successful")
    void save_PersistAnime_WhenSuccessful(){
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Save update anime when successful")
    void save_UpdateAnime_WhenSuccessful(){
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(animeToBeSaved);
        savedAnime.setName("Overlord");
        Anime animeUpdated = this.animeRepository.save(savedAnime);
        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(savedAnime.getName());
    }

    @Test
    @DisplayName("Delete anime when successful")
    void delete_RemoveAnime_WhenSuccessful(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(anime);
        this.animeRepository.delete(savedAnime);
        Optional<Anime> animeOptional = this.animeRepository.findById(savedAnime.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by name returns a list of anime when successful")
    void findByName_ReturnListOfAnime_WhenSuccessful(){
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime savedAnime = this.animeRepository.save(animeToBeSaved);
        String name = savedAnime.getName();
        List<Anime> animes =  this.animeRepository.findByName(name);
        Assertions.assertThat(animes)
                .isNotEmpty()
                .contains(savedAnime);
    }

    @Test
    @DisplayName("Find by name returns empty list of anime when no anime is found")
    void findByName_ReturnEmptyList_WhenAnimeIsNotFound(){
        List<Anime> animes =  this.animeRepository.findByName("xaxa");
        Assertions.assertThat(animes).isEmpty();

    }


    @Test
    @DisplayName("Save throw ConstraintValidationException when name is empty")
    void save_ThrowsConstraintValidationException_WhenNameIsEmpty(){
        Anime anime = new Anime();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime))
                .withMessageContaining("The anime name cannot be empty");

    }



}