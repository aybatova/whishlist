package ru.itpark.wishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.wishlist.domain.Wish;
import ru.itpark.wishlist.service.WishesService;

@Controller
@RequestMapping("/wishlist")
public class WishesController {
    private final WishesService wishesService;

    public WishesController(WishesService wishesService) {
        this.wishesService = wishesService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("wishlist", wishesService.findAll());
        return "wishlist";
    }

    @GetMapping ("/add")
    public String addForm() {
        return "wish-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Wish wish) {
        wishesService.add(wish);

        return "redirect:/wishlist";
    }


    @GetMapping ("/{id}")
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("wish", wishesService.findById (id));
        return "wish";
    }

    @PostMapping("{id}/remove")
    public String remove(@PathVariable int id) {
        wishesService.removeById(id);

        return "redirect:/wishlist";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id) {
        return "wish-edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(
            @PathVariable int id,
            @ModelAttribute Wish wish
    ) {
        wishesService.save(wish);
        return "redirect:/wishlist";
    }

}
