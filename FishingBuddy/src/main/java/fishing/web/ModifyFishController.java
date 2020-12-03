package fishing.web;

import fishing.Bait;
import fishing.Fish;
import fishing.data.BaitRepository;
import fishing.data.FishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/modify")
public class ModifyFishController {

    private FishRepository fishRepo;
    private BaitRepository baitRepo;

    @Autowired
    public ModifyFishController(FishRepository fishRepo, BaitRepository baitRepo) {
        this.fishRepo = fishRepo;
        this.baitRepo = baitRepo;
    }

    @GetMapping("/{fishId}")
    public  String updateFish(@PathVariable("fishId") long id, Model model) {
        Fish fish = fishRepo.findById(id).get();
        model.addAttribute("fish", fish);
        model.addAttribute("baitIds", getBaitIds(fish));
        addBaitsToModel(model);
        return "update-fish";
    }

    @PostMapping("/update/{fishId}")
    public String processUpdateFish(@PathVariable("fishId") long id, @Valid @ModelAttribute("fish") Fish fish, Errors errors) {
        if(errors.hasErrors())
            return "update-fish";

        Fish newFish = fishRepo.findById(id).get();
        newFish.setBaits(fish.getBaits());
        newFish.setName(fish.getName());
        fishRepo.save(newFish);
        log.info("Processing..." + newFish);
        return "redirect:/data";
    }

    private List<String> getBaitIds(Fish fish) {
        List<Bait> baits = fish.getBaits();
        List<String> baitIds = new ArrayList<>();
        for(Bait bait: baits){
            baitIds.add(bait.getId());
        }
        return baitIds;
    }

    private void addBaitsToModel (Model model){
        List<Bait> baits = (List<Bait>) baitRepo.findAll();
        Bait.Type[] types = Bait.Type.values();
        for(Bait.Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(baits, type));
        }
    }

    private List<Bait> filterByType(List<Bait> baits, Bait.Type type) {
        return baits
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
