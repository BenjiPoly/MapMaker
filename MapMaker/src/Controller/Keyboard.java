package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import Model.Game;
import Model.Player;

public class Keyboard implements KeyListener {
    private Game game;
    private Player player;

    public Keyboard(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        switch (key) {
        case KeyEvent.VK_RIGHT:
            game.moveEntity(1, 0);
            break;
        case KeyEvent.VK_LEFT:
            game.moveEntity(-1, 0);
            break;
        case KeyEvent.VK_DOWN:
            game.moveEntity(0, 1);
            break;
        case KeyEvent.VK_UP:
            game.moveEntity(0, -1);
            break;
        case KeyEvent.VK_A:
        	game.addObject(player.getPosX(), player.getPosY(), 'A');
        	break;
        case KeyEvent.VK_SPACE:
        	game.addObject(player.getPosX(), player.getPosY(), ' ');
        	break;
        case KeyEvent.VK_ENTER:
        	game.makeMap();
        	break;
        case KeyEvent.VK_B:
        	game.addObject(player.getPosX(), player.getPosY(), 'B');
        	break;
        case KeyEvent.VK_H:
        	game.addObject(player.getPosX(), player.getPosY(), 'H');
        	break;
        case KeyEvent.VK_D:
        	game.addObject(player.getPosX(), player.getPosY(), 'D');
        	break;
        case KeyEvent.VK_P:
        	game.addObject(player.getPosX(), player.getPosY(), 'P');
        	break;
        case KeyEvent.VK_C:
        	game.addObject(player.getPosX(), player.getPosY(), 'C');
        	break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    }
}
