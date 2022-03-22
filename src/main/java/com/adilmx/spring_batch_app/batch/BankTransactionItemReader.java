package com.adilmx.spring_batch_app.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.adilmx.spring_batch_app.entity.BankTransaction;

@Configuration
public class BankTransactionItemReader {
	    //define our reader
		@Bean
		public FlatFileItemReader<BankTransaction> flatFileItemReader(@Value("${inputFile}") Resource inputFile){
			FlatFileItemReader<BankTransaction> flatFileItemReader = new FlatFileItemReader<>();
			
			//name of the reader
			flatFileItemReader.setName("CSV-READER");
			//skip line 1 of parameters
			flatFileItemReader.setLinesToSkip(1);
			//set the resource to read (data.csv)
			flatFileItemReader.setResource(inputFile);
			//set the Map between a line of CSV and the entity
			flatFileItemReader.setLineMapper(lineMapper());
			
			return flatFileItemReader;
		}

		@Bean
		public LineMapper<BankTransaction> lineMapper() {
			
			DefaultLineMapper<BankTransaction> lineMapper = new DefaultLineMapper<>();
			DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
			
			//define the delimiter of the data in the file
			lineTokenizer.setDelimiter(",");
			//tolerate empty fields
			lineTokenizer.setStrict(false);
			//defile for every data in the file his occurrence as defined in these names
			lineTokenizer.setNames("id","accountId","strTransactionDate","transactionType","amount");
			
			//set the tokenizer of our line mapper
			lineMapper.setLineTokenizer(lineTokenizer);
			
			//define the target class where fields extracted should be mapped with it
			BeanWrapperFieldSetMapper<BankTransaction> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
			fieldSetMapper.setTargetType(BankTransaction.class);
			
			lineMapper.setFieldSetMapper(fieldSetMapper);
			
			return lineMapper;
		}
}
