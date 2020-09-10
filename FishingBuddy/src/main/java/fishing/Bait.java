package fishing;
import lombok.Data;

@Data
public class Bait {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        SPOON, JIG, PLASTIC_BAIT, FLIES, SPINNER,
        LIVE_BAIT;
    }
}
