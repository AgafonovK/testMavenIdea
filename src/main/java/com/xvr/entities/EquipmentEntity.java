package com.xvr.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment", schema = "public", catalog = "Test5")
public class EquipmentEntity {
    private int id;
    private String equipmentName;
    private String equipmentSerialNumb;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "equipment_name")
    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    @Basic
    @Column(name = "equipment_serial_numb")
    public String getEquipmentSerialNumb() {
        return equipmentSerialNumb;
    }

    public void setEquipmentSerialNumb(String equipmentSerialNumb) {
        this.equipmentSerialNumb = equipmentSerialNumb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentEntity that = (EquipmentEntity) o;
        return id == that.id &&
                Objects.equals(equipmentName, that.equipmentName) &&
                Objects.equals(equipmentSerialNumb, that.equipmentSerialNumb);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, equipmentName, equipmentSerialNumb);
    }
}
