package com.xvr.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment_type", schema = "public", catalog = "Test5")
public class EquipmentTypeEntity {
    private int id;
    private String typeEquip;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_equip")
    public String getTypeEquip() {
        return typeEquip;
    }

    public void setTypeEquip(String typeEquip) {
        this.typeEquip = typeEquip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentTypeEntity that = (EquipmentTypeEntity) o;
        return id == that.id &&
                Objects.equals(typeEquip, that.typeEquip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, typeEquip);
    }
}
