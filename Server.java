import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(9000)) {
                System.out.println("Servidor TCP esperando conexões na porta 9000...");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Cliente TCP conectado: " + clientSocket.getInetAddress().getHostAddress());
                    // nova thread para cada cliente
                    new Thread(new ClientHandlerTCP(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try (DatagramSocket serverSocket = new DatagramSocket(9001)) {
                System.out.println("Servidor UDP esperando datagramas na porta 9001...");
                while (true) {
                    byte[] receiveBuffer = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                    serverSocket.receive(receivePacket);
                    System.out.println("Requisição UDP recebida de: " + receivePacket.getAddress().getHostAddress());

                    //nova thread pars cada requisição UDP
                    new Thread(new ClientHandlerUDP(serverSocket, receivePacket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}