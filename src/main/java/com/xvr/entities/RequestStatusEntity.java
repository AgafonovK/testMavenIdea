package com.xvr.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "request_status", schema = "public", catalog = "Test5")
public class RequestStatusEntity {
    private int id;
    private String reqStatus;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "req_status")
    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestStatusEntity that = (RequestStatusEntity) o;
        return id == that.id &&
                Objects.equals(reqStatus, that.reqStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, reqStatus);
    }
}
