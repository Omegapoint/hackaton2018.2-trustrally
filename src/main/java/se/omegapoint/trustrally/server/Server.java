package se.omegapoint.trustrally.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server implements Runnable {

    DatagramSocket connectionSocket;
    private boolean running;
    private byte[] buf = new byte[256];

    public Server() {
        try {
            connectionSocket = new DatagramSocket(4555); //TODO: Add server address...
        } catch (SocketException e) {
            System.out.println("Connection failed...");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            try {
                System.out.println("Running server...");
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                connectionSocket.receive(packet);
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, clientAddress, clientPort);

                String clientData = new String(packet.getData(), 0, packet.getLength());

                if (clientData.equals("end")) { //TODO: Implement MessageType
                    running = false;
                    continue;
                }

                connectionSocket.send(packet);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}