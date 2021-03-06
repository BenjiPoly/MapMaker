package View;

import Model.GameObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
	private JPanel windowPanel;
    private Map map = new Map();
    private int size;
    private String gameName = "Splendid Game";

    public Window(int size) { // Ajout d'un param�tre permetant de d�finir la taille de la carte
    	this.size = size;
    	
    	JFrame window = new JFrame(gameName);
		this.windowPanel = new JPanel();
		this.windowPanel.setLayout(new BoxLayout(this.windowPanel, BoxLayout.Y_AXIS));
		this.map.setPreferredSize(new Dimension(size, size));
		this.windowPanel.add(this.map);
		
        //Cr�e la fen�tre � proprement parl�
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Quand on clique sur la croix, �a quitte
        window.setBounds(0, 0, (size)*32, (size+1)*32); //Adapte la taille de la fen�tre � la taille de la carte
        window.getContentPane().setBackground(Color.GRAY);//La couleur de fond de la fen�tre est grise
        window.getContentPane().add(this.windowPanel);//On met la carte dans la fen�tre
        window.setLocationRelativeTo(null);//On place la fen�tre au centre de l'image
        window.setResizable(false);//On emp�che de redimensionner la fen�tre
        window.setVisible(true);//On rend la fen�tre visible
    }

    public void setGameObjects(ArrayList<GameObject> objects) { //Fonction qui ajoute tout le terrain � la carte
        this.map.setTerrain(objects);
        this.map.redraw();
    }

    public void update() {	//Met la carte et l'inventaire � jour
        this.windowPanel.repaint();
       // this.inventory.redraw();
    }
    
//    public void updateInventory() {
//    	this.inventory.redraw();
//    }

    public void setKeyListener(KeyListener keyboard) { //Ajoute le lecteur de clavier � la fen�tre
        this.map.addKeyListener(keyboard);
    }
    
    public int getSize() { //permet aux autre classe d'obtenir la taille de la carte
    	return size; 
    }
}
