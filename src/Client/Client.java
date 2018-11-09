package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static Socket socket = null;
    public static Thread t1;

    public static void main(String[] args) {

        try {

            System.out.println("Demande de connexion. ");
            socket = new Socket("127.0.0.1", 3000);
            System.out.println("Connexion établie avec le serveur.");


            t1 = new Thread(new Connexion(socket));
            t1.start();


        } catch (UnknownHostException e) {
            System.err.println("Impossible de se connecter à l'adresse" + socket.getLocalAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
