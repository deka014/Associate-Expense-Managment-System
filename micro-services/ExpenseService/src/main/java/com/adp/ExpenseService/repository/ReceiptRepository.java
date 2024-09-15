package com.adp.ExpenseService.repository;

import com.adp.EntitiesService.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {

}

