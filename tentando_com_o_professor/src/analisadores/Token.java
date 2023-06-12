package analisadores;

public class Token {
	public static final int TK_IDENTIFICADOR = 0;
	public static final int TK_NUMERO = 1;
	public static final int TK_OPERADOR = 2;
	public static final int TK_PONTUACAO = 3;
	public static final int TK_ATRIBUICAO = 4;
	
	public static final String TK_TEXT[] = {
		"IDENTIFICADOR", "NUMERO", "OPERADOR", "PONTUACAO", "ATRIBUICAO"
	};
	
	private int tipo;
	private String text;
	private int linha;
	private int coluna;
	
	public Token(int tipo, String text) {
		super();
		this.tipo = tipo;
		this.text = text;
	}
	
	public Token() {
		super();
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public String toString() {
		return "Token [tipo= " + tipo + ", text= " + text + " ]";
	}
	
	
	
}
