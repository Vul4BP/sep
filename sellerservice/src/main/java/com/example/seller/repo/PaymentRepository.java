package com.example.seller.repo;

import com.example.seller.models.Payment;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PaymentRepository extends CrudRepository<Payment, String> {

}
