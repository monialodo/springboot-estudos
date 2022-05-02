package academy.devdojo.springbootessentials2.requests;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostRequestBody {
    @NotNull(message = "The anime name cannot be null")
    @NotEmpty (message = "The anime name cannot be empty")
    private String name;
}
