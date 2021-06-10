import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import klient.*;

public class Server
{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private File ranking = new File("ranking.txt");
    private ArrayList<UserScore> scores = new ArrayList<>();


    public void loadData() throws FileNotFoundException
    {
        String[] buff;
        scores.clear();

        Scanner loader = new Scanner(ranking);
        while(loader.hasNextLine())
        {
            buff = loader.nextLine().split(";");
            scores.add(new UserScore(buff[0],Integer.parseInt(buff[1])));
        }

        loader.close();
    }


    public void saveData(UserScore savedData) throws IOException
    {
        BufferedWriter output = new BufferedWriter(new FileWriter(ranking,true));
        output.append(savedData.getCSV());
        output.close();
    }


    public void start(int port) throws IOException
    {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();

            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
    }


    public void action() throws Exception
    {
        UserScore inputLine;
        inputLine = (UserScore)in.readObject();

        if("Send".equals(inputLine.getUserName()))
        {
            System.out.println("sending\n");
            out.writeObject(scores);
        }
        else
        {
            System.out.println("appending");
            System.out.println(inputLine.getScore());
            scores.add(inputLine);
            saveData(inputLine);
        }
    }


    public void sendMessege(UserScore msg) throws IOException
    {
        out.writeObject(msg);
    }


    public void stop() throws IOException
    {
        in.close();
        out.close();
        serverSocket.close();
        clientSocket.close();
    }

}
