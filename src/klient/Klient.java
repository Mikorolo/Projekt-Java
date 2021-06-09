package klient;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class Klient
{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    ArrayList<UserScore> scores;


    public void establishConnection(String ip, int port) throws IOException
    {
        clientSocket = new Socket(ip,port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }


    public void sendMessege(UserScore msg) throws IOException
    {
        out.writeObject(msg);
    }


    public void requestRanking() throws ClassNotFoundException,IOException
    {
        sendMessege(new UserScore("Send",0));

        Object obj = in.readObject();
        scores = (ArrayList<UserScore>)obj;

        scores.sort(new ScoreSort());

    }

    public ArrayList<UserScore> getScores()
    {
        return scores;
    }

    public void stopConnection() throws IOException
    {
        clientSocket.close();
        out.close();
        in.close();
    }
}
