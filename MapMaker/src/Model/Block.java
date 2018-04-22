package Model;

import java.util.ArrayList;

public class Block extends GameObject implements Deletable, Activable {
	protected ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
	public Block(int x, int y, char ID) {
        super(x, y, ID);
    }

	@Override
	public void activate() {
		notifyDeletableObserver();
	}

	@Override
	public void attachDeletable(DeletableObserver po) {
		observers.add(po);		
	}

	@Override
	public void notifyDeletableObserver() {
		for (DeletableObserver o : observers) {
            o.delete(this);
        }
	}

}
