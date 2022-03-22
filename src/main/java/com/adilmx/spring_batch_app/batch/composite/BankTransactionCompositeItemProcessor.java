package com.adilmx.spring_batch_app.batch.composite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adilmx.spring_batch_app.batch.BankTransactionItemProcessor;
import com.adilmx.spring_batch_app.batch.analytics.BankTransactionItemAnalyticsProcessor;
import com.adilmx.spring_batch_app.entity.BankTransaction;

@Configuration
public class BankTransactionCompositeItemProcessor {
	@Autowired private BankTransactionItemProcessor bankItemAnalyticsProcessor;
	@Autowired private BankTransactionItemAnalyticsProcessor bankItemProcessor;

	@Bean
	public CompositeItemProcessor<BankTransaction, BankTransaction> compositeItemProcessor() {
		List<ItemProcessor<BankTransaction, BankTransaction>> itemProcessors = new ArrayList<>();
		// define the list of processors
		itemProcessors.add(bankItemProcessor);
		itemProcessors.add(bankItemAnalyticsProcessor);

		CompositeItemProcessor<BankTransaction, BankTransaction> compositeItemProcessor = new CompositeItemProcessor<>();
		// add ours processors to the composite
		compositeItemProcessor.setDelegates(itemProcessors);
		return compositeItemProcessor;
	}

}
