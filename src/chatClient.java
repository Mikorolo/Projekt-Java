import java.net.*;
import java.io.*;
import javax.swing.*;
class chatClient
{
	public static void main(String arg[]) throws Exception
	{
		Socket s = new Socket("127.0.0.1", 2000);
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
		String msg = dis.readUTF();
		System.out.println(msg);
		String u = br.readLine();
		dos.writeUTF("Client: " + u);
		}
	}
}