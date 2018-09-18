/*
*save array of sweetness 
*
*
 */
package com.transfer.ordersweetnesswork.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Present")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "sweetness", "sumMass"})
public class Present implements Serializable {
    @XmlElement(required = true, name = "id")
    private int id;
    @XmlElementWrapper(name = "sweetnessList")
    @XmlElement(name = "sweetness", required = true)
    private List<Sweetness> sweetness;
    @XmlElement(required = true,name = "sumMass")
    private double sumMass = 0.0;
    private static int valueId = 0;

    public Present(List<Sweetness> inputSweetness) {
        sweetness.addAll(inputSweetness);
        for (Sweetness sweetnes : inputSweetness) {
            sumMass += sweetnes.getMass();
        }
        id = ++this.valueId;
    }

    public Present() {
        this.sweetness = new ArrayList<Sweetness>();
        id = ++this.valueId;
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
    //serves to add elements instead of the default method
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
