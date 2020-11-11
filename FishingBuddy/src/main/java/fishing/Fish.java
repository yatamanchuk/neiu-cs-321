package fishing;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;


import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;


    @ManyToMany(targetEntity = Bait.class)

    @NotEmpty(message = "You must choose at least 1 bait")
    private List<Bait> baits;

    @PrePersist
    void createdAt() {

        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne
    private User user;

    public void addUserToFish(User user) {
        this.setUser(user);
    }
}
