/*
 * class handler queries
 */
package com.transfer.ordersweetnesswork.server;

import com.transfer.ordersweetnesswork.DAO.ISweetness;
import com.transfer.ordersweetnesswork.entity.Present;
import com.transfer.ordersweetnesswork.Service.ChocolateCandyDAO;
import com.transfer.ordersweetnesswork.Service.IcicleDAO;
import com.transfer.ordersweetnesswork.Service.ToffeeDAO;
import com.transfer.ordersweetnesswork.entity.Sweetness;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class OrderProcessing {

    private ThreadServer threadServer;
    private Present present;

    public OrderProcessing(ThreadServer threadServer) {
        this.threadServer = threadServer;
    }
    /*
    receives order parameters and calls a method 
    to work with the database with these parameters
    and return object present
    */
    public List<? extends Sweetness> processing() throws IOException {
        BufferedReader input = threadServer.getInputMsg();
        String stringParams;
        int i = 0;
        while (i < 3) {
            threadServer.sentMSG("input type, taste and value");
            stringParams = input.readLine()
                    .toLowerCase(Locale.UK);
            String[] params;
            params = stringParams.split(("[,;:.!?\\s]+"));
            if (params.length == 3) {
                try {
                    List<? extends Sweetness> listSweetness = this.requestDb(params);
                    if (listSweetness == null) {
                        threadServer.sentMSG("not found");
                        return null;
                    } else {
                        threadServer.sentMSG("was added "+listSweetness.toString());
                        return listSweetness;
                    }
                } catch (SQLException ex) {

                    System.out.println(ex.getMessage());
                }
            } else {
                threadServer.sentMSG("invalid params");
                i++;
            }
        }
        return null;
    }
    
    //depending on the parameters, returns the corresponding objects or null
    public List<? extends Sweetness> requestDb(String[] params) throws SQLException {
        threadServer.sentMSG("req");
        Integer numbers;
        numbers = Integer.parseInt(params[2]);
        ISweetness sweetness;
        switch (params[0]) {
            case "icicle":
                sweetness = new IcicleDAO();
                return sweetness.getByTestyDesiredAmount(params[1], numbers);
            case "toffee":
                sweetness = new ToffeeDAO();
                return sweetness.getByTestyDesiredAmount(params[1], numbers);
            case "chocolatecand":
                sweetness = new ChocolateCandyDAO();
                return sweetness.getByTestyDesiredAmount(params[1], numbers);
            default:
                threadServer.sentMSG("null");
                return null;
        }
    }
}
