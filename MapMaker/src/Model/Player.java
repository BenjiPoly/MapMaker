package Model;


public class Player extends GameObject{
    
    public Player(int x, int y) {
        super(x, y, '&');
    }
    
    public void move(int x, int y) {
		this.setPosX(this.getPosX()+x);
		this.setPosY(this.getPosY()+y);
	}
}
