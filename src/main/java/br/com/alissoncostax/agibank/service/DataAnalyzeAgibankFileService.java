package br.com.alissoncostax.agibank.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import br.com.alissoncostax.agibank.exception.BusinessAgibankFileException;
import br.com.alissoncostax.agibank.infraestructure.ImportAndExportFile;
import br.com.alissoncostax.agibank.model.agibankfile.AgibankFile;
import br.com.alissoncostax.agibank.model.agibankfile.Sale;
import br.com.alissoncostax.agibank.service.validation.ValidateAgibankFile;
import br.com.alissoncostax.agibank.util.Constants;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DataAnalyzeAgibankFileService {
	
	private AgibankFile file = new AgibankFile();
	
	public void init() {
		try {
			log.info("[DataAnalyzeAgibankFileService] [init] - Starting File Data Analysis");
			
			ImportAndExportFile importAndExportFile = new ImportAndExportFile(
					Constants.INPUT_AGIBANK,  Constants.OUTPUT_AGIBANK,
					Constants.FORMAT_INPUT_FILE, Constants.FORMAT_OUTPUT_FILE);
			importAndExportFile.createDirectoryIfNotExists(Constants.INPUT_AGIBANK);
			importAndExportFile.importFiles();
			importAndExportFile.readLines();
			
			this.processAnalyzeFileAgibank(importAndExportFile.getLines());
			
			importAndExportFile.createDirectoryIfNotExists(Constants.OUTPUT_AGIBANK);
			importAndExportFile.exportFile(this.exportDataAnalysisLines());
			
			log.info("[DataAnalyzeAgibankFileService] [init] - Finish File Data Analysis");
		} catch (Exception erro) {
			log.error("[DataAnalyzeAgibankFileService] [init] - Error Process Analysis File", erro);
			throw new BusinessAgibankFileException("[DataAnalyzeAgibankFileService] [init] - Error Process Analysis File");
		}
	}
	
	private void processAnalyzeFileAgibank(List<String> lines) {
		if (Objects.nonNull(lines)) {
			lines.forEach(line -> {
				if (line.startsWith(Constants.SALESMAN)) {
					log.info("[DataAnalyzeAgibankFileService] [processAnalyzeFileAgibank] - Starting Process Analysis Salesman");
					ValidateAgibankFile.validLayoutLineSalesman(line);
					file.addSalesman(line);
	            } else if (line.startsWith(Constants.CLIENT)) {
	            	log.info("[DataAnalyzeAgibankFileService] [processAnalyzeFileAgibank] - Starting Process Analysis Client");
	            	ValidateAgibankFile.validLayoutLineClient(line);
	            	file.addClient(line);
	            } else if (line.startsWith(Constants.SALES)) {
	            	log.info("[DataAnalyzeAgibankFileService] [processAnalyzeFileAgibank] - Starting Process Analysis Sales");
	            	ValidateAgibankFile.validLayoutLineSales(line);
	            	file.addSale(line);
	            } else {
	            	log.error("[DataAnalyzeAgibankFileService] [processAnalyzeFileAgibank] - Type of this line is not valid!");
	            }
				log.info("[DataAnalyzeAgibankFileService] [processAnalyzeFileAgibank] - Finish Process Analysis");
			});
		} else {
			log.info("[DataAnalyzeAgibankFileService] [processAnalyzeFileAgibank] - File(s) or Lines not found!");
		}
	}
	
	public List<String> exportDataAnalysisLines() {
		if (file.getSalesmans().isEmpty() || file.getClients().isEmpty() || file.getSales().isEmpty()) {
			return new ArrayList<String>();
		}
		Sale saleMax = file.getSales().stream().max(Comparator.comparing(Sale::getPriceTotal)).orElse(null);
		Sale saleMin = file.getSales().stream().min(Comparator.comparing(Sale::getPriceTotal)).orElse(null); 
	    String qtyClient = "Quantity of Clients: "+file.getClients().size();
	    String qtySalesman = "Quantity of Salesman: "+file.getClients().size();
	    String codeHighestValueSale = "Highest Sale Code: "+ (Objects.nonNull(saleMax) ? saleMax.getId() : "N/A");
	    String nameSalesmanLowestSale = "Smallest Seller's Name: "+ (Objects.nonNull(saleMin) ? saleMin.getSalesmanName() : "N/A");
	    return Arrays.asList(qtyClient, qtySalesman, codeHighestValueSale, nameSalesmanLowestSale);
	}
	
}
