package com.example.seller.service;

import com.example.seller.Dto.MagazineDto;
import com.example.seller.models.Magazine;
import com.example.seller.repo.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MagazineService implements IMagazineService {

    @Autowired
    private MagazineRepository magazineRepository;

    @Override
    public MagazineDto addCasopis(MagazineDto magazineDto) {
        Magazine magazine = new Magazine();

        magazine.setCasopisId(magazineDto.getId());
        magazine.setNaziv(magazineDto.getNaziv());
        magazine.setIssn(magazineDto.getIssn());
        magazine.setClanarina(magazineDto.getClanarina());
        magazine.setNaciniPlacanja(magazineDto.getNaciniPlacanja());

        try {
            magazineRepository.save(magazine);
        } catch (Exception e){
          System.out.println(e.getMessage());
          return null;
        }

        return magazineDto;
    }

    @Override
    public Magazine getByMagazineId(Long id) {
        return  magazineRepository.findByCasopisId(id);
    }
}
