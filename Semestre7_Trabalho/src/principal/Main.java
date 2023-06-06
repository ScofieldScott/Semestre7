package principal;

import java.util.List;
import analisadores.ArvoreSintatica;
import analisadores.AnalisadorSintatico;
import analisadores.AnalisadorLexico;
import analisadores.AnalisadorSemantico;
import analisadores.Token;


public class Main {
    public static void main(String[] args) {
        String input = "int x = 10 + 5;";
        
        // Analisador Léxico
        AnalisadorLexico lexer = new AnalisadorLexico(input);
        List<Token> tokens = lexer.analisar();
        System.out.println("Tokens:");
        for (Token token : tokens) {
            System.out.println(token);
        }
        System.out.println();

        // Analisador Sintático
        AnalisadorSintatico parser = new AnalisadorSintatico(tokens);
        ArvoreSintatica sintacticTree = parser.parse();
        System.out.println("Árvore Sintática:");
        sintacticTree.imprimir();
        System.out.println();

        // Analisador Semântico
        AnalisadorSemantico semanticAnalyzer = new AnalisadorSemantico();
        boolean passouAnaliseSemantica = semanticAnalyzer.analisar(sintacticTree);
        if (passouAnaliseSemantica) {
            System.out.println("Análise Semântica: Sucesso");
        } else {
            System.out.println("Análise Semântica: Erro(s) detectado(s)");
        }
    }
}

