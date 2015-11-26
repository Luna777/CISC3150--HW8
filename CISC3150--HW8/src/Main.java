import java.util.*;
import java.io.*;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


public class Main{
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

class Server extends Thread{
//	private PipedWriter outSA;
//	private PipedReader inAS;
//	private PipedWriter outSB;
//	private PipedReader inBS;
	
	private DataOutputStream outSA;
	private DataInputStream inAS;
	private DataOutputStream outSB;
	private DataInputStream inBS;	
	
	public Server(OutputStream oSA, OutputStream oSB, 
			InputStream iAS, InputStream iBS){
		
		outSA= new DataOutputStream(oSA);
		outSB=new DataOutputStream(oSB);
		inAS=new DataInputStream(iAS);
		inBS=new DataInputStream(iBS);
	}
	public void run(){
		try{
				while(true){ 
					String x = inAS.readUTF();
					outSB.writeUTF(x);	
					
					String y = inBS.readUTF();
					outSA.writeUTF(y);		
				}
			}
			catch(Throwable ex){
				System.out.println("error S");
		}

	}

}



class ClientA extends Thread{
	private DataInputStream in;
	private DataOutputStream out;

//	private PipedWriter out;
//	private PipedReader in;
	
	public ClientA(OutputStream o,InputStream i){
		in= new DataInputStream(i);
		out=new DataOutputStream(o);
	}
	public void run(){
		try{
			
			while(true){ 
				sleep(700);
				out.writeUTF("message from A");
				System.out.println("A sent out: message from A");
				sleep(1000);
				String x = in.readUTF();
				System.out.println("A read in: "+x);

			}
		}
		catch(Throwable ex){
			System.out.println("error A");
		}

	}
	
}

class ClientB extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	
//	private PipedWriter out;
//	private PipedReader in;
	
	public ClientB(OutputStream o,InputStream i){
		in= new DataInputStream(i);
		out=new DataOutputStream(o);
	}
	public void run(){
		try{
			while(true){ 

				sleep(1000);
				String x = in.readUTF();
				System.out.println("B read in: "+x);
				
				sleep(1000);
				
				out.writeUTF("message from B");
				System.out.println("B sent out: message from B");

			}
		}
		catch(Throwable ex){
			System.out.println("error B");
		}

	}
	
}





