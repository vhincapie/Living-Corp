package co.edu.unbosque.view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ImagenBean implements Serializable {

    private List<String> imagenesPropiedades = new ArrayList<>();
    private List<String> imagenesRecursos = new ArrayList<>();

    @PostConstruct
    public void init() {
        cargarImagenesPropiedad();
        cargarImagenesRecurso();
    }

    public void cargarImagenesPropiedad(){
        imagenesPropiedades.add("images/render_1.jpg");
        imagenesPropiedades.add("images/render_2.jpg");
        imagenesPropiedades.add("images/render_3.jpg");
        imagenesPropiedades.add("images/render_4.jpg");
        imagenesPropiedades.add("images/render_5.jpg");
        imagenesPropiedades.add("images/render_6.jpg");
        imagenesPropiedades.add("images/render_7.jpg");
        imagenesPropiedades.add("images/render_8.jpg");
        imagenesPropiedades.add("images/render_9.jpg");
        imagenesPropiedades.add("images/render_10.jpg");
    }

    public void cargarImagenesRecurso(){
        imagenesRecursos.add("images/recurso_1.jpg");
        imagenesRecursos.add("images/recurso_2.JPG");
    }

    public List<String> getImagenesPropiedades() {
        return imagenesPropiedades;
    }

    public List<String> getImagenesRecursos() {
        return imagenesRecursos;
    }
}
