//CISC3150
//Xin Guan
//11/25/15
//
//Main contains:
// a String array for random message
// all the pipes
// a ClientA, a ClientB and a Server
//
// pipes are connected like:
//	  ClientA <=====>Server<=====> ClientB
// (no talking between ClientA and ClientB)


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


public class Main{
	//10 messages for output randomly
	final static String[] msgs = {"Blah!", "Oh no!", "How's it going?", "Don't repeat me!", 
			"I said don't repeat me!", "Hello, who are you?", "Anybody home?", 
			"Hello Kitty~", "Good Job.", "How about this?"}; 
	public static void main(String[] args) throws IOException{
		
		//all the pipes to connect ClientA to Server and ClientB to Server
		//A->S
		PipedOutputStream outputAS= new PipedOutputStream();
		PipedInputStream inputAS = new PipedInputStream(outputAS);
		//B->S
		PipedOutputStream outputBS= new PipedOutputStream();
		PipedInputStream inputBS = new PipedInputStream(outputBS);
		//S->A
		PipedOutputStream outputSA= new PipedOutputStream();
		PipedInputStream inputSA = new PipedInputStream(outputSA);
		//S->B
		PipedOutputStream outputSB= new PipedOutputStream();
		PipedInputStream inputSB = new PipedInputStream(outputSB);
		
		//ClientA and ClientB can only talk to Server
		ClientA ca = new ClientA(outputAS, inputSA);
		ClientB cb = new ClientB(outputBS, inputSB);
		Server server = new Server(outputSA, outputSB, inputAS, inputBS);
		
		//start all the threads
		ca.start();
		cb.start();
		server.start();
	}
}












