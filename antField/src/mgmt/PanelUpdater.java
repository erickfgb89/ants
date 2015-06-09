package mgmt;

import gui.AntPanel;

/**
 * This {@link Runnable} object will be instantiated in a new {@link Thread} to schedule 
 * updating the graphical representation of the {@link mgmt.World}.
 * 
 * @author Erick Aldaz
 *
 */
public class PanelUpdater implements Runnable {

	/**
	 * Panel to paint when updating the GUI
	 */
	private final AntPanel panel;
	
	@Override
	public void run() {
		while( true ) {
			panel.repaint( );
			
			try {
				Thread.sleep( 50 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Basic constructor. populates the {@link gui.AntPanel} reference.
	 * @param p {@link gui.AntPanel} object to paint at regular intervals.
	 */
	public PanelUpdater( AntPanel p ) {
		panel = p;
	}

}
