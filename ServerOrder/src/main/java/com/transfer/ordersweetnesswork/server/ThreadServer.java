/*
 * Thread for work with one client
 * Receives messages from the client and responds to keywords
 */
package com.transfer.ordersweetnesswork.server;

import com.transfer.ordersweetnesswork.entity.Present;
import com.transfer.ordersweetnesswork.entity.Sweetness;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

class ThreadServer implements Runnable {

    Server server;
    Socket socket;
    PrintWriter outputMsg;
    BufferedReader inputMsg;
    int id;
    Present present;
    String userName;
    private static volatile int idvalue = 0;
    private String startWorcsWithServer = "input \"order\" for workcs with service";
    //codeword to start file sharing
    private String codeForStartTransfer = "8934951934553323435465768";
    ObjectOutputStream outFile;

    public ThreadServer(Server server, Socket socket, int id) {
        this.id = ++idvalue;
        this.server = server;
        this.socket = socket;
        present = new Present();
        try {
            outputMsg = new PrintWriter(socket.getOutputStream(), true);
            inputMsg = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //the first level of work with the client
    /*
    exchanges messages with the client, waits for the receipt of the keyword 
    and, if it is received, initializes the processing of requests to the database
     */
    @Override
    public void run() {
        this.sentMSG("Server listens");
        try {
            this.userName = inputMsg.readLine();
            server.addUser(userName);
            String msg;
            OrderProcessing processing;
            this.sentMSG(startWorcsWithServer);
            while (true) {
                msg = inputMsg.readLine();
                if (msg.equals("order")) {
                    processing = new OrderProcessing(this);
                    System.out.println(msg);
                    List<? extends Sweetness> listSweetness = processing.processing();
                    if (listSweetness != null) {
                        present.addSweetnessSet(listSweetness);
                        this.sentMSG("inpu + for add more or - for finishing");
                        while (true) {
                            msg = inputMsg.readLine();
                            if (msg.equals("+")) {
                                this.sentMSG(startWorcsWithServer);
                                break;
                            } else {
                                this.sentMSG("server " + present.toString());
                                this.sentMSG(codeForStartTransfer);
                                File file = new File("file" + this.id + ".xml");
                                marshallering(file, present);
                                int fileSize = (int)(long) file.length();
                                this.sentMSG("file" + this.id + ".xml" + ":" + fileSize);
                                sentXmlPresent(file, fileSize);
                            }
                        }
                    }
                    Thread.sleep(100);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (JAXBException ex) {
            System.out.println("JAXBException in ThreadServer");
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    //sent message for client
    public void sentMSG(String msg) {
        outputMsg.println(msg);
        outputMsg.flush();
    }
    
    //disconnect client
    public void disconnect() {
        try {

            if (inputMsg != null) {
                inputMsg.close();
            }
            if (outputMsg != null) {
                outputMsg.close();
            }
            socket.close();
            System.out.println(userName + " disconnecting");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Thread.interrupted();
        }
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PrintWriter getOutputMsg() {
        return outputMsg;
    }

    public void setOutputMsg(PrintWriter outputMsg) {
        this.outputMsg = outputMsg;
    }

    public BufferedReader getInputMsg() {
        return inputMsg;
    }

    public void setInputMsg(BufferedReader inputMsg) {
        this.inputMsg = inputMsg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //do marshaller
    private void marshallering(File file, Present present) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Present.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(present, file);
    }
    //sent xml file to the client
    private void sentXmlPresent(File file, int fileSize) throws FileNotFoundException, IOException {
        int in;
        BufferedOutputStream bos;
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); 
            bos = new BufferedOutputStream(socket.getOutputStream());
            byte[] byteArray = new byte[fileSize];
            while ((in = bis.read(byteArray)) != -1) {
                bos.write(byteArray, 0, in);
            }
        System.out.println("end sent");
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        bos.close();
    }

}
