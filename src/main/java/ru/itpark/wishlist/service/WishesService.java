package ru.itpark.wishlist.service;

import org.springframework.stereotype.Service;
import ru.itpark.wishlist.domain.Wish;
import ru.itpark.wishlist.repository.WishesRepository;

import java.util.List;

@Service
public class WishesService {
    private final WishesRepository wishesRepository;

    public WishesService(WishesRepository wishesRepository) {
        this.wishesRepository = wishesRepository;
    }

    public List<Wish> findAll() {

        return wishesRepository.findAll();

    }

    public Wish findById(int id) {
        return wishesRepository.findById(id);
    }

    public void removeById(int id) {
        wishesRepository.removeById(id);
    }

    public void add(Wish wish) {
        wishesRepository.add (wish);
    }
}
