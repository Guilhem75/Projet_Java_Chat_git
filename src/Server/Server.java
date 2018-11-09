package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static Thread t;
    private static int nbrclient = 0;

    public static void main(String... args) {

        ServerSocket socketserver;
        try {
            ServerSocket socket = new ServerSocket(3000);
            System.out.println("Initialisation du serveur. ");
            System.out.println("Connexion de(s) client(s) ... ");


            while (true) {

                Socket client = socket.accept(); //Un client se connecte et on l'accepte
                System.out.println("Client connecté : ");
                System.out.println("Le client numéro " + nbrclient + " est connecté. ");
                nbrclient++;

                t = new Thread(new Accept_Client(client));
                t.start();
            }


        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}

