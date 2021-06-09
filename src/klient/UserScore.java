package klient;


import java.io.Serializable;


public class UserScore implements Serializable
{
    String UserName;
    int score;

    public UserScore(String name, int scr)
    {
        UserName = name;
        score = scr;
    }

    public String getScore()
    {
        return UserName + " - " + score;
    }

    public Integer getValue()
    {
        return score;
    }
    public String getUserName(){ return UserName; }
    public String getCSV(){return UserName+";"+score+"\n";}
}