package analisadores;

import entities.enums.TipoToken;

public class Token {
    private TipoToken tipo;
    private String valor;

    public Token(TipoToken tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public String toString() {
        return "[" + tipo + "-> " + valor + "]";
    }
}