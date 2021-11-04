package ar.test.banco;

public class ListElement {

    public String color;
    public String fecha;
    public String saldo;
    public String status;


    public ListElement(String color, String fecha, String saldo, String status) {
        this.color = color;
        this.fecha = fecha;
        this.saldo = saldo;
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
