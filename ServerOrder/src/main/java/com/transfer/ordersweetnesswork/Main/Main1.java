//test work dao
package com.transfer.ordersweetnesswork.Main;

import com.transfer.ordersweetnesswork.Service.ChocolateCandyDAO;
import com.transfer.ordersweetnesswork.Service.IcicleDAO;
import com.transfer.ordersweetnesswork.entity.ChocolateCandy;
import com.transfer.ordersweetnesswork.entity.Icicle;
import java.sql.SQLException;

//test work dao
public class Main1 {

    public static void main(String[] args) throws SQLException {
        ChocolateCandyDAO ccDao = new ChocolateCandyDAO();
        IcicleDAO icDao = new IcicleDAO();
        ChocolateCandy chok = new ChocolateCandy.Builder()
                .idChocolateCandy(2)
                .nameTypeSweetness("MM")
                .taste("hD")
                .price(12.2)
                .mass(12)
                .nameProdyck("Ad")
                .idSweetnes(7788)
                .build();
        Icicle icicle1 = new Icicle.Builder()
                .idIcicle(1)
                .taste("1")
                .idSweetnes(3)
                .mass(23.2)
                .nameProdyck("icicle1")
                .nameTypeSweetness("1")
                .price(99.9)
                .build();
        Icicle icicle2 = new Icicle.Builder()
                .idIcicle(2)
                .idSweetnes(4)
                .mass(93.2)
                .taste("1")
                .nameProdyck("icicle1")
                .nameTypeSweetness("1")
                .price(999.9)
                .build();
        Icicle icicle3 = new Icicle.Builder()
                .idIcicle(3)
                .idSweetnes(5)
                .mass(3.2)
                .taste("1")
                .nameProdyck("icicle1")
                .nameTypeSweetness("1")
                .price(0.9)
                .build();

        System.out.println("add cc "+ccDao.addChocolateCandy(chok));
        System.out.println("add ic1 "+icDao.addIcicle(icicle1));
        System.out.println("add ic2 "+icDao.addIcicle(icicle2));
        System.out.println("add ic3 "+icDao.addIcicle(icicle3));
        System.out.println("-------------------------------");
        System.out.println("get bay testy ");
        System.out.println(icDao.getByTestyDesiredAmount("1", 4));
    }
}
