import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

class ClientA extends Thread{
	private DataInputStream in;
	private DataOutputStream out;
	private Date date;

//	private PipedWriter out;
//	private PipedReader in;
	
	public ClientA(OutputStream o,InputStream i){
		in= new DataInputStream(i);
		out=new DataOutputStream(o);
		
	}
	public void run(){
		try{
			Random rd = new Random();
			while(true){ 
				sleep((rd.nextInt(5)+5)*100);
				date = new Date();
				String s = Main.msgs[rd.nextInt(9)];
				out.writeUTF(s);
				System.out.println("At "+date.toString()+", A said: "+s);
				sleep(rd.nextInt(10)*100);
				date = new Date();
				String x = in.readUTF();
				System.out.println("At "+date.toString()+", A received: "+x);

			}
		}
		catch(Throwable ex){
			System.out.println("error in A");
		}

	}
	
}