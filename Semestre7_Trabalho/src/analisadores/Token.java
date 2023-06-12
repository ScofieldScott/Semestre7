package analisadores;

import entities.enums.TipoToken;

public class Token {
	private TipoToken tipo;
    private String text;

    public Token(TipoToken tipo, String valor) {
        this.tipo = tipo;
        this.text = valor;
    }
    
    public Token() {
    	super();
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public void setTipo(TipoToken tipo) {
    	this.tipo = tipo;
    }

    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

    public String toString() {
        return "Token [Tipo= " + tipo + ", Texto= " + text + "]";
    }
}
