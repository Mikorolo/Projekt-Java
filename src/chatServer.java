import java.net.*;
import java.io.*;
import javax.swing.*;
class chatServer
{
	public static void main(String arg[]) throws Exception
	{
		ServerSocket server = new ServerSocket(2000);
		Socket s = server.accept();
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		DataInputStream dis = new DataInputStream(s.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
		//user input
		String u = br.readLine();
		
		dos.writeUTF("Server:" + u);
		
		String msg = dis.readUTF();
		System.out.println(msg);
		}
	}
}