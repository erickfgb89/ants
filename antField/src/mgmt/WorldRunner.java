package mgmt;

import java.util.stream.Stream;

/**
 * This object will be instantiated in a {@link Thread} that will govern the 
 * scheduling of the simulation.
 * 
 * @author Erick Aldaz
 *
 */
public class WorldRunner implements Runnable {

	/**
	 * {@code mgmt.World} object that houses the {@code ants.Atom}s being scheduled.
	 */
	private final World world;
	
	/**
	 * Signals a tick of the {@link mgmt.World} clock at regular intervals, ideally in another Thread.
	 */
	@Override
	public void run() {
		Stream.generate( world::lottery ).forEach( a -> a.getAnt( ).run( ) );
		/*
		while( true ) {
			world.tick( );
			
			try {
				Thread.sleep( 1 );
			} catch( InterruptedException e ) {
				e.printStackTrace( );
			}
		}
		*/
	}
	
	/**
	 * Basic constructor that populates this object's {@link mgmt.World} reference.
	 * @param w {@link mgmt.World} object hosting the simulation that this object will run.
	 */
	public WorldRunner( World w ) {
		world = w;
	}

}
