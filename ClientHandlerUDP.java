import java.net.*;
import java.io.*;

public class ClientHandlerUDP implements Runnable {
    private DatagramSocket socket;
    private DatagramPacket receivePacket;

    public ClientHandlerUDP(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.receivePacket = packet;
    }

    @Override
    public void run() {
        try {
            String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("UDP: Recebido '" + request + "' de " + receivePacket.getAddress().getHostAddress());

            Thread.sleep(5000); 

            String response = processRequest(request);

            byte[] sendBuffer = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, 
                                                            receivePacket.getAddress(), receivePacket.getPort());
            socket.send(sendPacket);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {
        // Simples lógica de exemplo
        if (request.equalsIgnoreCase("Natal")) {
            return "Tempo em Natal: Ensolarado";
        } else if (request.equalsIgnoreCase("Nazaré")) {
            return "Tempo em Nazaré: Chuvoso";
        } else {
            return "Cidade nao encontrada";
        }
    }
}