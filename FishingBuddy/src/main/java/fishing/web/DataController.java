package fishing.web;

import fishing.Bait;
import fishing.Fish;
import fishing.data.BaitRepository;
import fishing.data.FishRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/data")
@Slf4j
public class DataController {

    private FishRepository fishRepo;
    private BaitRepository baitRepo;

    @Autowired
    public DataController(FishRepository fishRepo, BaitRepository baitRepo) {
        this.fishRepo = fishRepo;
        this.baitRepo = baitRepo;
    }

   @GetMapping
   public String showData() {
      return "data";
    }

    @ModelAttribute
    public void addBait (Model model)
    {
        model.addAttribute("bait", Arrays.asList(Fish.class));
        List<Bait> allData = fishRepo.findAllByOrderByPostedAtDesc();
        model.addAttribute("posts", allData);
    }
}
