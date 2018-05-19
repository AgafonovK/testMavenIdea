package com.xvr.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment_status", schema = "public", catalog = "Test5")
public class EquipmentStatusEntity {
    private int id;
    private String statusEquip;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status_equip")
    public String getStatusEquip() {
        return statusEquip;
    }

    public void setStatusEquip(String statusEquip) {
        this.statusEquip = statusEquip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentStatusEntity that = (EquipmentStatusEntity) o;
        return id == that.id &&
                Objects.equals(statusEquip, that.statusEquip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, statusEquip);
    }
}
