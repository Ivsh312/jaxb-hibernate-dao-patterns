/*
 * class - Entity from ChocolateCandy table
 */
package com.transfer.ordersweetnesswork.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlTransient
@Entity
@DiscriminatorValue("CHOCK")
public class ChocolateCandy extends Sweetness implements Serializable {
    //@Id @GeneratedValue

    @XmlElement(required = true)
    protected Integer idChocolateCandy;

    public ChocolateCandy() {

    }

    private ChocolateCandy(Builder builder) {
        super(builder);
        this.idChocolateCandy = builder.idChocolateCandy;
    }

    public Integer getIdChocolateCandy() {
        return idChocolateCandy;
    }

    public void setIdChocolateCandy(Integer idChocolateCandy) {
        this.idChocolateCandy = idChocolateCandy;
    }

    public static class Builder extends Sweetness.InnerBuilder<Builder> {

        protected int idChocolateCandy;

        public Builder idChocolateCandy(int idChocolateCandy) {
            this.idChocolateCandy = idChocolateCandy;
            return this;
        }

        @Override
        public ChocolateCandy build() {
            return new ChocolateCandy(this);
        }
    }

    public void setIdChocolateCandy(int idChocolateCandy) {
        this.idChocolateCandy = idChocolateCandy;
    }

    @Override
    public String toString() {
        return "ChocolateCandy{"
                + "idChocolateCandy="
                + idChocolateCandy
                + ", idSweetnes="
                + idSweetnes
                + ", taste="
                + taste
                + ", mass="
                + mass
                + ", price="
                + price
                + ", nameProdyck="
                + nameProdyck
                + ", nameTypeSweetness="
                + nameTypeSweetness + '}';
    }

}
