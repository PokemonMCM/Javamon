package pkmn.multi;

public class PkmnArenaOutMove {

	/**
	 * Contains all the information on any moves made by both clients.
	 */
	
	private byte moveCat = 0; //1 = fight, 2 = items, 3 = switch, 4 = forfeit
	private byte attackNum = 0;
	private byte target = 0; //1 = enemy 1, 2 = enemy 2, 3 = ally, 4 = self
	private String item = null; //name of item to use
	private String switchPkmn = null; //name of Pokemon to switch to
	
	public PkmnArenaOutMove(byte moveCat, byte attackNum, byte target) //if attacking
	{
		this.moveCat = moveCat;
		this.attackNum = attackNum;
		this.target = target;
	}
	
	public PkmnArenaOutMove(byte moveCat, String item, byte target) //if items
	{
		this.moveCat = moveCat;
		this.item = item;
		this.target = target;
	}
	
	public PkmnArenaOutMove(byte moveCat, String switchPkmn)
	{
		this.moveCat = moveCat;
		this.switchPkmn = switchPkmn;
	}
	
	public PkmnArenaOutMove(byte moveCat)
	{
		this.moveCat = moveCat;
	}
	
	public int getMoveCat()
	{
		return this.moveCat;
	}
	
	public int getTarget()
	{
		return this.target;
	}
	
	public int getAttackNum()
	{
		return this.attackNum;
	}
	
	public String getItem()
	{
		return this.item;
	}
	
	public String getSwitchPkmn()
	{
		return this.switchPkmn;
	}

}
