package com.transfer.ordersweetnesswork.DAO;

import com.transfer.ordersweetnesswork.entity.Present;
import java.sql.SQLException;
import java.util.List;

public interface IPresentDAO {
        //create
    public boolean addPresent(Present present) throws SQLException;

    //get by id
    public Present getById(Integer id) throws SQLException;

    //get the assigned amount
    public List<Present> getByLimetAmout(int numbers) throws SQLException;
    
     //delete by id
    public int deleteById(int id) throws SQLException;
}
