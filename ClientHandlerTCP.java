import java.io.*;
import java.net.Socket;

public class ClientHandlerTCP implements Runnable {
    private Socket clientSocket;

    public ClientHandlerTCP(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request = in.readLine();
            System.out.println("TCP: Recebido '" + request + "' de " + clientSocket.getInetAddress());

            // Simula o tempo de processamento
            Thread.sleep(5000); 

            String response = processRequest(request);
            out.println(response);
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String processRequest(String request) {
        if (request.equalsIgnoreCase("Natal")) {
            return "Tempo em Natal: Ensolarado";
        } else if (request.equalsIgnoreCase("Nazaré")) {
            return "Tempo em Nazaré: Chuvoso";
        } else {
            return "Cidade nao encontrada";
        }
    }
}