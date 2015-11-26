import java.util.*;
import java.io.*;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


public class Main{
	//10 messages
	final static String[] msgs = {"Blah!", "Oh no!", "How's it going?", "Don't repeat me!", 
			"I said don't repeat me!", "Hello, who are you?", "Anybody home?", 
			"Hello Kitty~", "Good Job.", "How about this?"}; 
	public static void main(String[] args) throws IOException{
		
		

//		PipedWriter outputAS = new PipedWriter();
//		PipedReader inputAS = new PipedReader(outputAS);
//		
//		PipedWriter outputBS = new PipedWriter();
//		PipedReader inputBS = new PipedReader(outputBS);
//		
//		PipedWriter outputSA = new PipedWriter();
//		PipedReader inputSA = new PipedReader(outputSA);
//		
//		PipedWriter outputSB = new PipedWriter();
//		PipedReader inputSB = new PipedReader(outputSB);
		
		PipedOutputStream outputAS= new PipedOutputStream();
		PipedInputStream inputAS = new PipedInputStream(outputAS);
		
		PipedOutputStream outputBS= new PipedOutputStream();
		PipedInputStream inputBS = new PipedInputStream(outputBS);
		
		PipedOutputStream outputSA= new PipedOutputStream();
		PipedInputStream inputSA = new PipedInputStream(outputSA);
		
		PipedOutputStream outputSB= new PipedOutputStream();
		PipedInputStream inputSB = new PipedInputStream(outputSB);
		
		
		
		ClientA ca = new ClientA(outputAS, inputSA);
		ClientB cb = new ClientB(outputBS, inputSB);
		Server server = new Server(outputSA, outputSB, inputAS, inputBS);

		
		
		ca.start();
		cb.start();
		server.start();
	}
}












