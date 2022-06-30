package com.shopizer.tools.language;

import com.shopizer.tools.language.process.JsonLanguageBuilder;

public class TranslateJsonLanguageBuilder {
	
	
	public static final String targetISOLanguage = "fr";

	private static final String filePath = "/Users/username/Documents/dev/workspace/shopizer/shop/shopizer/packages/theme/lang/en.json";

	
	public static void main(String args[]) {
		
		if (args.length == 0) {
			System.out.println("This software requires 2 arguments (1) absolute root path of json file (example /temp/app/shopizer) (2) target language iso code (example es)");
		}
		
		TranslateJsonLanguageBuilder langPackApp = new TranslateJsonLanguageBuilder();
		
		langPackApp.translateFromExisting(args[1], args[0]);
	    langPackApp.printInstructions(args[1], args[0]);
		
		
		//langPackApp.translateFromExisting(filePath, targetISOLanguage);
		//langPackApp.printInstructions(filePath, targetISOLanguage);
		
		
	}
	
	public void translateFromExisting(String path, String targetISOLang) {
		
		JsonLanguageBuilder builder = new JsonLanguageBuilder();
		builder.process(path, "us-east-1", targetISOLanguage);

		
	}
	
	public void printInstructions(String path, String targetLanguage) {
		StringBuilder instructions = new StringBuilder();
		instructions.append("**************************************").append("\r\n");
		instructions.append("Next steps:")
		.append("\r\n")
		.append("\r\n")
		.append("- Make sure the file is generated in " + path + " and has .json extension").append("\r\n");

		instructions.append(path).append("\r\n");

		instructions.append("\r\n").append("\r\n");
		instructions.append("check if translation makes sense in individual files and fix bad translations if any").append("\r\n").append("\r\n");
		
		instructions.append("- Run this query in Shopizer database").append("\r\n");
		instructions.append("insert into SALESMANAGER.LANGUAGE('LANGUAGE_ID','DATE_CREATED','DATE_MODIFIED','CODE') values (select SEQ_COUNT from SM_SEQUENCER where SEQ_NAME='LANG_SEQ_NEXT_VAL', CURDATE(), CURDATE(), '" + targetLanguage + "')").append("\r\n");
		instructions.append("\r\n").append("\r\n");
		instructions.append("Feel free to share your new language with use ! Submit a pull request (PR) to https://github.com/shopizer-ecommerce/shopizer");
		System.out.println(instructions);
	}

}
