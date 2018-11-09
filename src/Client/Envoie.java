package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Envoie implements Runnable {

    //private PrintWriter out;
    private String identifiant = null, message = null;
    private Scanner sc = null;
    private ObjectOutputStream out;


    public Envoie(ObjectOutputStream out) {
        this.out = out;
    }

    public void run() {

        sc = new Scanner(System.in);

        while (true) {
            System.out.println("Votre message : ");
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
