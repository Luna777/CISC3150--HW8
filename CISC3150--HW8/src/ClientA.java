//CISC3150
//Xin Guan
//11/25/15
// 
//ClientA:
// send a message randomly picked to Server, then print out the message.
// catch the message from Server, print it out.
// 
// there are random time intervals between the sending and catching 
// (I didn't make the random time too randomly, I narrow down the
// range of it as "sleep(rd.nextInt(10)*100)". 
// That will be easy for look up.)
//
// PipedWriter and PipedReader are good for characters, but
// I really want to send and receive strings through the pipe as message.
// So, I found this page and learn to use DataInputStream 
// and DataOutputStream which is also for I/O in this page: 
// http://www.informit.com/articles/article.aspx?p=26326&seqNum=10)

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
				sleep(rd.nextInt(10)*100);
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