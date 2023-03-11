package gui;

import util.Contract;

public class Formatter {
	
	private String seed;
	private String key;
	private String result;
	private int indent;
	
	public Formatter() {
		seed =null;
		key = null;
		result = null;
		indent = 0;
	}
	
	public void setSeed(String seed) {
		this.seed = seed;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public void setResult(String result) {
		this.result=result;
	}
	
	public void setScreenSize(int n) {
		indent =n;
	}
	
	
	public String getFormated() {
		Contract.checkCondition(seed != null);
		Contract.checkCondition(key != null);
		Contract.checkCondition(result!= null);
		Contract.checkCondition(indent >=1 );
		
		return addIndent("-------Nouvelle opération-------"
				+ "\nClé utilisée: "+seed+ '\n'
				+"Tableau de permutations utilisé: "+key+'\n'
				+"Résultat: "+ result+"\n\n\n\n" , indent);
	}
	
	private static String addIndent(String input, int indent) {
	    if (input == null || input.isEmpty() || indent < 1) {
	        return input;
	    }

	    StringBuilder sb = new StringBuilder(input);
	    int count = 0;

	    for (int i = 0; i < sb.length(); i++) {
	        if (sb.charAt(i) != '\n') {
	            count++;
	        }
	        if (sb.charAt(i) == '\n') {
	            count=0;
	        }

	        if (count == indent) {
	            sb.insert(i + 1, '\n');
	            count = 0;
	        }
	    }

	    return sb.toString();
	}
	
}
	
	
	

