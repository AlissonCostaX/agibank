package br.com.alissoncostax.agibank.model.agibankfile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.alissoncostax.agibank.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Sale {
	
	private Long id;
	private String salesmanName;
	private BigDecimal priceTotal;
	private List<Item> itens;
	
	public void addItem(String line) {
		this.itens = new ArrayList<>();
		List<String> fields = Stream.of(line
				.replace("[", "")
				.replace("]", "")
				.split(Constants.DEFAULT_SEPARATOR_SALES)).collect(Collectors.toList());

		fields.forEach(itemLine -> {
			List<String> itemFields = Stream.of(itemLine.split(Constants.DEFAULT_SEPARATOR_ITEM)).collect(Collectors.toList());
			Item item = Item.builder()
							.id(new Long(itemFields.get(0)))
							.quantity(new BigDecimal(itemFields.get(1)))
							.price(new BigDecimal(itemFields.get(2)))
							.build();
			
			this.itens.add(item);
		});
    }

}
