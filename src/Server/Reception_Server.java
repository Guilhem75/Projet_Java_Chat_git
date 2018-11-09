package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Reception_Server implements Runnable {

    //private BufferedReader in;
    //private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message = null, identifiant = null;

    public Reception_Server(ObjectInputStream in, String identifiant) {

        this.in = in;
        this.identifiant = identifiant;
    }

    public void run() {

        while (true) {

            try {
                message = (String) in.readObject();
                System.err.println(identifiant + " " + message);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
