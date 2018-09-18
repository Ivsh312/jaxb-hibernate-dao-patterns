/*
 * client for work ith service
 * open 2 thread 
 * 1 ReadThread message
 * 2 sent message
 */
package com.transfer.ordersweetnesswork.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.xml.bind.JAXBException;

public class Client {

    Socket socket;

    public Client() {
        try {
            this.socket = new Socket("192.168.100.4", 8888);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException, JAXBException {

        Client client = new Client();
        client.play();
    }
   
    //start threads for receiving and sending messages to the server
    public void play() {
        Thread thReadThread = new Thread(new ReadThread(socket, this));
        thReadThread.start();
        Thread thWriteThread = new Thread(new WriteThread(socket, this));
        thWriteThread.start();
    }
}
