package br.com.alissoncostax.agibank.model.agibankfile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.alissoncostax.agibank.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgibankFile {
	
	private List<Salesman> salesmans;
	private List<Client> clients;
	private List<Sale> sales;
	
	public AgibankFile() {
		this.salesmans = new ArrayList<Salesman>();
		this.clients = new ArrayList<Client>();
		this.sales = new ArrayList<Sale>();
	}
	
	public void addSalesman(String line) {
		List<String> fields = Stream.of(line.split(Constants.DEFAULT_SEPARATOR_FILE)).collect(Collectors.toList());
		Salesman salesman = Salesman.builder()
									.cpf(fields.get(1))
									.name(fields.get(2))
									.salary(new BigDecimal(fields.get(3)))
									.build();
		this.salesmans.add(salesman);
    }
	
	public void addClient(String line) {
		List<String> fields = Stream.of(line.split(Constants.DEFAULT_SEPARATOR_FILE)).collect(Collectors.toList());
		Client client = Client.builder()
									.cnpj(fields.get(1))
									.name(fields.get(2))
									.businessArea(fields.get(3))
									.build();
		this.clients.add(client);
    }
	
	public void addSale(String line) {
		List<String> fields = Stream.of(line.split(Constants.DEFAULT_SEPARATOR_FILE)).collect(Collectors.toList());
		Sale sale = Sale.builder()
								.id(new Long(fields.get(1)))
								.salesmanName(fields.get(3))
								.build();
		sale.addItem(fields.get(2));
		sale.setPriceTotal(sale.getItens().stream().map(x -> x.getQuantity().multiply(x.getPrice())).reduce(BigDecimal.ZERO, BigDecimal::add));
		this.sales.add(sale);
    }

}
