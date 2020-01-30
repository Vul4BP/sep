package com.example.seller.service;

import com.example.seller.Dto.ItemDto;
import com.example.seller.models.Item;
import com.example.seller.models.Seller;
import com.example.seller.repo.ItemRepository;
import com.example.seller.repo.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements IItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setGeneratedId(itemDto.getGeneratedId());
        item.setAmount(itemDto.getAmount());
        //item.setIzdanje(itemDto.getIzdanje());

        Seller seller = sellerRepository.findByEmail(itemDto.getSellerEmail());
        if(seller != null) {
            item.setSeller(seller);
        }

        try {
            itemRepository.save(item);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        return itemDto;
    }
}
