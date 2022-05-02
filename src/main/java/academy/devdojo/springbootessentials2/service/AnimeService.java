package academy.devdojo.springbootessentials2.service;

import academy.devdojo.springbootessentials2.domain.Anime;
import academy.devdojo.springbootessentials2.exception.BadRequestException;
import academy.devdojo.springbootessentials2.mapper.AnimeMapper;
import academy.devdojo.springbootessentials2.repository.AnimeRepository;
import academy.devdojo.springbootessentials2.requests.AnimePostRequestBody;
import academy.devdojo.springbootessentials2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List <Anime>  listAllNonPageable() {
        return animeRepository.findAll();
    }


    public List <Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }


    public Anime findById(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
             }

   @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {

        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));

    }

    public void delete(long id) {
        animeRepository.delete(findById(id));
    }


    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findById(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);

    }
}

