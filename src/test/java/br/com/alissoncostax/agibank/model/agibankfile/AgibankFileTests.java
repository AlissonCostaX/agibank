package br.com.alissoncostax.agibank.model.agibankfile;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AgibankFileTests {
	
    @Test
    public void getSalesmans() {
    	AgibankFile agibankFile = new AgibankFile();
    	agibankFile.addSalesman("001ç11111111111çPedroç500000");
    	agibankFile.addSalesman("001ç22222222222çJoseç800");
    	Assertions.assertTrue(agibankFile.getSalesmans().size() == 2);
        Salesman salesman = agibankFile.getSalesmans().get(0);
        Assertions.assertTrue("11111111111".equals(salesman.getCpf()));
    }
    
    @Test
    public void getClient() {
    	AgibankFile agibankFile = new AgibankFile();
    	agibankFile.addClient("002ç84700540000178çEduardo PereiraçRural");
    	agibankFile.addClient("002ç27937574000147çRicardo SouzaçEmpresario");
    	agibankFile.addClient("002ç30063320000151çFelipe AlvesçTurista");
    	Assertions.assertTrue(agibankFile.getClients().size() == 3);
        Client client = agibankFile.getClients().get(2);
        Assertions.assertTrue("Turista".equals(client.getBusinessArea()));
        Assertions.assertFalse("Eduardo Pereira".equals(client.getName()));
    }
    
    @Test
    public void getSales() {
    	AgibankFile agibankFile = new AgibankFile();
    	agibankFile.addSale("003ç20ç[1-34-10,2-33-1.50,3-40-0.10]çMario");
    	Assertions.assertTrue(agibankFile.getSales().size() == 1);
        Sale sale = agibankFile.getSales().get(0);
        Assertions.assertTrue("Mario".equals(sale.getSalesmanName()));
        Assertions.assertTrue(sale.getItens().size() == 3);
    }
    
    @Test
    public void getItens() {
    	AgibankFile agibankFile = new AgibankFile();
    	agibankFile.addSale("003ç20ç[1-34-10,2-33-1.50,3-40-0.10]çMario");
    	Item item = agibankFile.getSales().get(0).getItens().get(0);
        Assertions.assertTrue(item.getId() == 1);
        item = agibankFile.getSales().get(0).getItens().get(1);
        Assertions.assertTrue(item.getQuantity().compareTo(new BigDecimal(33)) == 0);
        item = agibankFile.getSales().get(0).getItens().get(2);
        Assertions.assertTrue(item.getPrice().compareTo(new BigDecimal("0.10")) == 0);
    }
	
}
