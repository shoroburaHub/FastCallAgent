package ua.com.fastCallAgent.entity;

import javax.enterprise.inject.Default;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by koko on 29.08.16.
 */
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private String phone;
    private String dateTime;

    private int callCount = 1;
    @Transient
    private boolean isLast;

    public Client(){

    }

    public Client(String status, String phone, String dateTime) {
        this.status = status;
        this.phone = phone;
        this.dateTime = dateTime;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return phone.equals(client.phone);

    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }

    public boolean getIsLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", callCount=" + callCount +
                '}';
    }
}