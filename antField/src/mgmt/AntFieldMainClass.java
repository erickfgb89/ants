package mgmt;

import gui.MainFrame;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import msg.Action;
import msg.ActionCreate;
import ants.Atom;
import ants.BasicAnt;

public class AntFieldMainClass {

	public enum PresentationMethod {
		GUI( "gui", "toPanel" ),
		CONSOLE( "console|stdout", "toConsole" );
		
		private final Pattern pattern;
		private final Method logTarget;
		
		private PresentationMethod( String pattern, String logMethod ) {
			this.pattern = Pattern.compile( pattern, Pattern.CASE_INSENSITIVE);
			Method tmpLogTarget = null;
			
			try {
				tmpLogTarget = Action.class.getMethod( logMethod );
			} catch( NoSuchMethodException e ) {
				System.err.println( "Log Method not found in Action class.  "+
						"Exiting with status 1, as we cannot continue without any sort of logging" );
				System.exit( 1 );
			}
			
			this.logTarget = tmpLogTarget;
		}
		
		public void logAction( Action a ) {
			try {
				logTarget.invoke( a );
			} catch ( IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e ) {
				System.err.println( "unable to log via method: " + logTarget.getName( ) );
			}
		}
		
		public static PresentationMethod parseMethod( String arg ) {
			for( PresentationMethod method: PresentationMethod.values( ) )
				if( method.pattern.matcher( arg ).matches( ) )
					return method;
			return null;
		}
	}
	
	/**
	 * Initially anticipated population of the world.  Plays a role in the initial capacity of the world.
	 */
	public static final int initialPopulationSize;
	
	/**
	 * Static variables that define the world.
	 */
	private static final int antCount, worldWidth, worldHeight;
	
	public static PresentationMethod logMethod;
	
	static {
		antCount = 200;
		worldWidth = 800;
		worldHeight = 800;
		initialPopulationSize = 200;
	}
	
	private static void finalizeAndAdd( World w, Atom a ) {
		a.getAnt( ).setParent( a );
		logMethod.logAction( new ActionCreate( a ) );
		w.put( a.getAnt( ), a );
	}
	
	/**
	 * For now, this method populates the world with a collection of {@link ants.BasicAnt}s.
	 * Future versions will populate the world dynamically based on budding and dying {@link ants.Ant}s.
	 * @param w The {@link mgmt.World} to be populated.
	 */
	private static void populateWorld( World w ) {
		Random r = new Random( );
		Iterator<Integer> ints = IntStream.generate( () -> r.nextInt( worldWidth ) ).iterator( );

		Stream.generate( BasicAnt::new )
			.limit( antCount )
			.map( ant -> new Atom( w, ant, ints.next( ), ints.next( ), 2, 2 ) )
			.forEach( atom -> finalizeAndAdd( w, atom ) );
		
	}
	
	public static void main(String[] args) {
		
		//We only expect a PresentationMethod as an argument
		logMethod = null;
		String[] arg = null;
		for( String s: args )
			if( ( arg=s.split( "=" ) ) != null
			&& arg[0].equalsIgnoreCase( "log" ) )
				logMethod = PresentationMethod.parseMethod( arg[1] );
		
		if( logMethod == null )
			logMethod = PresentationMethod.GUI;
		
		World world = new World( worldWidth, worldHeight );
		WorldRunner wr = new WorldRunner( world );
		
		populateWorld( world );
		
		new Thread( wr ).start( );
		
		if( logMethod == PresentationMethod.GUI )
			new MainFrame( world );
	}

}
