package br.com.alissoncostax.agibank.model.agibankfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Client {
	
	private String cnpj;
	private String name;
	private String businessArea;
	
}
