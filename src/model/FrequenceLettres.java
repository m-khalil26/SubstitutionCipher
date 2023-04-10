package model;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant le calcul des fréquences d'apparition des charactères d'un texte.
 *
 */
public class FrequenceLettres {
	
	/**
	 * Permet le calcul des fréquences d'apparition des lettres d'une String,
	 * selon un Alphabet défini, et un mode(Majuscules,Minuscules,Mixe).
	 * Et renvoie une Map qui définis ces associations.(Lettre,Fréquence)
	 * @param texte
	 * @param a
	 * @param mode
	 * @return
	 */
    public static Map<Character, Double> calculerFrequence(String texte,Alphabet a,String mode) {
        Map<Character, Integer> frequences = new HashMap<Character, Integer>();
        Map<Character, Double> frequences_d = new HashMap<Character, Double>();
        char[] alp = a.alphabet();
        for(char c : alp) {
        	frequences.put(c,0);
        }
        
        texte = removeAccents(texte);
        
        for (int i = 0; i < texte.length(); i++) {
        	char lettre;
        	if(mode ==Attack.MINUSCULES) {
        		lettre = Character.toLowerCase(texte.charAt(i));
        	}else if (mode == Attack.MAJUSCULES){
        		lettre = Character.toUpperCase(texte.charAt(i));
        	}else {
        		lettre = texte.charAt(i);
        	}
        	
            if (frequences.containsKey(lettre)) {
           		frequences.put(lettre, frequences.get(lettre) + 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : frequences.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            double freq = (double)count / texte.length();
            frequences_d.put(c, freq);
        }
        return frequences_d;
    }
    
    
    
    
    // ***** OUTILS *****
    /**
     * Permet de supprimer les accents d'une chaine de caractères.
     * @param s
     * @return
     */
    private static String removeAccents(String s) {
		assert s != null;
		assert !s.isEmpty();

		// décompose les caractères accentués (ex: é -> e + ')
		String stringNormalized = Normalizer.normalize(s, Normalizer.Form.NFD);

		// Supprime les caractères accentués (\p{M})
		return stringNormalized.replaceAll("\\p{M}", "");
	}
    
}