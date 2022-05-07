package academy.devdojo.springbootessentials2.util;

import academy.devdojo.springbootessentials2.domain.Anime;
import academy.devdojo.springbootessentials2.requests.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {

    public static AnimePostRequestBody createAnimePostRequestBody(){
        return AnimePostRequestBody.builder()
                .name(AnimeCreator.createAnimeToBeSaved().getName())
                .build();

    }

}
