package academy.devdojo.springbootessentials2.service;

import academy.devdojo.springbootessentials2.domain.Anime;
import academy.devdojo.springbootessentials2.mapper.AnimeMapper;
import academy.devdojo.springbootessentials2.repository.AnimeRepository;
import academy.devdojo.springbootessentials2.requests.AnimePostRequestBody;
import academy.devdojo.springbootessentials2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public Anime findById(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
             }


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
