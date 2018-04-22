import java.io.IOException;
import java.util.Scanner;

import Controller.Keyboard;
import Model.Game;
import View.Window;

public class Main {
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	int size = -1;
    	while(size < 10 || size > 30) {
    		System.out.println("Entrer un taille de map (entre 10 et 30)");
    		size = sc.nextInt();
    	}
    	sc.close();
        Window window = new Window(size);

        Game game = new Game(window);
		try {
			Keyboard keyboard = new Keyboard(game);
			keyboard.setPlayer(game.getPlayer());
			window.setKeyListener(keyboard);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
