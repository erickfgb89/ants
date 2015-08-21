/**
 * 
 */
package ants;

import static org.junit.Assert.assertEquals;
import mgmt.World;
import msg.ActionMove;

import org.junit.Test;

/**
 * This test ensures that movement wraps around the world from the left edge to the right, bottom to top, etc.
 * 
 * @author Erick Aldaz
 *
 */
public class AntMovementTest {

	private World w;
	private Atom a;
	
	private void reset( ) {
		w = new World(10, 10);
		a = new Atom( w,2,2,1,1 );
	}
	
	@Test
	public final void test() {
		//roll to the right side
		reset( );
		a.move( new ActionMove( a.getAnt( ), 4, Math.PI ) );
		assertEquals( "Atom should have rolled to the right-hand side of the World.\t"
				+ "x: "+a.x, 8, a.x, 0.1);
		
		//roll to the left side
		reset( );
		a.move( new ActionMove( a.getAnt( ), 4, 0 ) );
		a.move( new ActionMove( a.getAnt( ), 4, 0 ) );
		assertEquals( "Atom should have rolled to the left-hand side of the World.\t"
				+ "x: "+a.x, 0, a.x, 0.1);
		
		//roll to bottom
		reset( );
		a.move( new ActionMove( a.getAnt( ), 4, -Math.PI/2 ) );
		assertEquals( "Atom should have rolled to the bottom edge of the World.\t"
				+ "y: "+a.y, 8, a.y, 0.1);
		
		//roll to top
		reset( );
		a.move( new ActionMove( a.getAnt( ), 4, Math.PI/2 ) );
		a.move( new ActionMove( a.getAnt( ), 4, 0 ) );
		assertEquals( "Atom should have rolled to the top edge of the World.\t"
				+ "y: "+a.y, 0, a.y, 0.1);
		
	}

}
