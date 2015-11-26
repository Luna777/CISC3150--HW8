//CISC3150
//Xin Guan
//11/25/15
//
//Server: 
// get a input from ClientA, then sent it to ClientB
// get a input from ClientB, then sent it to ClientA
// with no delay time.


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
					//get a input from ClientA, then sent it to ClientB
					String x = inAS.readUTF();
					outSB.writeUTF(x);	
					
					//get a input from ClientB, then sent it to ClientA
					String y = inBS.readUTF();
					outSA.writeUTF(y);		
				}
			}
			catch(IOException ex){
				System.out.println("error in S");
		}

	}

}