/*
 * class - Entity from Toffee table
 */
package com.transfer.ordersweetnesswork.entity;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlTransient
@XmlType
@Entity
public class Toffee extends Sweetness implements Serializable {

    @XmlElement(required = true)
    protected Integer idToffee;

    public Toffee() {

    }

    private Toffee(Builder builder) {
        super(builder);
        this.idToffee = builder.idToffee;
    }

    public void setIdToffee(Integer idToffee) {
        this.idToffee = idToffee;
    }

    public Integer getIdToffee() {
        return idToffee;
    }

    public static class Builder extends Toffee.InnerBuilder<Builder> {

        protected int idToffee;

        public Builder idToffee(int idToffee) {
            this.idToffee = idToffee;
            return this;
        }

        @Override
        public Toffee build() {
            Preconditions.checkArgument(this.idToffee > 0, "no field of idSweetnes is initalized correct");
            return new Toffee(this);
        }
    }

    @Override
    public String toString() {
        return "Toffee{"
                + "idToffee="
                + idToffee
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
