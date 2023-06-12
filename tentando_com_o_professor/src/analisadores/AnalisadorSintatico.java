package analisadores;

import execoes.ExecaoSintatica;

public class AnalisadorSintatico {
	private Token token;
	private AnalisadorLexico al;
	
	public AnalisadorSintatico(AnalisadorLexico al) {
		this.al = al;
	}
	public void Exprecao() {
		T();
		El();
	}
	
	public void El() {
		token = al.nextToken();
		if (token != null) {
			Operador();
			T();
			El();
			
		} 
	}
	
	public void T() {
		token = al.nextToken();
		if(token.getTipo() != Token.TK_OPERADOR && token.getTipo() != Token.TK_NUMERO) {
			throw new ExecaoSintatica("ID ou NUMERO esprado, encontrado " + Token.TK_TEXT[token.getTipo()] + " (" + token.getText() + ") na LINHA "+ token.getLinha() + " e na COLUNA " + token.getColuna());																
		}
	}
	
	public void Operador() {
		if (token.getTipo() != Token.TK_OPERADOR) {
			throw new ExecaoSintatica("Operador esperado, encontrado " + Token.TK_TEXT[token.getTipo()] + " (" + token.getText() + ") na LINHA "+ token.getLinha() + " e na COLUNA " + token.getColuna());
		}
	}
	
}
