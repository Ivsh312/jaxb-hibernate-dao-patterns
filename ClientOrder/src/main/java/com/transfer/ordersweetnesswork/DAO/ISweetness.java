package com.transfer.ordersweetnesswork.DAO;

import com.transfer.ordersweetnesswork.entity.Sweetness;
import java.sql.SQLException;
import java.util.List;

public interface ISweetness {
    public List<? extends Sweetness> getByTestyDesiredAmount(String taste, int numbers) throws SQLException;
}
