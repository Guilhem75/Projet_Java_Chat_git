package Server;

import Shared.AuthentificationRequest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectedClient implements Runnable {

    private ServerSocket socketserver = null;
    private Socket socket = null;

    public Thread t2;

    public ConnectedClient(ServerSocket ss) {
        socketserver = ss;
    }

    public void run() {

        try {
            while (true) {

                socket = socketserver.accept();
                System.out.println("Un client souhaite se connecter ");

                t2 = new Thread(new AuthentificationRequest(socket));
                t2.start();
            }
        } catch (IOException e) {
            System.err.println("Erreur de serveur");
        }
    }
}

