package fishing.web;

import fishing.Bait.Type;
import fishing.Bait;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping ("/baits")
public class ListBaitsController {

    @GetMapping
    public String showBaits() {
        return "baits";
    }

    @ModelAttribute
    public void addAttribute (Model model){
        List<Bait> baits = creatBaitList();
        Type [] types = Bait.Type.values();
        for(Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(baits, type));
        }
    }

    private List<Bait> filterByType(List<Bait> baits, Type type) {
        return baits
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


    private List<Bait> creatBaitList() {
        List<Bait> baits = Arrays.asList(
                new Bait( "D", "Daredevil", Type.SPOON),
                new Bait( "JS", "Johnson Silver Minnow", Type.SPOON),
                new Bait( "CM", "Cast Master", Type.SPOON),
                new Bait( "LC", "Little Cleo", Type.SPOON),
                new Bait( "FoJ", "Football Jig", Type.JIG),
                new Bait( "FlJ", "Flipping Jig", Type.JIG),
                new Bait( "SJ", "Swimming Jig", Type.JIG),
                new Bait( "APJ", "Arkey Power Jig", Type.JIG),
                new Bait( "AFJ", "Arkey Finesse Jig", Type.JIG),
                new Bait( "PSB", "Plastic Stick Bait", Type.PLASTIC_BAIT),
                new Bait( "SPB", "Soft Plastic Swimming Bait", Type.PLASTIC_BAIT),
                new Bait( "SPC", "Soft Plastic Craw", Type.PLASTIC_BAIT),
                new Bait( "FW", "Finesse Worm", Type.PLASTIC_BAIT),
                new Bait( "RTW", "Ribbon Tail Worm", Type.PLASTIC_BAIT),
                new Bait( "MH", "Mayfly Hatch", Type.FLIES),
                new Bait( "CFH", "Caddis Fly Hatch", Type.FLIES),
                new Bait( "SH", "Stonefly Hatch", Type.FLIES),
                new Bait( "MF", "Midges Fly", Type.FLIES),
                new Bait( "SF", "Scuds Fly", Type.FLIES),
                new Bait( "LF", "Leeches Fly", Type.FLIES),
                new Bait( "KVD", "Strike King KVD", Type.SPINNER),
                new Bait( "TeT", "Terminator T1", Type.SPINNER),
                new Bait( "NP", "Nochols Pulsator", Type.SPINNER),
                new Bait( "Mi", "Minnows", Type.LIVE_BAIT),
                new Bait( "In", "Insects", Type.LIVE_BAIT),
                new Bait( "Wo", "Worms", Type.LIVE_BAIT),
                new Bait( "Sh", "Shrimp", Type.LIVE_BAIT),
                new Bait( "Cr", "Crayfish", Type.LIVE_BAIT)
        );

        return baits;
    }
}
