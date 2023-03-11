package gui;


//ORDONNER LES BARRES

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Chart extends JPanel {
    private static final long serialVersionUID = 1L;
	private int[] data;
    private String[] names;
    private int padding = 25;

    public void setData(int[] data) {
        this.data = data;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(30, 40, 44));
        // Dessiner les barres
        drawBars(g);

        // Récupérer la largeur et la hauteur du panneau
        int width = getWidth();
        int height = getHeight();

        // Récupérer le maximum des données
        int maxValue = getMaxValue();

        // Dessiner l'axe des ordonnées
        g.setColor(new Color(119, 118, 123));
        g.drawLine(padding, padding, padding, height - padding);

        // Dessiner les marques de l'axe des ordonnées
        int step = getStep(maxValue);
        for (int i = 0; i <= maxValue; i += step) {
            int x0 = padding;
            int y0 = height - padding - (i * (height - (2 * padding))) / maxValue;
            int x1 = padding + 5;
            int y1 = y0;
            g.drawLine(x0, y0, x1, y1);
            g.drawString(Integer.toString(i), x0 - 22, y0 + 5);
        }

        // Dessiner l'axe des abscisses
        g.drawLine(padding, height - padding, width - padding, height - padding);

        // Dessiner les noms des données sur l'axe des abscisses
        if (names != null) {
            int barSpacing = (int) ((double) (width - (2 * padding)) / (double) data.length);
            for (int i = 0; i < data.length; i++) {
                int x = padding + (i * barSpacing) + (barSpacing / 2);
                int y = height - (padding / 2);
                g.drawString(names[i], x - (names[i].length() * 3), y);
            }
        }
    }

    private void drawBars(Graphics g) {
        // Récupérer la largeur et la hauteur du panneau
        int width = getWidth();
        int height = getHeight();

        // Récupérer le maximum des données
        int maxValue = getMaxValue();

        // Calculer l'espacement entre chaque barre
        int barSpacing = (int) ((double) (width - (2 * padding)) / (double) data.length);

        // Déterminer la largeur de chaque barre en fonction de l'espacement et de la largeur du panneau
        int barWidth = (int) ((double) barSpacing * 0.8);

        // Dessiner chaque barre en parcourant les données
        for (int i = 0; i < data.length; i++) {
            // Calculer les coordonnées de la barre
            int x1 = padding + (i * barSpacing) + (barSpacing - barWidth) / 2;
            int y1 = (int) (((double) (maxValue - data[i]) / (double) maxValue) *
                    (height - (2 * padding))) + padding;

            // Dessiner la barre
            g.setColor(Color.BLUE);
            g.fillRect(x1, y1, barWidth, (int) (((double) data[i] / (double) maxValue) * (height - (2 * padding))));
            g.setColor(Color.BLACK);
            g.drawRect(x1, y1, barWidth, (int) (((double) data[i] / (double) maxValue) * (height - (2 * padding))));

        }
    }

    private int getMaxValue() {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > maxValue) {
                maxValue = data[i];
            }
        }
        return maxValue;
    }

    private int getStep(int maxValue) {
        int step = 1;
        if (maxValue > 10) {
            step = maxValue / 10;
        }
        return step;
    }
}

