package Server;

import com.sun.jdi.ObjectReference;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Accept_Client implements Runnable {

    private ServerSocket socketserver;
    private boolean connecte = false;
    private Socket client;
    private int nbrclient = 1;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Accept_Client(Socket client) throws IOException {
        this.client = client;

    }

    public void run() {

        while (true) {

            try {
                out = new ObjectOutputStream(client.getOutputStream());
                in = new ObjectInputStream(client.getInputStream());

                do {
                    String id = (String) in.readObject();
                    String pass = (String) in.readObject();

                    if (id.equals("Guilhem") && pass.equals("azerty")) {
                        // System.out.println("Bonjour Guilhem, vous êtes connecté !");
                        out.writeObject("AUTHOK");
                        System.out.println("Client " + id + " authentifié.");
                        connecte = true;

                    } else
                        out.writeObject("AUTHNOK");
                        //System.out.println("Erreur d'authentification. ");
                        connecte = false;

                } while (!connecte);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }


    }
}

