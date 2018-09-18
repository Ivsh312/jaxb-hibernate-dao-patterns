/*
 * class - Entity from ICICLE table
 */
package com.transfer.ordersweetnesswork.entity;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
@XmlType
@XmlTransient
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@DiscriminatorValue("ICICLE")
public class Icicle extends Sweetness implements Serializable {
    @XmlElement(required = true)
    protected Integer idIcicle;
    
    public Icicle(){
        
    }
    
    private Icicle(Builder builder) {
        super(builder);
        this.idIcicle = builder.idIcicle;
    }

    public Integer getIdIcicle() {
        return idIcicle;
    }

    public void setIdIcicle(int idIcicle) {
        this.idIcicle = idIcicle;
    }

    @XmlType
    public static class Builder extends Icicle.InnerBuilder<Builder> {
        @XmlElement
        protected int idIcicle;

        public Builder idIcicle(int idIcicle) {
            this.idIcicle = idIcicle;
            return this;
        }

        @Override
        public Icicle build() {
            Preconditions.checkArgument(this.idIcicle > 0, "no field of idSweetnes is initalized correct");
            return new Icicle(this);
        }
    }

    @Override
    public String toString() {
        return "Icicle{" 
                + "idIcicle=" 
                + idIcicle 
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
