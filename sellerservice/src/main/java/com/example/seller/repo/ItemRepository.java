package com.example.seller.repo;

import com.example.seller.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {
    Item findByGeneratedId(String generatedId);
}
