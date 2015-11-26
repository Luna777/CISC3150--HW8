import java.io.DataInputStream;
import java.io.DataOutputStream;
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
					String x = inAS.readUTF();
					outSB.writeUTF(x);	
					
					String y = inBS.readUTF();
					outSA.writeUTF(y);		
				}
			}
			catch(Throwable ex){
				System.out.println("error in S");
		}

	}

}