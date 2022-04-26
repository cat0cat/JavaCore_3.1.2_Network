import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server started");
        int port = 8089;

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket clientSocket = serverSocket.accept(); // ждем подключения
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
            {
                System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());
                final String name = in.readLine();
                out.println(String.format("Hi %s, your port is %d. Are you child? (yes/no)", name, clientSocket.getPort()));
                final String answer1 = in.readLine();
                System.out.println(answer1);
                if (answer1.equals("yes")) {
                    out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                } else if (answer1.equals("no")) {
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                } else {
                    out.println("The answer is wrong");
                }
                final String bye = in.readLine();
                System.out.println(bye);
                out.println(bye);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
