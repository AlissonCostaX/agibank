package br.com.alissoncostax.agibank.exception;

public class BusinessAgibankFileException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BusinessAgibankFileException(String mensagem) {
		super(mensagem);
	}

}
