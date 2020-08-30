package br.com.alissoncostax.agibank.util;

import java.io.File;

public abstract class Constants {
	
	/*
	 * CONSTANTS LOCALE AND TYPE AGIBANK FILE
	 * */
	public static final String INPUT_AGIBANK = "data".concat(File.separator).concat("in");
	public static final String OUTPUT_AGIBANK = "data".concat(File.separator).concat("out");
	public static final String FORMAT_INPUT_FILE = ".dat";
	public static final String FORMAT_OUTPUT_FILE = "done.dat";
	
	/*
	 * CONSTANTS AGIBANK FILE
	 * */
	public static final String DEFAULT_SEPARATOR_FILE = "ç";
	public static final String DEFAULT_SEPARATOR_SALES = ",";
	public static final String DEFAULT_SEPARATOR_ITEM = "-";
	
	/*
	 * CONSTANTS OF SALES AGIBANK FILE
	 * */
	public static final String SALESMAN = "001";
	public static final String CLIENT = "002";
	public static final String SALES = "003";
	
}
