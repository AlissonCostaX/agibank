package br.com.alissoncostax.agibank.service.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alissoncostax.agibank.exception.BusinessAgibankFileException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ValidateAgibankFileTests {
	
	/*
	 * Salesman
	 * */
	
	@Test
	void shouldThrowExceptionIfSizeLineGreaterNecessarySalesman() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutLineSalesman("001ç11111111111çPedroç500000çABCDE");
		});
		Assertions.assertEquals("Data size must be equals 4, the line contains: [001, 11111111111, Pedro, 500000, ABCDE]", exception.getMessage());
	}
	
	@Test
	void shouldThrowExceptionIfCPFSizeIsInvalid() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutLineSalesman("001ç11111111çPedroç500000");
		});
		Assertions.assertEquals("CPF invalid!", exception.getMessage());
	}
	
	@Test
	void shouldThrowExceptionIfSalaryIsInvalid() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutLineSalesman("001ç11111111111çPedroç500ABC000");
		});
		Assertions.assertEquals("The value dont's monetary!", exception.getMessage());
	}
	
	/*
	 * Client
	 * */
	
	@Test
	void shouldThrowExceptionIfSizeLineGreaterNecessaryClient() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutLineClient("002ç2345675434544345çJose da SilvaçRuralçABCDE");
		});
		Assertions.assertEquals("Data size must be equals 4, the line contains: [002, 2345675434544345, Jose da Silva, Rural, ABCDE]", exception.getMessage());
	}
	
	@Test
	void shouldThrowExceptionIfCNPJSizeIsInvalid() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutLineClient("002ç2345675434544345çJose da SilvaçRural");
		});
		Assertions.assertEquals("CNPJ invalid!", exception.getMessage());
	}
	
	/*
	 * Sales
	 * */
	
	@Test
	void shouldThrowExceptionIfSizeLineGreaterNecessarySales() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutLineSales("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPauloçABCDE");
		});
		Assertions.assertEquals("Data size must be equals 4, the line contains: [003, 08, [1-34-10,2-33-1.50,3-40-0.10], Paulo, ABCDE]", exception.getMessage());
	}
	
	/*
	 * Item
	 * */
	
	@Test
	void shouldThrowExceptionIfSizeLineGreaterNecessaryItem() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutItem("[1-34-10-ABC,2-33-1.50,3-40-0.10]");
		});
		Assertions.assertEquals("Data size must be equals 3, the line contains: [1, 34, 10, ABC]", exception.getMessage());
	}
	
	@Test
	void shouldThrowExceptionIfCodeNotNumeric() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutItem("[1-34-10,2-33-1.50,C-40-0.10]");
		});
		Assertions.assertEquals("The value dont's numeric!", exception.getMessage());
	}
	
	@Test
	void shouldThrowExceptionIfQuantityNotNumeric() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutItem("[1-34-10,2-AB-1.50,3-40-0.10]");
		});
		Assertions.assertEquals("The value dont's numeric!", exception.getMessage());
	}
	
	@Test
	void shouldThrowExceptionIfValueItemIsInvalid() {
		Exception exception = Assertions.assertThrows(BusinessAgibankFileException.class, () -> {
			ValidateAgibankFile.validLayoutItem("[1-34-ABC,2-33-1.50,3-40-0.10]");
		});
		Assertions.assertEquals("The value dont's monetary!", exception.getMessage());
	}
	
}
