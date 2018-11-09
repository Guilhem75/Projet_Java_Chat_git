package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Chat_Client implements Runnable {

    public Socket socket;
    // private PrintWriter out = null;
    //private BufferedReader in = null;
    private Scanner sc;
    private Thread t3, t4;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Chat_Client(ObjectOutputStream out, ObjectInputStream in) {
        this.in = in;
        this.out = out;

    }

    public void run() {

        //out = new PrintWriter(socket.getOutputStream());
        //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        sc = new Scanner(System.in);

        Thread t3 = new Thread(new Envoie(out));
        t3.start();

        Thread t4 = new Thread(new Reception(in));
        t4.start();
    }

}


