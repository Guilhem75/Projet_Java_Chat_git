package Client;

import Server.Chat_Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Connexion implements Runnable {

    private Socket socket = null;
    public static Thread t2;
    public static String identifiant, motdepasse, message1, message2, message3;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Scanner sc;
    private boolean connexion = false;
    private boolean connecte = false;

    public Connexion(Socket s) {

        socket = s;
    }

    public void run() {

        try {

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            sc = new Scanner(System.in);


            do {


                System.out.println("Identifiant : ");
                identifiant = sc.next();
                out.writeObject(identifiant);
                out.flush();

                System.out.println("Mot de passe : ");
                motdepasse = sc.next();
                out.writeObject(motdepasse);
                out.flush();

                String repponse = (String) in.readObject();
                if (repponse.equals("AUTHOK")) {
                    System.out.println("Bonjour Guilhem, vous êtes connecté !");
                    connecte = true;
                } else if (repponse.equals("AUTHNOK")) {
                    System.out.println("Erreur ! Votre identifiant ou votre mot de passe est incorrect. Veuillez recommencer. ");

                }
            } while (!connecte);
            //if (identifiant)


            t2 = new Thread(new Chat_Client(out, in));
            t2.start();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Le serveur ne répond pas. ");
        }
    }
}

