package quiztastic.server;

import quiztastic.core.TUI;
import quiztastic.entry.DisplayQuestions;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer implements Runnable{

    DisplayQuestions displayQuestions = new DisplayQuestions();

    //We create a socket so we can have more connections
    private final Socket socket;

    //We create a constructor for the socket
    public MyServer(Socket socket) {
        this.socket = socket;
    }

    //This code handles the thread via the runnable method (Runnable)
    @Override
    public void run() {

        //Handle IO exception
        try{

//            TUI tui = new TUI();
//            tui.halloWorld(socket);

            displayQuestions.displayQuestions(socket);

//            We put the socket to a scanner, so we can visually se the messges from the socket
//            Scanner scanner = new Scanner(socket.getInputStream());
//
//            //We use the "printOut" to write visual messages (Like System.out.println())
//            //WE CAN'T USE System.out.println for client messages
//            PrintWriter printOut = new PrintWriter(socket.getOutputStream());
//
//            /**
//             *
//             * 1. Start project
//             * 2. Open Terminal
//             * 3. Write: telnet 127.0.0.1 6060
//             * See the connection in the Terminal -> "Write something >"
//             *
//             */
//
//            //We create a String
//            String line;
//
//            //Message to user
//            printOut.print("Write something > ");
//
//            //We flush and show the message
//            printOut.flush();
//
//            //We scan/loop as long as the user doesn't write "quit"
//            while(!(line = scanner.nextLine()).equalsIgnoreCase("quit")){
//
//                //We can stop all future connections if we write "stop"
//                if(line.equalsIgnoreCase("stop")){
//
//                    //We set the volatile boolean to false
//                    keepRunning = false;
//                }
//
//                //We reprint the message that the user is writing
//                printOut.println("You said: " + line);
//
//                //Message to user
//                printOut.print("Write something > ");
//
//                //We flush and show the message
//                printOut.flush();
//
//            }
//
            //We close the socket (We get to is when we write "quit")
            socket.close();

        } catch (IOException e) {

            //The error message
            e.printStackTrace();

        }

    }

    //The overall RUN variable
    public static volatile boolean keepRunning = true;

    public static void main(String[] args) throws IOException {

        //Local port: There is op to 1000. After 1000 we need special permission.
        final int port = 6060;

        //Create "main" socket from port for a 1 to 1 connection
        final ServerSocket serverSocket = new ServerSocket(port);

        //The server keeps listening for connections
        while (keepRunning){

            //Creates the specific socket connection
            Socket socket = serverSocket.accept();

            //Get connection message and IP from client and server port from client
            System.out.println("[CONECTED] client IP: " + socket.getInetAddress() + " client port: " + socket.getPort());
            System.out.println("From local IP: " + socket.getLocalAddress() + " local port: " + socket.getLocalPort());

            //We create a thread from the MyFirstServer constructor
            Thread thread = new Thread(new MyServer(socket));

            //We start the thread
            thread.start();


//            //Makes the thread sleep
//            try{
//                Thread.sleep( 3000 );
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            //Waits for response "async"
//            try{
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            //Waits for response "async" for amount of time
//            try{
//                thread.join(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            //Interrupts the thread and closes it
//            thread.interrupt();


        }

    }

}
