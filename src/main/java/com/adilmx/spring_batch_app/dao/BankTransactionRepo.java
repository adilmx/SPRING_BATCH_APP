package com.adilmx.spring_batch_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adilmx.spring_batch_app.entity.BankTransaction;

@Repository
public interface BankTransactionRepo extends JpaRepository<BankTransaction, Long>{

}
