package com.shopizer.tools.language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shopizer.tools.language.process.LanguageBuilder;


/**
 * Lang pack generator
 * @author carlsamson
 *
 */
public class LanguagePackBuilder {
	
	public static final String targetISOLanguage = "es";
	public static final String shopizerRootPath = "/Users/drive/Documents/dev/workspaces/master/shopizer";
	
	private static final String pathToBundlesFiles = "/sm-shop/src/main/resources/bundles";
	private static List<String> bundleFileNames = new ArrayList<>(Arrays.asList("shopizer", "shipping", "payment", "messages"));
	
	
	public static void main(String args[]) {
		
		if (args.length == 0) {
			System.out.println("This software requires 2 arguments (1) absolute root path of shopizer (example /temp/app/shopizer) (2) target language iso code (example es)");
		}
		
		LanguagePackBuilder langPackApp = new LanguagePackBuilder();
		
		langPackApp.translateFromExisting(args[1], args[0]);
	    langPackApp.printInstructions(args[1], args[0]);
		
		
		//langPackApp.translateFromExisting(targetISOLanguage, shopizerRootPath);
		//langPackApp.printInstructions(targetISOLanguage, shopizerRootPath);
		
		
	}
	
	public void translateFromExisting(String shopizerRoot, String targetISOLang) {
		
		LanguageBuilder builder = new LanguageBuilder();
		
		for(String file : bundleFileNames) {
				
			String fullPath = shopizerRootPath + pathToBundlesFiles + "/" + file;
			builder.process(fullPath, "us-east-1", targetISOLanguage);
			
		}
		
	}
	
	public void printInstructions(String path, String targetLanguage) {
		StringBuilder instructions = new StringBuilder();
		instructions.append("**************************************").append("\r\n");
		instructions.append("Next steps:")
		.append("\r\n")
		.append("\r\n")
		.append("- Make sure these files are generated in " + path + pathToBundlesFiles + ":").append("\r\n");
		for(String file : bundleFileNames) {
			String fullPath = shopizerRootPath + pathToBundlesFiles + "/" + file + "_" + targetLanguage + ".properties";
			instructions.append(fullPath).append("\r\n");
		}
		instructions.append("\r\n").append("\r\n");
		instructions.append("check if translation makes sense in individual files and fix bad translations if any").append("\r\n").append("\r\n");
		
		instructions.append("- Run this query in Shopizer database").append("\r\n");
		instructions.append("insert into SALESMANAGER.LANGUAGE('LANGUAGE_ID','DATE_CREATED','DATE_MODIFIED','CODE') values (select SEQ_COUNT from SM_SEQUENCER where SEQ_NAME='LANG_SEQ_NEXT_VAL', CURDATE(), CURDATE(), '" + targetLanguage + "')").append("\r\n");
		instructions.append("\r\n").append("\r\n");
		instructions.append("Feel free to share your new language with use ! Submit a pull request (PR) to https://github.com/shopizer-ecommerce/shopizer");
		System.out.println(instructions);
	}

}
