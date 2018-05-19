package com.xvr.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "request", schema = "public", catalog = "Test5")
public class RequestEntity {
    private int id;
    private String requestDescription;
    private Date requestStartDate;
    private Date requestEndDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "request_description")
    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    @Basic
    @Column(name = "request_start_date")
    public Date getRequestStartDate() {
        return requestStartDate;
    }

    public void setRequestStartDate(Date requestStartDate) {
        this.requestStartDate = requestStartDate;
    }

    @Basic
    @Column(name = "request_end_date")
    public Date getRequestEndDate() {
        return requestEndDate;
    }

    public void setRequestEndDate(Date requestEndDate) {
        this.requestEndDate = requestEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestEntity that = (RequestEntity) o;
        return id == that.id &&
                Objects.equals(requestDescription, that.requestDescription) &&
                Objects.equals(requestStartDate, that.requestStartDate) &&
                Objects.equals(requestEndDate, that.requestEndDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, requestDescription, requestStartDate, requestEndDate);
    }
}
