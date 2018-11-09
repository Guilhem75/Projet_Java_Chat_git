package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Envoie_Serveur implements Runnable {

    // private PrintWriter out;
    private String message = null;
    private Scanner sc = null;
    private ObjectOutputStream out;
    //private ObjectInputStream in;

    public Envoie_Serveur(ObjectOutputStream out) {
        this.out = out;
    }

    public void run() {

        sc = new Scanner(System.in);

        while (true) {
            System.out.println("Serveur : ");
            message = sc.nextLine();
            try {
                out.writeObject(message);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
