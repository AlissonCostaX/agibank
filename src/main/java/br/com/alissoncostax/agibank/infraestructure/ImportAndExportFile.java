package br.com.alissoncostax.agibank.infraestructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class ImportAndExportFile {
	
	private String inputLocale;
	private String outputLocale;
	private String formatInputFile;
	private String formatOutputFile;
	private List<Path> files;
	private List<String> lines;
	
	public ImportAndExportFile(String inputLocale, String outputLocale, String formatInputFile, String formatOutputFile) {
		this.inputLocale = inputLocale;
		this.outputLocale = outputLocale;
		this.formatInputFile = formatInputFile;
		this.formatOutputFile = formatOutputFile;
	}
	
	public void importFiles() throws FileNotFoundException {
		Path path = Paths.get(this.getInputLocale());
		if (!Files.exists(path)) {
			throw new FileNotFoundException("Directory not found(s)!");
		}
		try {
			this.files = Files.list(path)
							   .filter(file -> file.toString().endsWith(this.getFormatInputFile()))
							   .collect(Collectors.toList());
		} catch (Exception erro) {
			log.error("[ImportAndExportFile] [importFiles] - Error Import Files!", erro);
		}
	}
	
	public void readLines() {
		files.forEach(file -> {
			try {
			lines = Files.readAllLines(file)
					 .stream()
					 .map(s -> s.trim())
					 .collect(Collectors.toList());
			} catch (Exception erro) {
				log.error("[ImportAndExportFile] [readLines] - Error Reading Lines from Files!", erro);
			}
		});
	}
	
	public void exportFile(List<String> lines) throws IOException {
		if (Objects.isNull(lines) || lines.isEmpty()) {
			return;
		}
		try {
			Path path = Paths.get(this.getOutputLocale()+File.separator+this.getFormatOutputFile());
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (Exception erro) {
			log.error("[ImportAndExportFile] [exportFile] - Output File Export Error!", erro);
		}
	}
	
	public void createDirectoryIfNotExists(String path) {
		if (!new File(path).exists()) {
			new File(path).mkdirs();
		}
	}

}
