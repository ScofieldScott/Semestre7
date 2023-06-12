package analisadores;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import execoes.ExecaoLexica;

public class AnalisadorLexico {
	private char[] content;
	private int estado;
	private int posicao;
	private int linha;
	private int coluna;
	
	public AnalisadorLexico(String input) {
		try { 
			String txtConteudo;
			txtConteudo = new String(Files.readAllBytes(Paths.get(input)), StandardCharsets.UTF_8);
			System.out.println("DEBUG --------- ");
			System.out.println(txtConteudo);
			System.out.println("-------------");
			content = txtConteudo.toCharArray();
			posicao = 0;
			linha = 1;
			coluna = 0;
		}
		catch(Exception ex) {
			ex.printStackTrace(); 
		}
	}
	
	public Token nextToken() {
		char posAtual;
		String term = "";
		Token token;
		
		if(isItOff()) {
			return null;
		}

		estado = 0;
		while(true) {
			posAtual = nextChar();
			coluna++;
			
			switch(estado) {
			case 0:
				 if(isChar(posAtual)) {
					 term += posAtual;
					 estado = 1;
				 }
				 else if(isDigit(posAtual)) {
					 estado = 2;
					 term += posAtual;
				 }
				 else if (isSpace(posAtual)) {
					 estado = 0;
				 }
				 else if (isPontuacao(posAtual)) {
					 estado = 3;
					 term += posAtual;
				 }
				 else if (isAtribuicao(posAtual)) {
					 estado = 4;
					 term += posAtual;
				 }
				 else if (isOperator(posAtual)){
					 term += posAtual;
					 token = new Token();
					 token.setTipo(Token.TK_OPERADOR);
					 token.setText(term);
					 token.setLinha(linha);
					 token.setColuna(coluna - term.length());
					 return token;
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
				else if (isSpace(posAtual) || isOperator(posAtual)|| isItOff(posAtual)){
					if (!isItOff(posAtual))
						back();
					token = new Token();
					token.setTipo(Token.TK_IDENTIFICADOR);
					token.setText(term);
					token.setLinha(linha);
					token.setColuna(coluna - term.length());
					return token;
				}
				else {
					throw new ExecaoLexica("Simbolo nao reconhecido");
				}
				break;
				
			case 2:
				if(isDigit(posAtual) || posAtual == '.') {
					estado = 2;
					term += posAtual;
				}
				else if (!isChar(posAtual) || isItOff(posAtual)){
					if(!isItOff(posAtual))
						back();
					token = new Token();
					token.setTipo(Token.TK_NUMERO);
					token.setText(term);
					token.setLinha(linha);
					token.setColuna(coluna - term.length());
					return token;
				} else {
					throw new ExecaoLexica("Numero nao reconhecido");
				}
				break;
			case 3:
				if(isPontuacao(posAtual)) {
					estado = 3;
					term += posAtual;
				}
				else if (!isDigit(posAtual) || isItOff(posAtual)){
					if (!isItOff(posAtual))
						back();
					token = new Token();
					token.setTipo(Token.TK_PONTUACAO);
					token.setText(term);
					token.setLinha(linha);
					token.setColuna(coluna - term.length());
					return token;
				} else {
					throw new ExecaoLexica("Pontuacao nao reconhecida");
				}
				break;
			case 4:
				if(isAtribuicao(posAtual)) {
					estado = 4;
					term += posAtual;
				}
				else if (!isPontuacao(posAtual) || isItOff(posAtual)){
					if (!isItOff(posAtual))
						back();
					token = new Token();
					token.setTipo(Token.TK_ATRIBUICAO);
					token.setText(term);
					token.setLinha(linha);
					token.setColuna(coluna - term.length());
					return token;
				} else {
					throw new ExecaoLexica("Atribuicao nao reconhecida");
				}
				break;
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
		return c == '>' || c == '<' || c == '!' || c == '+' || c == '-' || c == '*' || c == '/';
	}
	
	private boolean isSpace(char c) {
		if (c == '\n' || c == '\r') {
			linha++;
			coluna = 0;
		}
		return c == ' ' || c == '\t' || c == '\n' || c == '\r';
	} 
	
	private boolean isPontuacao (char c) {
		return c == ';' || c == '.' || c == ',';
	}
	
	private boolean isAtribuicao (char c) {
		return c == '=';
	}
	
	private char nextChar () {
		if (isItOff()) {
			return '\0';
		}
		return content[posicao++];
	}
	
	private boolean isItOff () {
		return posicao >= content.length;
	}

	private void back() {
		posicao --;
	}
	
	private boolean isItOff (char c) {
		return c == '\0';
	}
}
