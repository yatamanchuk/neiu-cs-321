package fishing.web;


import fishing.Fish;
import fishing.User;
import fishing.data.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private FishRepository fishRepo;

    @Autowired
    public OrderController(FishRepository fishRepo) {
        this.fishRepo = fishRepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderform";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("fish") Fish fish, Errors errors,
                                @AuthenticationPrincipal User user)
    {
        if(errors.hasErrors())
            return "orderform";

        fish.setUser(user);
        return "redirect:/";
    }

}
