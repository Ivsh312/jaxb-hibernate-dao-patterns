/*
 * class - Entity from Sweetness table
 */
package com.transfer.ordersweetnesswork.entity;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
//@XmlSeeAlso({Icicle.class,Toffee.class,ChocolateCandy.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"idSweetnes", "taste", "mass", "price", "nameProdyck", "nameTypeSweetness"})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Sweetness implements Serializable {
    @XmlElement(required = true)
    @Id @GeneratedValue
    protected Integer idSweetnes;
    @XmlElement(required = true)
    protected String taste;
    @XmlElement(required = true)
    protected double mass;
    @XmlElement(required = true)
    protected double price;
    @XmlElement(required = true)
    protected String nameProdyck;
    @XmlElement(required = true)
    protected String nameTypeSweetness;
    
    public Sweetness(){
        
    }

    protected Sweetness(InnerBuilder<?> builder) throws IllegalStateException {
        Preconditions.checkArgument(builder.idSweetnes > 0, 
                "no field of idSweetnes is initalized correct");
        Preconditions.checkArgument(builder.mass > 0.0, 
                "no field of mass is initalized correct");
        Preconditions.checkArgument(builder.price > 0.0, 
                "no field of price is initalized correct");
        Preconditions.checkNotNull(builder.taste,
                "no field of taste is initalized correct");
        Preconditions.checkNotNull(builder.nameProdyck,
                "no field of nameProdyck is initalized");
        Preconditions.checkNotNull(builder.nameTypeSweetness,
                "no field of nameTypeSweetness is initalized");

        idSweetnes = builder.idSweetnes;
        taste = builder.taste;
        mass = builder.mass;
        price = builder.price;
        nameProdyck = builder.nameProdyck;
        nameTypeSweetness = builder.nameTypeSweetness;
    }
    @XmlType
    protected static class InnerBuilder<T extends InnerBuilder<T>> {
        @XmlAttribute
        protected int idSweetnes;
        @XmlAttribute
        protected String taste;
        @XmlAttribute
        protected double mass;
        @XmlAttribute
        protected double price;
        @XmlAttribute
        protected String nameProdyck;
        @XmlAttribute
        protected String nameTypeSweetness;

        public T idSweetnes(int idSweetnes) {
            this.idSweetnes = idSweetnes;
            return (T) this;
        }

        public T taste(String taste) {
            this.taste = taste;
            return (T) this;
        }

        public T mass(double mass) {
            this.mass = mass;
            return (T) this;
        }

        public T price(double price) {
            this.price = price;
            return (T) this;
        }

        public T nameProdyck(String nameProdyck) {
            this.nameProdyck = nameProdyck;
            return (T) this;
        }

        public T nameTypeSweetness(String nameTypeSweetness) {
            this.nameTypeSweetness = nameTypeSweetness;
            return (T) this;
        }

        public Sweetness build() {
            return new Sweetness(this);
        }
    }

    public int getIdSweetnes() {
        return idSweetnes;
    }

    public void setIdSweetnes(int idSweetnes) {
        this.idSweetnes = idSweetnes;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNameProdyck() {
        return nameProdyck;
    }

    public void setNameProdyck(String nameProdyck) {
        this.nameProdyck = nameProdyck;
    }

    public String getNameTypeSweetness() {
        return nameTypeSweetness;
    }

    public void setNameTypeSweetness(String nameTypeSweetness) {
        this.nameTypeSweetness = nameTypeSweetness;
    }

    @Override
    public String toString() {
        return "Sweetnes{" + "idSweetnes=" + idSweetnes + ", taste=" + taste + ", mass=" + mass + ", price=" + price + ", nameProdyck=" + nameProdyck + ", nameTypeSweetness=" + nameTypeSweetness + '}';
    }
}
