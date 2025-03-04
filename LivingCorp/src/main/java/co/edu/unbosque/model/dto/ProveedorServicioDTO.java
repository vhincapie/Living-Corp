package co.edu.unbosque.model.dto;

public class ProveedorServicioDTO {

    private int id;
    private String email;
    private String description;
    private String type;
    private double price;

    public ProveedorServicioDTO() {
    }

    public ProveedorServicioDTO(int id, String email, String description, String type, double price) {
        this.id = id;
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
