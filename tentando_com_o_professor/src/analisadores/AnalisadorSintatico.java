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
			Pontuacao();
		} 
	}
	
	public void T() {
		token = al.nextToken();
		if(token.getTipo() != Token.TK_IDENTIFICADOR) {
			throw new ExecaoSintatica("ID inesprado, encontrado " + Token.TK_TEXT[token.getTipo()] + " (" + token.getText() + ") na LINHA "+ token.getLinha() + " e na COLUNA " + token.getColuna());																
		}
	}
	
	public void Numero() {
		token = al.nextToken();
		if(token.getTipo() != Token.TK_NUMERO) {
			throw new ExecaoSintatica("NUMERO inesprado, encontrado " + Token.TK_TEXT[token.getTipo()] + " (" + token.getText() + ") na LINHA "+ token.getLinha() + " e na COLUNA " + token.getColuna());																
		}
	}
	
	public void Operador() {
		token = al.nextToken();
		if (token.getTipo() != Token.TK_OPERADOR) {
			throw new ExecaoSintatica("Operador inesperado, encontrado " + Token.TK_TEXT[token.getTipo()] + " (" + token.getText() + ") na LINHA "+ token.getLinha() + " e na COLUNA " + token.getColuna());
		}
	}

	public void Pontuacao() {
		token = al.nextToken();
		if(token.getTipo() != Token.TK_PONTUACAO) {
			throw new ExecaoSintatica("Pontuacao inesperado, encontrado " + Token.TK_TEXT[token.getTipo()] + " (" + token.getText() + ") na LINHA "+ token.getLinha() + " e na COLUNA " + token.getColuna());
		}
	}
	
}
