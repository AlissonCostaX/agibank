package br.com.alissoncostax.agibank.model.agibankfile;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Salesman {
	
	private String cpf;
	private String name;
	private BigDecimal salary;
	
}
