package br.com.alissoncostax.agibank.infraestructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ImportAndExportFileTests {
	
	private final static String PATH_IN = "data"+File.separator+"in";
	private final static String PATH_OUT = "data"+File.separator+"out";
	private final String FORMAT_INPUT_FILE = ".dat";
	private final String FORMAT_OUTPUT_FILE = "agibankJunitDone.dat";
	
	@Test
	@Order(1)
	void shouldThrowExceptionIfNoInputDirectoryExists() {
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			ImportAndExportFile importAndExportfile = new ImportAndExportFile(PATH_IN+File.separator+"adbcde", null, FORMAT_INPUT_FILE, null);
			importAndExportfile.importFiles();
		});	
	}
	
	@Test
	@Order(1)
	void shouldThrowExceptionIfNoOutputDirectoryExists() {
		Assertions.assertThrows(FileNotFoundException.class, () -> {
			ImportAndExportFile importAndExportfile = new ImportAndExportFile(PATH_OUT+File.separator+"adbcde", null, FORMAT_INPUT_FILE, null);
			importAndExportfile.importFiles();
		});
	}
	
	@Test
	@Order(2)
	void shouldCreateDirectoriesIfNotExisting() {
		ImportAndExportFile importAndExportfile = new ImportAndExportFile();

		importAndExportfile.createDirectoryIfNotExists(PATH_IN);
		importAndExportfile.createDirectoryIfNotExists(PATH_OUT);
		
		Assertions.assertTrue(new File(PATH_IN).isDirectory());
	}
	
	@Test
	@Order(3)
	void shouldImportCorrectFormatFiles() throws IOException {
		Path path = Paths.get(PATH_IN+File.separator+"agibankJUnitTestes"+FORMAT_INPUT_FILE);
		List<String> lines = Arrays.asList("001ç11111111111çPedroç50000", "001ç32456788654çPauloç40000.99");
		Files.write(path, lines, StandardCharsets.UTF_8);
		
		path = Paths.get(PATH_IN+File.separator+"agibankJUnitTestes"+FORMAT_INPUT_FILE+"fail");
		lines = Arrays.asList("001ç22222222222çPedroç50000", "001ç32456788654çPauloç40000.99");
		Files.write(path, lines, StandardCharsets.UTF_8);
		
		ImportAndExportFile importAndExportfile = new ImportAndExportFile(PATH_IN, null, FORMAT_INPUT_FILE, null);
		importAndExportfile.importFiles();
		Assertions.assertTrue(importAndExportfile.getFiles().size() == 1);
	}
	
	@Test
	@Order(4)
	void shouldExportFile() throws IOException {
		Path path = Paths.get(PATH_OUT+File.separator+FORMAT_OUTPUT_FILE);
		List<String> lines = Arrays.asList("Quantity of Clients: 10", "Quantity of Salesman: 10", "Highest Sale Code: 100", "Smallest Seller's Name: Testes");
		
		ImportAndExportFile importAndExportfile = new ImportAndExportFile(null, PATH_OUT, null, FORMAT_OUTPUT_FILE);
		importAndExportfile.exportFile(lines);
		
		Assertions.assertTrue(path.toFile().isFile());
		
	}
	
}
