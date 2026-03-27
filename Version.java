import java.util.ArrayList;
import java.util.List;

public class Version {
    private int id;
    private String contenido;
    Version padre;
    List<Version> hijos = new ArrayList<>();

    public Version(int id, String contenido, Version padre) {
        this.id = id;
        this.contenido = contenido;
        this.padre = padre;
    }

    public Version(int id, String contenido){
        this.id = id;
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Version getPadre() {
        return padre;
    }

    public void setPadre(Version padre) {
        this.padre = padre;
    }

    public List<Version> getHijos() {
        return hijos;
    }

    public void setHijos(List<Version> hijos) {
        this.hijos = hijos;
    }
}


