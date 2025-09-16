import java.io.*;
import java.net.*;

public class ClientTCP {
    public static void main(String[] args) {
        try (Socket socket = new Socket("10.0.2.15", 9000); // Substituir pelo IP do servidor
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Conectado ao servidor TCP...");
            out.println("Natal"); 
            String response = in.readLine();
            System.out.println("Resposta do servidor: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}