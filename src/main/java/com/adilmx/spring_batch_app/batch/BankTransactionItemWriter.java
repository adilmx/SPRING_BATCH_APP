package com.adilmx.spring_batch_app.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adilmx.spring_batch_app.dao.BankTransactionRepo;
import com.adilmx.spring_batch_app.entity.BankTransaction;

@Component
public class BankTransactionItemWriter implements ItemWriter<BankTransaction>{
	@Autowired
	private BankTransactionRepo bankTransactionRepo;

	@Override
	public void write(List<? extends BankTransaction> bankTransactions) throws Exception {
		bankTransactionRepo.saveAll(bankTransactions);
	}
    
}
