package com.example.seller.repo;

import com.example.seller.models.Magazine;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MagazineRepository extends CrudRepository<Magazine, String> {
    Magazine findByNaziv(String naziv);
    Magazine findByIssn(String issn);
    Magazine findByCasopisId(Long id);
}
