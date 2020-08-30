package br.com.alissoncostax.agibank.model.agibankfile;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Item {
	
	private Long id;
	private BigDecimal quantity;
	private BigDecimal price;

}
