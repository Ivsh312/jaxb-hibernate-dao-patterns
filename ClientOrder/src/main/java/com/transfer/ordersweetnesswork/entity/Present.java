/*
*save array of sweetness 
*
*
 */
package com.transfer.ordersweetnesswork.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Present")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "sweetness", "sumMass"})
@Entity
@Table(name = "Present")
public class Present implements Serializable {
    @XmlElement(required = true, name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPresent")
    private int id;
    @XmlElementWrapper(name = "sweetnessList")
    @XmlElement(name = "sweetness", required = true)
    @OneToMany(mappedBy = "present")
    private List<Sweetness> sweetness;
    @Column(name = "sumMass")
    @XmlElement(required = true, name = "sumMass")
    private double sumMass = 0.0;
    private static int valueId = 0;

    public Present(List<Sweetness> inputSweetness) {
        sweetness.addAll(inputSweetness);
        for (Sweetness sweetnes : inputSweetness) {
            sumMass += sweetnes.getMass();
        }
        id = valueId++;
    }

    public Present() {
        this.sweetness = new ArrayList<Sweetness>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean addSweetness(Sweetness swets) {
        System.out.println("1");
        System.out.println(swets);
        this.sumMass += swets.getMass();
        return this.sweetness.add(swets);
    }

    public boolean addSweetnessSet(List<? extends Sweetness> swets) {
        for (Sweetness sweetnes : swets) {
            sumMass += sweetnes.getMass();
        }
        id = valueId++;
        return sweetness.addAll(swets);
    }

    public void setSweetness(List<Sweetness> sweetness) {
        this.sweetness.addAll(sweetness);
        for (Sweetness sweetnes : sweetness) {
            sumMass += sweetnes.getMass();
        }
        this.sweetness = sweetness;
    }

    public void setSumMass(double sumMass) {
        this.sumMass = sumMass;
    }

    public static void setValueId(int valueId) {
        Present.valueId = valueId;
    }

    public List<Sweetness> getSweetness() {
        return sweetness;
    }

    public double getSumMass() {
        return sumMass;
    }

    public static int getValueId() {
        return valueId;
    }

    @Override
    public String toString() {
        return "Present{" + "id=" + id + ", sweetness=" + sweetness + ", sumMass=" + sumMass + '}';
    }

}
