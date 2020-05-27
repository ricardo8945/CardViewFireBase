package com.example.cardviewfirebase;

public class News {
    private String id;
    private String Fecha;
    private String Descripcion;
    private String Image;

    public News() {

    }

    public News(String id, String fecha, String descripcion, String image) {
        this.id = id;
        Fecha = fecha;
        Descripcion = descripcion;
        Image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
