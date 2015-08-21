package utils;

import java.util.stream.Stream;

public final class AntUtils {

	private static <T> T delayReturn( T obj, int delay ) {
		try {
			Thread.sleep( 0, delay );
		} catch (InterruptedException e) {
			System.err.println( "interrupted during sleep. killing thread" );
		}
		
		return obj;
	}
	
	/**
	 * Attaches a static delay to the iteration of a {@code Stream}
	 * @param delay Time, in ns, to wait between returning each subsequent element in the Stream argument
	 * @param stream Stream object to delay
	 * @return Delayed Stream object
	 */
	public static <T> Stream<T> delayedStream( int delay, Stream<T> stream ) {
		return stream.map( x -> delayReturn( x, delay ) );
	}
	
}
