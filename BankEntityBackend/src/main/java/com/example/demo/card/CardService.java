package com.example.demo.card;

import com.example.demo.model.Card;

import java.util.List;

public interface CardService {
    List<Card> findAll();

    Card findByPan(String pan);
}
