package fishing;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime createdAt;

    @NotNull(message = "Name is required")
    @Size(min = 2, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Bait.class)
    @NotEmpty(message = "You must choose at least 1 bait")
    private List<Bait> baits;

    @PrePersist
    void createdAt() {

        this.createdAt = LocalDateTime.now();
    }
}
