import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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