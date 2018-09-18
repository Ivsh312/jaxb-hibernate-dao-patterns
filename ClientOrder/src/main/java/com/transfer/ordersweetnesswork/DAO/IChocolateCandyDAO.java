package com.transfer.ordersweetnesswork.DAO;


import com.transfer.ordersweetnesswork.entity.ChocolateCandy;
import com.transfer.ordersweetnesswork.entity.Sweetness;
import java.sql.SQLException;
import java.util.List;

public interface IChocolateCandyDAO extends ISweetness{

    //create
    public boolean addChocolateCandy(ChocolateCandy chocolateCandy) throws SQLException;

    //get by id
    public ChocolateCandy getById(Integer id) throws SQLException;

    //get the assigned amount
    public List<? extends Sweetness> getByTestyDesiredAmount(String taste, int numbers) throws SQLException;
    
    //delete by id
    public int deleteById(int id) throws SQLException;
}
