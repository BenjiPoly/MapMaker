package Model;

import View.Window;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game implements DeletableObserver {
	
    private ArrayList<GameObject> terrains = new ArrayList<GameObject>();
    
    /*
     * Pour faire les listes de map,
     * Prendre un paramètre de taille de map
     * avec celui-ci aller chercher l'ensemble des maps de cette taille
     * celles-ci se trouveraient dans un dossier du nom de leur taille
     * Utiliser les proposition trouvée ici https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
     * Ensuite faire un random sur la longeur de la liste obtenue et prendre la map qui correspond au random
     * 
     */

    private Window window;
    private int size;
    private int level = 0;

    public Game(Window window) throws IOException{
        this.window = window;
        size = window.getSize();
        
        //Creating the player
        
        Player player = new Player(0,0);
        terrains.add(player);
   
        this.buildMap();
        window.setGameObjects(terrains);
        notifyView();
    }
    
    public Player getPlayer() {
    	return (Player) terrains.get(0);
    }
    
    /*
     * Fonction qui vérifie les condition nécéssaire au déplacement d'une entité
     */
    public void moveEntity(int x, int y) {
        ((Player) terrains.get(0)).move(x, y);
        notifyView();
    }

    /*
     * Met l'affichage à jour
     */
    public void notifyView() {
        window.update();
    }

    /*
     *Supprime l'objet auquel est attaché le Deletable ps
     */
	@SuppressWarnings("unlikely-arg-type")
	@Override
    synchronized public void delete(Deletable ps) {
        terrains.remove(ps);
        notifyView();
    }
    public int getLevel() {
    	return level;
    }    
    
    private void removeBlock(int x,int y) {
    	for(GameObject elem : terrains) {
    		if(elem instanceof Block && elem.getPosX() == x && elem.getPosY()==y) {
    			((Block) elem).activate();
    			break;
    		}
    	}
    }

    public void addObject(int x, int y, char ID) {
    	removeBlock(x,y);
    	if(x>0&&x<size-1&&y>0&&y<size-1) {
    		Block block = new Block(x,y,ID);
        	block.attachDeletable(this);
        	terrains.add(block);
    	}
    	notifyView();
    }
    
    private void buildMap() throws IOException {
    	
    	for (int i = 0; i < size; i++) {
            terrains.add(new Block(i, 0,'A'));
            terrains.add(new Block(0, i,'A'));
            terrains.add(new Block(i, size - 1,'A'));
            terrains.add(new Block(size - 1, i,'A'));
        }
    	for (int i = 1;i<size-1;i++) {
    		for(int j = 1;j<size-1;j++) {
    			Block block = new Block(i,j,' ');
    			block.attachDeletable(this);
    			terrains.add(block);
    		}
    	}
    }
    
    private char getID(int x, int y) {
    	for(GameObject elem : terrains) {
    		if(elem instanceof Block && elem.getPosX()==x && elem.getPosY()==y) {
    			return elem.getID();
    		}
    	}
    	return 'A';
    }
    
    public void makeMap() {
    	FileReader fr = null;
    	BufferedReader br = null;
    	int mapNumber = 0;
    	
    	try {
			fr = new FileReader("src/mapNumber.txt");
			br = new BufferedReader(fr);
	    	mapNumber = Integer.parseInt(br.readLine()) + 1;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
	    	try {
	    		fr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	
    	try {
    		fw = new FileWriter("maps/map"+mapNumber+".txt");
    		bw = new BufferedWriter(fw);
    		
    		for(int i = 0 ;i < size ; i++) {
        		for(int j = 0;j<size;j++) {
        			bw.write(getID(j,i));
        		}
        		bw.newLine();
        	}
    		
    		fw = new FileWriter("src/mapNumber.txt", false);
    		bw = new BufferedWriter(fw);
    		bw.write(String.valueOf(mapNumber));
    		System.out.println("Map " + mapNumber + "enregistrée.");
    	}catch(IOException e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			bw.close();
    			fw.close();
    		}catch(IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
}