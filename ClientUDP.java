import java.net.*;
import java.io.*;

public class ClientUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            
            InetAddress serverAddress = InetAddress.getByName("10.0.2.15"); // Substituir pelo IP do servidor
            byte[] sendBuffer = "Nazaré".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 9001);

            System.out.println("Enviando requisição UDP");
            socket.send(sendPacket);
            
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Resposta do servidor: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}