package fishing.web;

import fishing.Bait.Type;
import fishing.Bait;
import fishing.Fish;
import fishing.User;
import fishing.data.BaitRepository;
import fishing.data.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping ("/baits")
public class ListBaitsController {

    private final BaitRepository baitRepo;
    private final FishRepository fishRepo;

    @Autowired
    public ListBaitsController(BaitRepository baitRepo, FishRepository fishRepo) {
        this.baitRepo = baitRepo;
        this.fishRepo = fishRepo;
    }

    @GetMapping
    public String showBaits() {

        return "baits";
    }

    @PostMapping
    public String processBaits(@Valid @ModelAttribute("fish") Fish fish, Errors errors, @AuthenticationPrincipal User user) {
        if(errors.hasErrors())
            return "baits";

        fish.addUserToFish(user);
        Fish savedFish = fishRepo.save(fish);
        return "redirect:/data";
    }

    @ModelAttribute
    public void addAttribute (Model model){
        List<Bait> baits = (List<Bait>) baitRepo.findAll();
        Type [] types = Type.values();
        for(Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(baits, type));
       }

    }

    @ModelAttribute (name = "fish")
    public Fish addFishToModel() {

        return new Fish();
    }

    private List<Bait> filterByType(List<Bait> baits, Type type) {
        return baits
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
