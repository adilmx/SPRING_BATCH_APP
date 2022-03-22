package com.adilmx.spring_batch_app.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransaction {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private long accountId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	@Transient
	private String strTransactionDate;
	private String transactionType;
	private double amount;
}
