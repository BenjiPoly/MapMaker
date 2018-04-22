package View;

import Model.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Map extends JPanel {
    private ArrayList<GameObject> terrains = null;
    
    
    private BufferedImage font;
    private BufferedImage breakableBlock;
    private BufferedImage tintedRock;
    private BufferedImage spike;

    public Map() {
        this.setFocusable(true);//Autorise la map à être au premier plan
        this.requestFocusInWindow(); //Demande à la map d'être au premier plan
        try { //Le try catch ici permet d'éviter les erreurs
        	this.font = ImageIO.read(getClass().getResourceAsStream("/images/backGround.jpg")); //Charge l'image dans la mémoire du jeu
        	this.breakableBlock = ImageIO.read(getClass().getResourceAsStream("/images/breakableBlock.png"));
        	this.tintedRock = ImageIO.read(getClass().getResourceAsStream("/images/tintedRock.png"));
        	this.spike = ImageIO.read(getClass().getResourceAsStream("/images/spike.png"));
        } catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <4; j++) {
                int x = i;
                int y = j;
                g.drawImage(font, x*256, y*256,null); //on dessine une image sur toutes les cases de sol, on ajoute plus bas les autres textures
            }
        }

        for (GameObject terrain : this.terrains) { //Dessine tout les composant du terrain
            int x = terrain.getPosX();
            int y = terrain.getPosY();
            char ID = terrain.getID();
            switch(ID) {
            case 'A':
            	g.setColor(Color.BLACK);
                g.drawRect(x * 32, y * 32, 31, 31);
            	g.setColor(Color.DARK_GRAY);
            	g.fillRect(x * 32, y * 32, 31, 31);
            	break;
            case 'B':
            	g.drawImage(breakableBlock, x*32, y*32,null);
            	break;
            case 'W':
            	g.setColor(Color.BLUE);
            	g.fillRect(x * 32, y * 32, 32, 32);
            	break;
            case 'H':
            	g.setColor(Color.BLACK);
            	g.fillRect(x*32, y*32, 32, 32);
            	break;
            case 'D':
            	g.setColor(Color.WHITE);
            	g.fillRect(x*32, y*32+28, 32, 4);
            	break;
            case 'P':
            	g.drawImage(spike, x*32, y*32, null);
            	break;
            case 'T':
            	g.drawImage(tintedRock, x*32, y*32,null);
            	break;
            case 'C':
            	g.setColor(Color.ORANGE);
            	g.fillRect(x*32+4, y*32+4, 24, 24);
            	break;
            }
        }
        int x = terrains.get(0).getPosX();
        int y = terrains.get(0).getPosY();
        g.setColor(Color.RED);
        g.drawRect(x*32, y*32, 32, 32);
    }
        

        
        
    public void setTerrain(ArrayList<GameObject> terrain) { //Permet de modifier le terrain de la map depuis l'extérieur de la classe
        this.terrains = terrain;
    }

    public void redraw() { //Redessine la map
        this.repaint();
    }
}
