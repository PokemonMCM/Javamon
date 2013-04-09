package pkmn.multi;

import java.net.Socket;
import java.util.Properties;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PkmnClient {

	/**
	 * The client-side program that connects to the server, takes user input, and processes
	 * the responses from the server.
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private void processTurnResponse(ObjectInputStream movesIn) throws ClassNotFoundException, IOException
	{
		PkmnArenaInMove nextMove = (PkmnArenaInMove) movesIn.readObject();
	}

}
