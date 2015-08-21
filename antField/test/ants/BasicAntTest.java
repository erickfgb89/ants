/**
 * 
 */
package ants;

import static org.junit.Assert.*;

import java.util.ArrayList;

import msg.Action;
import msg.ActionMove;

import org.junit.Test;

/**
 * @author Erick Aldaz
 *
 */
public class BasicAntTest {

	/**
	 * Test method for {@link ants.BasicAnt#run()}.
	 */
	@Test
	public final void testRun() {
		BasicAnt a = new BasicAnt( new Atom(null, 0, 0, 0, 0 ){
			private static final long serialVersionUID = 1L;

			@Override
			public Action move( ActionMove action ) {
				
				assertTrue( "magnitude should be <=5 and dd should be <=Math.PI:\t" +
						"magnitude: "+action.magnitude+", dd: "+action.direction, 
						action.magnitude <= 5 && action.direction <= Math.PI);
				
				return null;
			}
		});
		
		for( int i = 0; i<500; i++ ) {
			a.run( );
		}
			
	}

	/**
	 * Test method for {@link ants.Ant#setParent(ants.Atom)}.
	 */
	@Test
	public final void testSetParent() {
		BasicAnt a = new BasicAnt( );
		ArrayList<Boolean> returns = new ArrayList<Boolean>( 150 );
		boolean finalResult;
		
		for( int i = 0; i<100; i++ )
			returns.add( a.setParent( null ) );
		
		finalResult = !returns.contains( Boolean.TRUE ) 
				&& a.setParent( new Atom( null, 0, 0, 0, 0 ) );
		
		assertTrue( "Failed: many calls to setParent with a null argument "
				+ "should all fail, but a non-null argument should still succeed.", finalResult );
	}

}
