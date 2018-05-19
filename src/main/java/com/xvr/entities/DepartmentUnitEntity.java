package com.xvr.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department_unit", schema = "public", catalog = "Test5")
public class DepartmentUnitEntity {
    private int id;
    private String departmentUnitName;
    private String departmentUnitHead;
    private String departmentUnitPhone;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_unit_name")
    public String getDepartmentUnitName() {
        return departmentUnitName;
    }

    public void setDepartmentUnitName(String departmentUnitName) {
        this.departmentUnitName = departmentUnitName;
    }

    @Basic
    @Column(name = "department_unit_head")
    public String getDepartmentUnitHead() {
        return departmentUnitHead;
    }

    public void setDepartmentUnitHead(String departmentUnitHead) {
        this.departmentUnitHead = departmentUnitHead;
    }

    @Basic
    @Column(name = "department_unit_phone")
    public String getDepartmentUnitPhone() {
        return departmentUnitPhone;
    }

    public void setDepartmentUnitPhone(String departmentUnitPhone) {
        this.departmentUnitPhone = departmentUnitPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentUnitEntity that = (DepartmentUnitEntity) o;
        return id == that.id &&
                Objects.equals(departmentUnitName, that.departmentUnitName) &&
                Objects.equals(departmentUnitHead, that.departmentUnitHead) &&
                Objects.equals(departmentUnitPhone, that.departmentUnitPhone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, departmentUnitName, departmentUnitHead, departmentUnitPhone);
    }
}
