package Client;


import java.io.ObjectInputStream;

public class Reception implements Runnable {

    private ObjectInputStream in;
    private String message = null;


    public Reception(ObjectInputStream in) {
        this.in = in;
    }

    public void run() {

        while (true) {

            while (true) {

            }
            // message = (String) in.readObject();
            // System.out.println("Serveur : ");

        }
    }

}
