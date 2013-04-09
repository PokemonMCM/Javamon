package pkmn.multi;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class PkmnArena extends Thread {

	/**
	 * Acts as the middle-man between two clients that calculates all the
	 * action and damage that occurs. It then sends that info off to each of
	 * the clients.
	 * @author Mattori
	 */
	
	private volatile static PkmnArenaOutMove pOneMove;
	private volatile static PkmnArenaOutMove pTwoMove;
	private volatile static PkmnArenaInMove pOneResponseMove;
	private volatile static PkmnArenaInMove pTwoResponseMove;
	private volatile static ObjectInputStream pOneIn;
	private volatile static ObjectInputStream pTwoIn;
	private volatile static Socket pOne;
	private volatile static Socket pTwo;
	private volatile static boolean winner = false;
	private static byte pOneCurrentPkmn = 0;
	private static byte pTwoCurrentPkmn = 0;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		PkmnArena pOneListener = new PkmnArena()
		{
			public void run()
			{
				try
				{
					pOneMove = (PkmnArenaOutMove) pOneIn.readObject();
				}
				catch (ClassNotFoundException | IOException e)
				{
					e.printStackTrace();
				}
				
				return;
			}
		};
		
		PkmnArena pTwoListener = new PkmnArena()
		{
			public void run()
			{
				try
				{
					pTwoMove = (PkmnArenaOutMove) pTwoIn.readObject();
				}
				catch (ClassNotFoundException | IOException e)
				{
					e.printStackTrace();
				}
				
				return;
			}
		};
		
		
		
		BufferedInputStream pOneBuffIn;
		BufferedInputStream pTwoBuffIn;
		PrintWriter pOneWrite;
		PrintWriter pTwoWrite;
		ObjectOutputStream pOneOut;
		ObjectOutputStream pTwoOut;
		Properties[] pOnePkmn = new Properties[6];
		Properties[] pTwoPkmn = new Properties[6];
		
		ServerSocket connectingServSock = new ServerSocket(0);
		System.out.println("Connection info:");
		try
		{
			System.out.println("IP Address -- " + InetAddress.getLocalHost().toString().split("/")[1]);
		}
		catch(UnknownHostException e)
		{
			System.out.println("Error: cannot determine host information, check your internet.");
			System.exit(1);
		}
		System.out.println("Socket port -- " + connectingServSock.getLocalPort());
		
		pOne = connectingServSock.accept();
		pTwo = connectingServSock.accept();
		
		pOneWrite = new PrintWriter(pOne.getOutputStream());
		pTwoWrite = new PrintWriter(pTwo.getOutputStream());
		pOneBuffIn = new BufferedInputStream(pOne.getInputStream());
		pTwoBuffIn = new BufferedInputStream(pTwo.getInputStream());
		pOneIn = new ObjectInputStream(new BufferedInputStream(pOne.getInputStream()));
		pTwoIn = new ObjectInputStream(new BufferedInputStream(pTwo.getInputStream()));
		pOneOut = new ObjectOutputStream(new BufferedOutputStream(pOne.getOutputStream()));
		pTwoOut = new ObjectOutputStream(new BufferedOutputStream(pTwo.getOutputStream()));
		
		connectingServSock.close();
		
		for(byte i = 0; i<6; i++)
			pOnePkmn[i].load(pOneBuffIn);
		
		for(byte i = 0; i<6; i++)
			pTwoPkmn[i].load(pTwoBuffIn);
		
		pOneWrite.println("starting");
		pTwoWrite.println("starting");
		
		pOneOut.writeObject(pTwoPkmn);
		pTwoOut.writeObject(pOnePkmn);
		
		while(!winner)
		{
			pOneWrite.println("waiting for move");
			pTwoWrite.println("waiting for move");
			pOneListener.start();
			pTwoListener.start();
			
			while(pOneListener.isAlive()&&pTwoListener.isAlive())
			{
				synchronized(Thread.currentThread())
				{
					Thread.currentThread().wait(400);
				}
			}
			
			pOneWrite.println("calculating");
			pTwoWrite.println("calculating");
			calculateTurn(pOnePkmn[pOneCurrentPkmn], pTwoPkmn[pTwoCurrentPkmn]);
			
			pOneWrite.println("finished calc");
			pTwoWrite.println("finished calc");
			pOneOut.writeObject(pOneResponseMove);
			pTwoOut.writeObject(pTwoResponseMove);
		}
		
		//end
		pOneWrite.close();
		pTwoWrite.close();
		pOneIn.close();
		pTwoIn.close();
		pOneBuffIn.close();
		pTwoBuffIn.close();
		pOneOut.close();
		pTwoOut.close();
	}
	
	private static void calculateTurn(Properties pOnePkmnProps, Properties pTwoPkmnProps)
	{
		//MEET'S CODE HERE PLZ
		//use pOneMove and pTwoMove, which are PkmnArenaOutMove instances, to get info on what is happening
		//make sure to construct PkmnArenaMoveIn's for both players (the variables are pOneResponseMove and pTwoResponseMove)
		//set winner to true when the all Pkmn are fainted or someone forfeits
	}

}
