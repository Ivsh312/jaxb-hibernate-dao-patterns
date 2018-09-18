package com.transfer.ordersweetnesswork.DAO;

import com.transfer.ordersweetnesswork.entity.Sweetness;
import java.sql.SQLException;
import java.util.List;
//get By Testy Desired Amount from db
public interface ISweetness {
    public List<? extends Sweetness> getByTestyDesiredAmount(String taste, int numbers) throws SQLException;
}
