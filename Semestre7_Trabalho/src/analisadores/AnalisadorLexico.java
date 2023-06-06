package analisadores;

import java.util.ArrayList;
import java.util.List;
import entities.enums.TipoToken;

public class AnalisadorLexico {
	private String input;
    private int indiceAtual;

    public AnalisadorLexico(String input) {
        this.input = input;
        this.indiceAtual = 0;
    }

    public List<Token> analisar() {
        List<Token> tokens = new ArrayList<>();

        while (indiceAtual < input.length()) {
            char caractereAtual = input.charAt(indiceAtual);

            if (Character.isDigit(caractereAtual)) {
                tokens.add(analisarNumero());
            } else if (Character.isLetter(caractereAtual)) {
                tokens.add(analisarIdentificador());
            } else if (caractereAtual == '+' || caractereAtual == '-' || caractereAtual == '*' || caractereAtual == '/') {
                tokens.add(new Token(TipoToken.OPERADOR, Character.toString(caractereAtual)));
                indiceAtual++;
            } else if (caractereAtual == '=') {
                tokens.add(new Token(TipoToken.ATRIBUICAO, Character.toString(caractereAtual)));
                indiceAtual++;
            } else if (caractereAtual == ';') {
                tokens.add(new Token(TipoToken.PONTO_VIRGULA, Character.toString(caractereAtual)));
                indiceAtual++;
            } else if (caractereAtual == ' ') {
                indiceAtual++;
            } else {
                throw new RuntimeException("Erro léxico: Caractere inválido encontrado");
            }
        }

        return tokens;
    }

    private Token analisarNumero() {
        StringBuilder valor = new StringBuilder();

        while (indiceAtual < input.length() && Character.isDigit(input.charAt(indiceAtual))) {
            valor.append(input.charAt(indiceAtual));
            indiceAtual++;
        }

        return new Token(TipoToken.NUMERO, valor.toString());
    }

    private Token analisarIdentificador() {
        StringBuilder valor = new StringBuilder();

        while (indiceAtual < input.length() && Character.isLetterOrDigit(input.charAt(indiceAtual))) {
            valor.append(input.charAt(indiceAtual));
            indiceAtual++;
        }

        return new Token(TipoToken.IDENTIFICADOR, valor.toString());
    }
}