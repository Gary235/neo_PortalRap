package com.example.neo_portalrap.Clases;

public class Base {

    private String id;
    private String artista;
    private String duracion;
    private String imagen;
    private String nombre;
    private String url;
    private Boolean seleccionado;

    public Base() {
    }

    public Base(String id, String artista, String duracion, String imagen, String nombre, String url, Boolean seleccionado) {
        this.id = id;
        this.artista = artista;
        this.duracion = duracion;
        this.imagen = imagen;
        this.nombre = nombre;
        this.url = url;
        this.seleccionado = seleccionado;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getDuracion() {
        return duracion;
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSeleccionado() { return seleccionado; }
    public void setSeleccionado(Boolean seleccionado) { this.seleccionado = seleccionado; }
}
