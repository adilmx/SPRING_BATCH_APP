package com.adilmx.spring_batch_app.batch;

import java.text.SimpleDateFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.adilmx.spring_batch_app.entity.BankTransaction;

@Component
public class BankTransactionItemProcessor implements ItemProcessor<BankTransaction, BankTransaction>{
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

	@Override
	public BankTransaction process(BankTransaction bankTransaction) throws Exception {
		bankTransaction.setTransactionDate(dateFormat.parse(bankTransaction.getStrTransactionDate()));
		return bankTransaction;
	}

}
