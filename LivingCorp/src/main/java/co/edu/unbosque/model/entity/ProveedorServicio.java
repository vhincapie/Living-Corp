package co.edu.unbosque.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SVC_PROVIDERS")
public class ProveedorServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROVIDER_ID")
    private int id;

    @Column(name = "PROVIDER_EMAIL")
    private String email;

    @Column(name = "SERVICE_DESCRIPTION")
    private String description;

    @Column(name = "SERVICE_TYPE")
    private String type;

    @Column(name = "SERVICE_PRICE")
    private double price;

    public ProveedorServicio() {
    }

    public ProveedorServicio(String email, String description, String type, double price) {
        this.email = email;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
