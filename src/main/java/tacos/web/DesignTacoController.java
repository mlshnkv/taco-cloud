package tacos.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.dto.Ingredient;
import tacos.dto.Taco;
import tacos.dto.TacoOrder;
import tacos.repository.IngredientRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = ingredientRepo.findAll();
        log.info("in method 'addIngredientsToModel'");

        ingredients.stream()
                .collect(Collectors.groupingBy(Ingredient::getType))
                .forEach((type, ingredientList) -> model.addAttribute(type.toString().toLowerCase(), ingredientList));
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        log.info("in ModelAttribute 'tacoOrder'");
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        log.info("in ModelAttribute 'taco'");
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        log.info("in get showDesignForm");
        return "design";
    }

    @PostMapping
    public String processTaco(
            @Valid Taco taco, Errors errors,
            @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}
