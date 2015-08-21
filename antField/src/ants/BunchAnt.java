package ants;

import msg.Action;

public class BunchAnt extends Ant {

	/**
	 * This implementation looks around in concentric circles, within a 
	 * hard-coded radius.  It returns the nearest Atom if one exists, or 
	 * null if one does not exist within its search radius.
	 * 
	 * @return nearest neighbor, if one exists within the
	 * range of vision of this {@code Ant}
	 */
	private Atom findNeighbor( ) {
		this.getParent();
		return null;
	}
	
	@Override
	public Action run() {
		return null;
	}

}
