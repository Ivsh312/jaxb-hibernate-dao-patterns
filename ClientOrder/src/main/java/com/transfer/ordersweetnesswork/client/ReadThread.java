/*
 * class for reading messages and responding to keywords
 */
package com.transfer.ordersweetnesswork.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread implements Runnable {

    BufferedReader inputMsg;
    Socket socket;
    Client client;

    public ReadThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
        try {
            inputMsg = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msg;
        while (true) {
            try {
                if ((msg = inputMsg.readLine()) != null) {
                    if ("8934951934553323435465768".equals(msg)) {
                        while (true) {
                            if((msg = inputMsg.readLine()) != null){
                            Thread thReadThread = new Thread(new DataReader(socket, this.client, msg));
                            thReadThread.start();
                            thReadThread.join();
                            //Thread.sleep(100);
                            break;
                            }
                        }
                        continue;
                    }
                    System.out.println(msg);
                    Thread.sleep(100);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
