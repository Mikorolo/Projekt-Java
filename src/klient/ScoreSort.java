package klient;


import java.util.Comparator;


public class ScoreSort implements Comparator<UserScore>
{
    @Override
    public int compare(UserScore o1, UserScore o2) {
        return o1.getValue().compareTo(o2.getValue());
    }
}
