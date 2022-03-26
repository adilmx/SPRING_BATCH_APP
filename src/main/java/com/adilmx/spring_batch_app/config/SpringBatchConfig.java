package com.adilmx.spring_batch_app.config;

import com.adilmx.spring_batch_app.batch.mapper.FieldMapper;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adilmx.spring_batch_app.batch.BankTransactionItemProcessor;
import com.adilmx.spring_batch_app.batch.analytics.BankTransactionItemAnalyticsProcessor;
import com.adilmx.spring_batch_app.entity.BankTransaction;



@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	
	@Autowired private ItemReader<BankTransaction> bankTransactionItemReader;
	@Autowired private ItemWriter<BankTransaction> bankTransactionItemWriter;
	//@Autowired private ItemProcessor<BankTransaction, BankTransaction> bankTransactionitemProcessor;
	@Autowired private CompositeItemProcessor<BankTransaction, BankTransaction> compositeItemProcessor;

	//@Bean
	public Job bankJob() {
		Step step = stepBuilderFactory.get("step-load-data")
				.<BankTransaction, BankTransaction>chunk(100)
				.reader(bankTransactionItemReader)
				//.processor(bankTransactionitemProcessor)
				.processor(compositeItemProcessor)
				.writer(bankTransactionItemWriter)
				.build();
		return (Job) jobBuilderFactory.get("ETL-load")
				.incrementer(new RunIdIncrementer())
				.start(step)
				.build();
	}

	
	@Bean(name = "fieldMapper")
    public FieldMapper fieldSetMapper(){
        FieldMapper mapper = new FieldMapper();
        mapper.setTargetType(BankTransaction.class);
        return mapper;
    }
}
