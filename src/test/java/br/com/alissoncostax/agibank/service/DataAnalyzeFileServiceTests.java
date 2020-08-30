package br.com.alissoncostax.agibank.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataAnalyzeFileServiceTests {
	
	@Test
	void shouldExecuteSuccessfully() {
		Assertions.assertDoesNotThrow(() -> {
			DataAnalyzeAgibankFileService dataAnalyzeFileService = new DataAnalyzeAgibankFileService();
			dataAnalyzeFileService.init();
		});
	}
	
}
