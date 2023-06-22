package analisadores;

import execoes.ExecaoSintatica;

public class AnalisadorSintatico {
	private Token token;
	private AnalisadorLexico al;
	private char[] content;
	private int posicao;
	
	public ArvoreSintatica parse() {
		return exprecao();
	}
	
	public AnalisadorSintatico(AnalisadorLexico al) {
		this.al = al;
	}
	
	private ArvoreSintatica exprecao() {
        ArvoreSintatica arvore = new ArvoreSintatica("Expressão");
        char posAtual;
        posAtual = nextChar();
        Token token = consumirToken();
        
        if (token.getTipo() == Token.TK_IDENTIFICADOR && proximoToken().getTipo() == Token.TK_ATRIBUICAO) {
            arvore.adicionarFilho(new ArvoreSintatica("Variável: " + token.getTipo()));
            arvore.adicionarFilho(new ArvoreSintatica("Operador: Atribuição"));
            consumirToken(); // Consumir o token de atribuição
            arvore.adicionarFilho(somaSubtracao());
        } else {
            arvore.adicionarFilho(somaSubtracao());
        }

        return arvore;
    }	
	
	public void Expressao() {
		T();
		El();
	}
	
	
	public void El() {
		token = al.nextToken();
		if (token != null) {
			T();
			El();
			Operador();
			Pontuacao();
		} 
	}
	
	public void T() {
		token = al.nextToken();
		if(token.getTipo() != Token.TK_IDENTIFICADOR || token.getTipo() != Token.TK_NUMERO) {
			throw new ExecaoSintatica("ID ou NUMERO inesprado, encontrado " + Token.TK_TEXT[token.getTipo()] + 
					" (" + token.getText() + ") na LINHA "+ token.getLinha() + " e na COLUNA " + token.getColuna());																
		}
	}
	
	public void Operador() {
		token = al.nextToken();
		if (token.getTipo() != Token.TK_OPERADOR) {
			throw new ExecaoSintatica("Operador inesperado, encontrado " + Token.TK_TEXT[token.getTipo()] + 
					" (" + token.getText() + ") na LINHA "+ token.getLinha() + " e na COLUNA " + token.getColuna());
		}
	}

	public void Pontuacao() {
		token = al.nextToken();
		if(token.getTipo() != Token.TK_PONTUACAO) {
			throw new ExecaoSintatica("Pontuacao inesperado, encontrado " + Token.TK_TEXT[token.getTipo()] + 
					" (" + token.getText() + ") na LINHA "+ token.getLinha() + " e na COLUNA " + token.getColuna());
		}
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
	
	 private Token consumirToken() {
	        if (posAtual < token.size()) {
	            Token token = token.getTipo();
	            posAtual++;
	            return token;
	        }

	        throw new RuntimeException("Erro de sintaxe: Fim inesperado do código");
	    }

	    private Token proximoToken() {
	        if (posAtual < token.size()) {
	            return token.get(posAtual);
	        }

	        throw new RuntimeException("Erro de sintaxe: Fim inesperado do código");
	    }
	
}
