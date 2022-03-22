package com.adilmx.spring_batch_app.batch.analytics;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.adilmx.spring_batch_app.entity.BankTransaction;

import lombok.Getter;

@Component
public class BankTransactionItemAnalyticsProcessor implements ItemProcessor<BankTransaction, BankTransaction>{
	//we can get these data in execution of this processor with state saved
	//(if there is no data we name the processor as StateLess(manipulate only data without saving))
	@Getter private double totalDebit ;
	@Getter private double totalCredit ;

	@Override
	public BankTransaction process(BankTransaction bankTransaction) throws Exception {
		/* totalCredit = 0;
		totalDebit = 0; */
		if(bankTransaction.getTransactionType().equalsIgnoreCase("D")) {
			//totalDebit.add(bankTransaction.getAmount());
			totalDebit += bankTransaction.getAmount();
		}else if(bankTransaction.getTransactionType().equalsIgnoreCase("C")) {
			//totalCredit.add(bankTransaction.getAmount());
			totalCredit += bankTransaction.getAmount();
		}
		
		return bankTransaction;
	}

}
