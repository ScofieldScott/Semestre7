package analisadores;

import java.util.List;
import entities.enums.TipoToken;

public class AnalisadorSintatico {
    private List<Token> tokens;
    private int indiceAtual;

    public AnalisadorSintatico(List<Token> tokens) {
        this.tokens = tokens;
        this.indiceAtual = 0;
    }

    public ArvoreSintatica parse() {
        return expressao();
    }

    private ArvoreSintatica expressao() {
        ArvoreSintatica arvore = new ArvoreSintatica("Expressão");

        Token token = consumirToken();
        if (token.getTipo() == TipoToken.IDENTIFICADOR && proximoToken().getTipo() == TipoToken.ATRIBUICAO) {
            arvore.adicionarFilho(new ArvoreSintatica("Variável: " + token.getValor()));
            arvore.adicionarFilho(new ArvoreSintatica("Operador: Atribuição"));
            consumirToken(); // Consumir o token de atribuição
            arvore.adicionarFilho(somaSubtracao());
        } else {
            arvore.adicionarFilho(somaSubtracao());
        }

        return arvore;
    }

    private ArvoreSintatica somaSubtracao() {
        ArvoreSintatica arvore = multiplicacaoDivisao();

        Token token = proximoToken();
        while (token.getTipo() == TipoToken.SOMA || token.getTipo() == TipoToken.SUBTRACAO) {
            arvore.adicionarFilho(new ArvoreSintatica("Operador: " + token.getValor()));
            consumirToken();
            arvore.adicionarFilho(multiplicacaoDivisao());
            token = proximoToken();
        }

        return arvore;
    }

    private ArvoreSintatica multiplicacaoDivisao() {
        ArvoreSintatica arvore = fator();

        Token token = proximoToken();
        while (token.getTipo() == TipoToken.MULTIPLICACAO || token.getTipo() == TipoToken.DIVISAO) {
            arvore.adicionarFilho(new ArvoreSintatica("Operador: " + token.getValor()));
            consumirToken();
            arvore.adicionarFilho(fator());
            token = proximoToken();
        }

        return arvore;
    }

    private ArvoreSintatica fator() {
        Token token = consumirToken();
        ArvoreSintatica arvore;

        if (token.getTipo() == TipoToken.NUMERO) {
            arvore = new ArvoreSintatica("Número: " + token.getValor());
        } else if (token.getTipo() == TipoToken.IDENTIFICADOR) {
            arvore = new ArvoreSintatica("Variável: " + token.getValor());
        } else {
            throw new RuntimeException("Erro de sintaxe: Token inesperado");
        }

        return arvore;
    }

    private Token consumirToken() {
        if (indiceAtual < tokens.size()) {
            Token token = tokens.get(indiceAtual);
            indiceAtual++;
            return token;
        }

        throw new RuntimeException("Erro de sintaxe: Fim inesperado do código");
    }

    private Token proximoToken() {
        if (indiceAtual < tokens.size()) {
            return tokens.get(indiceAtual);
        }

        throw new RuntimeException("Erro de sintaxe: Fim inesperado do código");
    }
}