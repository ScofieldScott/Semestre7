package analisadores;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import execoes.ExecaoLexica;

public class AnalisadorLexico {
	private int posicao;
	private char [] content;
	private int estado;
	

	public AnalisadorLexico(String input) {
		try {
			String txtConteudo;
			txtConteudo = new String(Files.readAllBytes(Paths.get(input)), StandardCharsets.UTF_8);
			System.out.println("DEBUG --------- ");
			System.out.println(txtConteudo);
			System.out.println("-------------");
			content = txtConteudo.toCharArray();
			this.posicao = 0;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Token analisar() {
		char posAtual;
		String term = "";
		Token token;
		
		if(isItOff()) {
			return null;
		}

		estado = 0;
		while(true) {
			posAtual = nextChar();
			switch(estado) {
			case 0:
				 if(isChar(posAtual)) {
					 term += posAtual;
					 estado = 1;
				 }
				 else if(isDigit(posAtual)) {
					 estado = 3;
					 term += posAtual;
				 }
				 else if (isSpace(posAtual)) {
					 estado = 0;
				 }
				 else if (isOperator(posAtual)){
					 estado = 5;
				 }
				 else {
					  throw new ExecaoLexica("Caracter nao reconhecido."); 
				 }
				 break;
			
			case 1:
				if(isChar(posAtual) || isDigit(posAtual)) {
					term += posAtual;
					estado = 1;
				}
				else if (isSpace(posAtual) || isOperator(posAtual)){
					estado = 2;
				}
				else {
					throw new ExecaoLexica("Simbolo nao reconhecido");
				}
				break;
				
			case 2:
				back();
				token = new Token();
				token.setTipo(entities.enums.TipoToken.IDENTIFICADOR);
				token.setText(term);
				return token; 
			case 3:
				if(isDigit(posAtual)) {
					estado = 3;
					term += posAtual;
				}
				else if(!isChar(posAtual)) {
					estado = 4;
				}
				else {
					throw new ExecaoLexica("Numero nao reconhecido");
				}
				break;
			case 4:
				token = new Token();
				token.setTipo(entities.enums.TipoToken.NUMERO);
				token.setText(term);
				back();
				return token;
			case 5:
				term += posAtual;
				token = new Token();
				token.setTipo(entities.enums.TipoToken.OPERADOR);
				token.setText(term);
				return token;
			}
		}
	}
	
	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
	
	private boolean isChar(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}
	
	private boolean isOperator(char c) {
		return c == '>' || c == '<' || c == '=' || c == '!' ||
				c == '-' || c == '+' || c == '*' || c == '/';
	}
	
	private boolean isSpace(char c) {
		return c == ' ' || c == '\t' || c == '\n' || c == '\r';
	} 
	
	private char nextChar () {
		return content[posicao++];
	}
	
	private boolean isItOff () {
		return posicao == content.length;
	}
	
	private void back() {
		posicao --;
	}
}
