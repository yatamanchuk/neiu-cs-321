package fishing;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Fish {

    private Long id;
    private LocalDateTime createdAt;

    @NotNull(message = "Name is required")
    @Size(min = 2, message = "Name must be at least 5 characters long")
    private String name;

    @NotEmpty(message = "You must choose at least 1 bait")
    private List<String> baits;

}
