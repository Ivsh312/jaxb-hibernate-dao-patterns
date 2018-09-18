package com.transfer.ordersweetnesswork.DAO;


import com.transfer.ordersweetnesswork.entity.Sweetness;
import com.transfer.ordersweetnesswork.entity.Toffee;
import java.sql.SQLException;
import java.util.List;

public interface IToffeeDAO extends ISweetness{
    
    //create
    public boolean addToffee(Toffee toffee) throws SQLException;

    //get by id
    public Toffee getById(Integer id) throws SQLException;

    //get the assigned amount
    public List<? extends Sweetness> getByTestyDesiredAmount(String taste, int numbers) throws SQLException;
    
     //delete by id
    public int deleteById(int id) throws SQLException;
}
