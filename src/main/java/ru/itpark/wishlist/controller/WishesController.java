package ru.itpark.wishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
