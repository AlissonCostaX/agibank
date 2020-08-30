package br.com.alissoncostax.agibank.service.validation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.alissoncostax.agibank.exception.BusinessAgibankFileException;
import br.com.alissoncostax.agibank.util.Constants;

public class ValidateAgibankFile {
	
	public static void validLayoutLineSalesman(String line) {
		List<String> fields = Stream.of(line.split(Constants.DEFAULT_SEPARATOR_FILE)).collect(Collectors.toList());
		validSize(fields, 4);
		validCPF(fields.get(1));
		validValueMonetary(fields.get(3));
	}
	
	public static void validLayoutLineClient(String line) {
		List<String> fields = Stream.of(line.split(Constants.DEFAULT_SEPARATOR_FILE)).collect(Collectors.toList());
		validSize(fields, 4);
		validCNPJ(fields.get(1));
	}

	public static void validLayoutLineSales(String line) {
		List<String> fields = Stream.of(line.split(Constants.DEFAULT_SEPARATOR_FILE)).collect(Collectors.toList());
		validSize(fields, 4);
		validLayoutItem(fields.get(2));
	}
	
	public static void validLayoutItem(String lineItens) {
		List<String> fields = Stream.of(lineItens
										.replace("[", "")
										.replace("]", "")
										.split(Constants.DEFAULT_SEPARATOR_SALES)).collect(Collectors.toList());

		fields.forEach(itemLine -> {
			List<String> itemFields = Stream.of(itemLine.split(Constants.DEFAULT_SEPARATOR_ITEM)).collect(Collectors.toList());
			validSize(itemFields, 3);
			validValueNumeric(itemFields.get(0));
			validValueNumeric(itemFields.get(1));
			validValueMonetary(itemFields.get(2));
		});
	}
	
	/*
	 * Generic Validates
	 * */
	
	private static void validSize(List<String> fields, Integer size) {
		if (fields.size() > size || fields.size() < size) {
			throw new BusinessAgibankFileException("Data size must be equals "+size+", the line contains: "+fields);
		}
	}
	
	private static void validCPF(String cpf) {
		if (cpf.length() > 11 || cpf.length() < 11) {
			throw new BusinessAgibankFileException("CPF invalid!");
		}
	}
	
	private static void validCNPJ(String cnpj) {
		if (cnpj.length() > 14 || cnpj.length() < 14) {
			throw new BusinessAgibankFileException("CNPJ invalid!");
		}
	}
	
	private static void validValueNumeric(String field) {
		Boolean isNumeric = field.chars().allMatch( Character::isDigit );
		if (!isNumeric) {
			throw new BusinessAgibankFileException("The value dont's numeric!");
		}
	}
	
	private static void validValueMonetary(String field) {
		try {
			Double.parseDouble(field);
		} catch (Exception erro) {
			throw new BusinessAgibankFileException("The value dont's monetary!");
		}
	}
	
}
