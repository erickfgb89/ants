package mgmt;

import gui.MainFrame;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ants.Ant;
import ants.Atom;
import ants.BasicAnt;

public class AntFieldMainClass {

	//TODO allow diffenet methods for spitting out data
	private enum PresentationMethod {
		GUI,
		FILE,
		CONSOLE
	}
	
	static char[] arr = "abcdefghijklmnopqrstuvwxyz".toCharArray( );
	static Random r = new Random();
	
	/**
	 * Static variables that define the world.
	 */
	private static final int antCount, worldWidth, worldHeight;
	
	static {
		antCount = 200;
		worldWidth = 800;
		worldHeight = 800;
	}
	
	/**
	 * For now, this method populates the world with a collection of {@link ants.BasicAnt}s.
	 * Future versions will populate the world dynamically based on budding and dying {@link ants.Ant}s.
	 * @param w The {@link mgmt.World} to be populated.
	 */
	private static void populateWorld( World w ) {
		Random r = new Random( );
		Ant ant;
		Atom atom;
		for( int c = 0; c<antCount; c++ ) {
			ant = new BasicAnt( );
			atom = new Atom( w, ant, 
					r.nextInt( worldWidth ), r.nextInt( worldHeight ), 2, 2 );
			ant.setParent( atom );
			w.add( atom );
		}
		
		Iterator<Integer> ints = IntStream.generate( () -> r.nextInt( worldWidth ) ).iterator( );
		
		Stream.generate( BasicAnt::new )
			.limit( antCount )//TODO
			.forEach( antb -> new Atom( w, antb, ints.next( ), ints.next( ), 2, 2 ) );
		
	}
	
	public static void main(String[] args) {
		
		//Stream<Character> stream = Stream.generate( AntFieldMainClass::lottery );
		
		//stream.limit(100).forEach(System.out::print);
		
		World world = new World( worldWidth, worldHeight );
		WorldRunner wr = new WorldRunner( world );
		
		populateWorld( world );
		
		new Thread( wr ).start( );
		
		new MainFrame( world );
	}

}
