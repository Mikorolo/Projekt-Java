import java.io.FileNotFoundException;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        Server testowy = new Server();

        while(true) {

            try {
                testowy.loadData();
            } catch (FileNotFoundException x) {
                x.printStackTrace();
            }

            try {
                testowy.start(6666);
            } catch (IOException x) {
                x.printStackTrace();
            }

            try {
                testowy.action();
            } catch (Exception x) {
                x.printStackTrace();
            }

            try {
                testowy.stop();
            } catch (IOException x) {x.printStackTrace();}

        }
    }
}
