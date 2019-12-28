package com.example.demo.card;

import com.example.demo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findOneByPan(String pan);
}
