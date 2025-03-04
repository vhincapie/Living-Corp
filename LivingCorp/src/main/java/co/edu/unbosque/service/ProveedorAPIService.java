package co.edu.unbosque.service;

import co.edu.unbosque.model.dto.ProveedorServicioDTO;
import com.google.gson.Gson;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Stateless
public class ProveedorAPIService implements Serializable {

    private static final String API_URL = "http://localhost:8888/proveedor/api";

    private final Client client;
    private final Gson gson;

    public ProveedorAPIService() {
        client = ClientBuilder.newClient();
        gson = new Gson();
    }

    public List<ProveedorServicioDTO> listarProveedores() {
        Response response = client.target(API_URL)
                .path("/proveedor")
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            ProveedorServicioDTO[] proveedoresArray = gson.fromJson(response.readEntity(String.class), ProveedorServicioDTO[].class);
            return Arrays.asList(proveedoresArray);
        } else {
            throw new RuntimeException("Error al obtener los proveedores desde la API. Código de estado: " + response.getStatus());
        }
    }

    public ProveedorServicioDTO obtenerProveedorPorId(int idProveedor) {
        Response response = client.target(API_URL)
                .path("/proveedor/" + idProveedor)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return gson.fromJson(response.readEntity(String.class), ProveedorServicioDTO.class);
        } else {
            throw new RuntimeException("Error al obtener el proveedor desde la API. Código de estado: " + response.getStatus());
        }
    }
}
