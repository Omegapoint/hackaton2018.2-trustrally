package se.omegapoint.trustrally.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.omegapoint.trustrally.common.PlayerType;
import se.omegapoint.trustrally.server.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPTest {

    Thread serverThread;
    Client client;

    @Before
    public void setup(){
        try {
            serverThread = new Thread(new Server());
            serverThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void canRunClient(){
        InetAddress inetAddress = InetAddress.getLoopbackAddress();
        try {
            Client client = new Client(PlayerType.DRIVER, inetAddress, 4555 );
            new Thread(client).start();

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        serverThread.interrupt();
    }
}
