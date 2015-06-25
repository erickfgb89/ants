package mgmt;

import java.util.stream.Stream;

import utils.AntUtils;

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
		//Simple random ants are not complex enough to take any significant
		//amount of time to run at each step, so we'll delay each step a short time
		//to make the local client display clearer.
		AntUtils.delayedStream( 20, Stream.generate( world::lottery ) )
			.forEach( a -> AntFieldMainClass.logMethod.logAction( a.run( ) ) );
	}
	
	/**
	 * Basic constructor that populates this object's {@link mgmt.World} reference.
	 * @param w {@link mgmt.World} object hosting the simulation that this object will run.
	 */
	public WorldRunner( World w ) {
		world = w;
	}

}
