package fishing;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Bait {

    @Id
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;

    public enum Type {
        SPOON, JIG, PLASTIC, FLIES, SPINNER,
        LIVE_BAIT
    }
}
