package academy.devdojo.springbootessentials2.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody {
    @NotNull(message = "The anime name cannot be null")
    @NotEmpty (message = "The anime name cannot be empty")
    private String name;
}
