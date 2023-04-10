package gui.utils;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 * Classe qui étends un JPanel et qui permet la création,
 * l'affichage et l'édition d'un diagramme de barres.
 */
public class Chart extends JPanel {
	
	//Attributs
    private static final long serialVersionUID = 1L;
    /**
     * Données à afficher( Hauteur des barres )
     */
	private double[] data;
	/**
	 * Données sur l'axe X
	 */
    private String[] names;
    private int padding = 25;
    /**
     * Couleur des barres
     */
    private Color barColor;

    //Requêtes
    /**
     * Renvoie un tableau de String contenant
     * les données de l'axe X 
     * @return
     */
    public String[] getNames() {
    	return names.clone();
    }
    //Commandes
    
    /**
     * Permet d'initialiser les données à afficher
     * ( Hauteur des barres )
     * @param data
     */
    public void setData(double[] data) {
        this.data = data;
    }
    
    /**
     * Permet d'initialiser les données de l'axe X
     * @param names
     */
    public void setNames(String[] names) {
        this.names = names;
    }
    
    /**
     * Permet de définir les couleurs des barres.
     * @param r
     */
    public void setBarColor(Color r) {
    	barColor = r;
    }
    
    /**
     * Dessine à l'écran le composant
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        sortDataAndNames();
        this.setBackground(new Color(30, 40, 44));
        drawBars(g);


        int width = getWidth();
        int height = getHeight();


        double maxValue = getMaxValue();


        g.setColor(new Color(119, 118, 123));
        g.drawLine(padding, padding, padding, height - padding);

        
        double step = getStep(maxValue);
        for (int i = 0; i <= maxValue; i += step) {
            int x0 = padding;
            double y0 = height - padding - (i * (height - (2 * padding))) / maxValue;
            int x1 = padding + 5;
            double y1 = y0;
            g.drawLine(x0, (int) y0, x1, (int) y1);
            g.drawString(Integer.toString(i), x0 - 22, (int) (y0 + 5));
        }
        g.drawLine(padding, height - padding, width - padding, height - padding);

        
        if (names != null) {
            int barSpacing = (int) ((double) (width - (2 * padding)) / (double) data.length);
            for (int i = 0; i < data.length; i++) {
                int x = padding + (i * barSpacing) + (barSpacing / 2);
                int y = height - (padding / 2);
                g.drawString(names[i], x - (names[i].length() * 3), y);
            }
        }
    }
    /**
     * Dessine les barres selon les données entrées
     * @param g
     */
    private void drawBars(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        double maxValue = getMaxValue();
        int barSpacing = (int) ((double) (width - (2 * padding)) / (double) data.length);
        int barWidth = (int) ((double) barSpacing * 0.8);
        for (int i = 0; i < data.length; i++) {
            int x1 = padding + (i * barSpacing) + (barSpacing - barWidth) / 2;
            int y1 = (int) (((double) (maxValue - data[i]) / (double) maxValue) *
                    (height - (2 * padding))) + padding;
            g.setColor(barColor);
            g.fillRect(x1, y1, barWidth, (int) (((double) data[i] / (double) maxValue) * (height - (2 * padding))));
            g.setColor(Color.BLACK);
            g.drawRect(x1, y1, barWidth, (int) (((double) data[i] / (double) maxValue) * (height - (2 * padding))));

        }
    }

        
    /**
     * Renvoie la valeur maximale du diagramme,
     * la valeur représentée par la barre la plus haute
     * @return
     */
    private double getMaxValue() {
        double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > maxValue) {
                maxValue = data[i];
            }
        }
        return maxValue;
    }

    /**
     * Permet de calculer le pas de l'axe Y par rapport à la 
     * valeur maximale
     * @param maxValue
     * @return
     */
    private double getStep(double maxValue) {
        double step = 1;
        if (maxValue > 10) {
            step = maxValue / 10;
        }
        return step;
    }
    /**
     * Permet le tri des valeurs et des noms ( Axe X) simmultanément
     */
    private void sortDataAndNames() {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] > data[i]) {
                    double tempData = data[i];
                    data[i] = data[j];
                    data[j] = tempData;

                    String tempName = names[i];
                    names[i] = names[j];
                    names[j] = tempName;
                }
            }
        }
    }
    
    /**
     * Renvoie la lettre la plus fréquente, le nom
     * associé à la plus haute barre
     * @return
     */
    public String getMostFrequentLetter() {
        double maxFreq = Double.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > maxFreq) {
                maxFreq = data[i];
                maxIndex = i;
            }
        }
        return names[maxIndex];
    }
}

