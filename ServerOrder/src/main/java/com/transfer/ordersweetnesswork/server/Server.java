/*
 *the server allocating for each client a separate flow
*/

package com.transfer.ordersweetnesswork.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Server {

    private ServerSocket serverSocket;
    private Set<ThreadServer> SetClients = new HashSet<>();
    private Set<String> SetNames = new HashSet<>();
    private static int valueId = 0;

    public static void main(String[] args) {
        Server serv = new Server();
        serv.start();//run server

    }
     /*
    waiting for client connections then add the 
    clients to the set and start processing in the new thread
    */
    private void start() {
        try {
            System.out.println("Enter the port number");
            Scanner scan = new Scanner(System.in);
            String port;
            port = scan.nextLine();
            try {
                this.serverSocket = new ServerSocket(Integer.parseInt(port));
            } catch (NumberFormatException e) {
                this.serverSocket = new ServerSocket(8888);  // default port for server 
            }
            
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                System.out.println("New user connected");
                ThreadServer threadServer = new ThreadServer(this, socket, Server.valueId++);
                SetClients.add(threadServer);
                Thread th = new Thread(threadServer);
                th.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //add new client
    public void addUser(String name) {
        this.SetNames.add(name);
    }
    
    //remove client
    public void removeUser(String userName, ThreadServer client) {
        boolean removed = this.SetNames.remove(userName);
        if (removed) {
            this.SetClients.remove(client);
            System.out.println("The user " + userName + " quitted");
        }
    }
}
