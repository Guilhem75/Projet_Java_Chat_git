package Server;

import Client.Envoie;
import Client.Reception;

import java.io.*;
import java.net.Socket;

public class Chat_Server implements Runnable {

    private Socket socket = null;
    //private BufferedReader in;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    //private PrintWriter out;
    private String identifiant = "client";
    private Thread t4, t5;

    public Chat_Server(Socket so, String ident) {

        socket = so;
        identifiant = ident;
    }

    public Chat_Server(Socket s) {

    }

    public void run() {

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Thread t4 = new Thread(new Reception_Server(in, identifiant));
            t4.start();
            Thread t5 = new Thread(new Envoie_Serveur(out));
            t5.start();
        } catch (IOException e) {
            System.err.println(identifiant + "s'est déconnecté.");
        }
    }
}

