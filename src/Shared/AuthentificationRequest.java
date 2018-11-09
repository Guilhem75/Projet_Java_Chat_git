package Shared;

import Server.Chat_Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class AuthentificationRequest implements Runnable {

    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String identifiant = "client ", motdepasse = null;
    public Thread t3;
    private boolean authentifier = false;

    public AuthentificationRequest(Socket so) {
        socket = so;
    }

    public void run() {

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            while (!authentifier) {

                out.println("Entrez votre identifiant : ");
                out.flush();
                identifiant = in.readLine();

                out.println("Entrez votre mot de passe : ");
                out.flush();
                motdepasse = in.readLine();

                if (isValid(identifiant, motdepasse)) {

                    out.println("Connexion ...");
                    System.out.println(identifiant + " est connecté ");
                    out.flush();
                    authentifier = true;
                } else {
                    out.println("Erreur de connexion");
                    out.flush();
                }
            }

            t3 = new Thread(new Chat_Server(socket, identifiant));
            t3.start();
        } catch (IOException e) {

            e.printStackTrace();
            System.err.println(identifiant + "ne répond pas. ");
        }
    }

    private boolean isValid(String identifiant, String motdepasse) {

        boolean connexion = false;

        try {
            Scanner sc = new Scanner(new File("client.txt"));

            while (sc.hasNext()) {
                if (sc.nextLine().equals(identifiant + " " + motdepasse)) {
                    connexion = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erreur, le fichier n'existe pas. ");
        }
        return connexion;
    }
}



