package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map.Entry;

import javax.swing.JPanel;

import mgmt.World;
import ants.Ant;
import ants.Atom;

/**
 * This panel implements a custom {@code paintComponent} method that paints 
 * every Ant that lives in the {@link mgmt.World}.
 * 
 * @author Erick Aldaz
 *
 */
public class AntPanel extends JPanel {
	
	private static final long serialVersionUID = -4256453432134377603L;
	
	/**
	 * {@link mgmt.World} object hosting the simulation.
	 */
	private final World w;
	
	/**
	 * Basic constructor - creates a blank {@link JPanel} object and populates 
	 * its {@link mgmt.World} reference.
	 * @param w {@link mgmt.World} object hosting this simulation.
	 */
	public AntPanel( World w ) {
		super( );
		
		this.w = w;
	}
	
	/**
	 * This implementation takes the {@link ants.Ant}s that live in this simulation 
	 * and paints them, after clearing the {@link JPanel} with the superclass's base implementation.
	 */
	@Override
	protected void paintComponent( Graphics g ) {
		super.paintComponent( g );
		Graphics2D g2d = (Graphics2D)g;
		
		for( Entry<Ant, Atom> pair: w.entrySet( ) ) {
			g2d.fill( pair.getValue( ) );
		}
	}
}
