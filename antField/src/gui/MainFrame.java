package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import mgmt.PanelUpdater;
import mgmt.World;

/**
 * MainFrame is a {@link JFrame} object that paints the state of the {@link mgmt.World} 
 * at regular intervals.
 * 
 * @author Erick Aldaz
 *
 */
public class MainFrame {
	
	/**
	 * Static variables that define the world.
	 */
	private static final int worldWidth, worldHeight;
	
	static {
		worldWidth = 800;
		worldHeight = 800;
	}
	
	/**
	 * Constructs the frame that will paint the {@link mgmt.World} with basic, static settings.
	 */
	public MainFrame( World w ) {
		JFrame f = new JFrame( "Ants" );
		World world = w;
		AntPanel p = new AntPanel( world );
		PanelUpdater update = new PanelUpdater( p );
		
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		f.add( p );
		f.setPreferredSize( new Dimension( worldWidth, worldHeight ) );
		f.pack();
		f.setLocationRelativeTo( null );
		f.setVisible( true );
		
		new Thread( update ).start( );
	}

	public static void main( String args[] ) {
		new MainFrame( null );
	}
}
