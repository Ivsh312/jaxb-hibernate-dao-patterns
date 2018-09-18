/*
 *class for working with a serialized object
 */
package com.transfer.ordersweetnesswork.client;

import com.transfer.ordersweetnesswork.Service.PresentDAO;
import com.transfer.ordersweetnesswork.entity.Present;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class DataReader implements Runnable {

    private Present present;
    private final Socket socket;
    private final Client client;
    String fileName;
    String size;
    String fileSize;

    public DataReader(Socket socket, Client client, String fileNameAndFileSize) {
        String[] paramsFileNameAndFileSize = fileNameAndFileSize.split(":");
        this.client = client;
        this.socket = socket;
        this.fileName = paramsFileNameAndFileSize[0];
        this.fileSize = paramsFileNameAndFileSize[1];
        present = new Present();
    }

    @Override
    public void run() {
        try {
            File file = readFile();
            unmarshallering(file);
            PresentDAO presentDAO = new PresentDAO();
//            presentDAO.addPresent(present);
            System.out.println("client " + present);
        } catch (JAXBException ex) {
            ex.printStackTrace();
//        } catch (SQLException ex) {
//            Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
//        } catch (SQLException ex) {
//            ex.printStackTrace();

    }
    
    //get file from server
    private File readFile() throws FileNotFoundException, IOException, InterruptedException {
        Thread.sleep(40);
        File file = new File(fileName);
        int in;
        BufferedInputStream bos;
        BufferedOutputStream bis = new BufferedOutputStream(new FileOutputStream(file));
        bos = new BufferedInputStream(socket.getInputStream());
        byte[] byteArray = new byte[Integer.parseInt(fileSize)];
        while ((in = bos.read(byteArray)) != -1) {
            bis.write(byteArray, 0, in);
        }
        bos.close();
        bis.close();
        return file;
    }
   
    //unmarshaller XML file
    private void unmarshallering(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Present.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        this.present = (Present) unmarshaller.unmarshal(file);
    }
}
