package pkmn.multi;

import java.util.Properties;

public class PkmnArenaInMove {

	/**
	 * Contains all information on the final resolution of a turn.
	 */
	
	private Properties enemy1PkmnProps;
	private Properties enemy2PkmnProps = null;
	private Properties allyPkmnProps = null;
	private Properties yourPkmnProps;
	private String[] moves;
	private boolean[] switched = new boolean[4];
	
	public PkmnArenaInMove(Properties enemyPkmnProps, Properties yourPkmnProps, String[] moves, boolean[] switchedPkmn) //1v1
	{
		this.enemy1PkmnProps = enemyPkmnProps;
		this.yourPkmnProps = yourPkmnProps;
		this.moves = moves;
		this.switched = switchedPkmn;
	}
	
	public PkmnArenaInMove(Properties enemy1PkmnProps, Properties enemy2PkmnProps, Properties allyPkmnProps, Properties yourPkmnProps, String[] moves, boolean[] switchedPkmn) //2v2
	{
		this.enemy1PkmnProps = enemy1PkmnProps;
		this.enemy2PkmnProps = enemy2PkmnProps;
		this.allyPkmnProps = allyPkmnProps;
		this.yourPkmnProps = yourPkmnProps;
		this.moves = moves;
		this.switched = switchedPkmn;
	}
	
	public boolean[] getSwitched()
	{
		return this.switched;
	}
	
	public Properties getMyProps()
	{
		return this.yourPkmnProps;
	}
	
	public Properties getEnemyProps()
	{
		return this.enemy1PkmnProps;
	}
	
	public Properties getEnemy2Props()
	{
		return this.enemy2PkmnProps;
	}
	
	public Properties getAllyProps()
	{
		return this.allyPkmnProps;
	}
	
	public String[] getMoves()
	{
		return this.moves;
	}

}
