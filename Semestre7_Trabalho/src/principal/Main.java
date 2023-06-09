package principal;

import analisadores.ArvoreSintatica;
import analisadores.AnalisadorSintatico;
import analisadores.AnalisadorLexico;
import analisadores.AnalisadorSemantico;
import analisadores.Token;
import execoes.ExecaoLexica;

public class Main {
    public static void main(String[] args) {
        
        // Analisador Léxico
        try {
        	AnalisadorLexico lexer = new AnalisadorLexico("input.isi");
        	Token token = null;
        	
        	do {
        		token = lexer.analisar();
        		if(token != null)
        			System.out.println(token);
        	}while (token != null);
        	
        }catch (ExecaoLexica ex) {
        	System.out.println("Erro Lexico " + ex.getMessage());
        }catch (Exception ex) {
        	System.out.println("Erro Generico");
        }

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

