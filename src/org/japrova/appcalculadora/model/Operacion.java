package org.japrova.appcalculadora.model;

public enum Operacion {

    MULT("*"),
    DIV("/"),
    SUM("+"),
    RES("-");

    private String operacion;

    Operacion(String operacion) {
        this.operacion = operacion;
    }

    public String getOperacion() {
        return operacion;
    }
}
