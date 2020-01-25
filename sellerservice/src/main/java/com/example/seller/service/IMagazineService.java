package com.example.seller.service;

import com.example.seller.Dto.MagazineDto;
import com.example.seller.models.Magazine;

public interface IMagazineService {
    public MagazineDto addCasopis(MagazineDto magazineDto);
    public Magazine getByMagazineId(Long id);
}
