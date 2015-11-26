import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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
