import java.util.*;
import java.io.*;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;


public class Main{
	public static void main(String[] args) throws IOException{
		
//		PipedOutputStream outputAS= new PipedOutputStream();
//		PipedInputStream inputAS = new PipedInputStream(outputAS);
//		
//		PipedOutputStream outputBS= new PipedOutputStream();
//		PipedInputStream inputBS = new PipedInputStream(outputBS);
		PipedWriter outputAS = new PipedWriter();
		PipedReader inputAS = new PipedReader(outputAS);
		
		PipedWriter outputBS = new PipedWriter();
		PipedReader inputBS = new PipedReader(outputBS);
		
		PipedWriter outputSA = new PipedWriter();
		PipedReader inputSA = new PipedReader(outputSA);
		
		PipedWriter outputSB = new PipedWriter();
		PipedReader inputSB = new PipedReader(outputSB);
		
		ClientA ca = new ClientA(outputAS, inputSA);
		ClientB cb = new ClientB(outputBS, inputSB);
		Server2 server = new Server2(outputSA, outputSB, inputAS, inputBS);
//		Server serverAB = new Server(outputSB,inputAS);
//		Server serverBA = new Server(outputSA,inputBS);
		
		//System.out.println("11111");
		
		
		ca.start();
		cb.start();
		server.start();
//		serverAB.start();
//		serverBA.start();
		//System.out.println("22222");
	}
}

class Server2 extends Thread{
	private PipedWriter outSA;
	private PipedReader inAS;
	private PipedWriter outSB;
	private PipedReader inBS;
	
	public Server2(PipedWriter oSA, PipedWriter oSB, PipedReader iAS, PipedReader iBS){
		
		outSA=oSA;
		outSB=oSB;
		inAS=iAS;
		inBS=iBS;
	}
	public void run(){
		try{
					
					while(true){ 
						
						int x = inAS.read();
						//sleep(1000);
						outSB.write(x);	
						
						int y = inBS.read();
						outSA.write(y);
						
					}
				}
				catch(Throwable ex){
					System.out.println("error S");
				}

	}
	
	
	
}


class Server extends Thread{
//	private DataInputStream in;
//	private DataOutputStream out;
	
//	private InputStream in;
//	private OutputStream out;
	
	private PipedWriter out;
	private PipedReader in;
	
	public Server(PipedWriter o,PipedReader i){
		in=i;
		out=o;
	}
	
//	public Server(InputStream is, OutputStream os)
//	{ 
////		in = new DataInputStream(is);
////		out= new DataOutputStream(os);
//		in=is;
//		out=os;
//	}
	
	public void run(){
		try{
			
			while(true){ // or just use for loop
//				String x = in.readUTF();
//				System.out.println(x);
//				out.writeUTF(x);
				
				int x = in.read();
				//System.out.println("Server: read in " +x + " and send out " + x);
				sleep(1000);
				out.write(x);
				
				//out.flush();
				//yield();
				
				
			}
		}
		catch(Throwable ex){
			System.out.println("error S");
		}

	}
	
	
	
	
}

class ClientA extends Thread{
//	private DataInputStream in;
//	private DataOutputStream out;
	
//	private InputStream in;
//	private OutputStream out;
//	
//	public ClientA(InputStream is, OutputStream os)
//	{ 
////		in = new DataInputStream(is);
////		out= new DataOutputStream(os);
//		in=is;
//		out=os;
//	}
	
	private PipedWriter out;
	private PipedReader in;
	
	public ClientA(PipedWriter o,PipedReader i){
		in=i;
		out=o;
	}
	public void run(){
		try{
			
			while(true){ // or just use for loop
//				String x = in.readUTF();
//				System.out.println("B says: "+x);
//				out.writeUTF("A speaks aaa");
				out.write(1);
				System.out.println("A sent out 1");
				sleep(1000);
				int x = in.read();
				System.out.println("A read in: "+x);
				
				
				
				//out.flush();
				//yield();
				
				//i++;
			}
		}
		catch(Throwable ex){
			System.out.println("error A");
		}

	}
	
}

class ClientB extends Thread{
//	private DataInputStream in;
//	private DataOutputStream out;
	
	
//	private InputStream in;
//	private OutputStream out;
//	
//	public ClientB(InputStream is, OutputStream os)
//	{ 
////		in = new DataInputStream(is);
////		out= new DataOutputStream(os);
//		
//		in = is;
//		out = os;
//	}
	
	private PipedWriter out;
	private PipedReader in;
	
	public ClientB(PipedWriter o,PipedReader i){
		in=i;
		out=o;
	}
	public void run(){
		try{
			//int i=0;
			while(true){ // or just use for loop
//				String x = in.readUTF();
//				System.out.println("A says: " +x);
//				out.writeUTF("B speaks bbb");
				
				int x = in.read();
				System.out.println("B read in: "+x);
				
				sleep(1000);
				
				out.write(2);
				System.out.println("B sent out 2");
				
				
				
				//out.flush();
				//yield();
				
				//i++;
			}
		}
		catch(Throwable ex){
			System.out.println("error B");
		}

	}
	
}





