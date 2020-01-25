package com.example.seller.Dto;

import com.example.seller.models.NacinPlacanja;
import lombok.Data;

import java.util.List;

@Data
public class MagazineDto {
    private Long id;
    private String naziv;
    private String issn;
    private Long clanarina;
    private List<NacinPlacanja> naciniPlacanja;
    //private String komeSePlaca;
    //private boolean enabled;
    //private List<NaucnaOblast> naucneoblasti;
    //private List<UserDto> urednici;
    //private List<UserDto> recenzenti;
}
