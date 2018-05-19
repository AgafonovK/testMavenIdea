package com.xvr.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "position", schema = "public", catalog = "Test5")
public class PositionEntity {
    private int idposition;
    private String positionName;

    @Id
    @Column(name = "idposition")
    public int getIdposition() {
        return idposition;
    }

    public void setIdposition(int idposition) {
        this.idposition = idposition;
    }

    @Basic
    @Column(name = "position_name")
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionEntity that = (PositionEntity) o;
        return idposition == that.idposition &&
                Objects.equals(positionName, that.positionName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idposition, positionName);
    }
}
