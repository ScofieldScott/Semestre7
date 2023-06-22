package principal;

import analisadores.AnalisadorLexico;
import analisadores.AnalisadorSintatico;
import analisadores.Token;
import execoes.ExecaoLexica;
import execoes.ExecaoSintatica;

public class Main {

	public static void main(String[] args) {
		
		try {
			AnalisadorLexico sc = new AnalisadorLexico("input.isi");
			AnalisadorSintatico parser = new AnalisadorSintatico(sc);
			Token token = null;
			
			do {
				token = sc.nextToken();
				if (token != null) 
					System.out.println(token);
				
			}while (token != null);
			parser.Exprecao();
			System.out.println("Compilado com sucesso!!");
			
		}catch(ExecaoLexica ex) {
			System.out.println("Erro Lexico " + ex.getMessage());
		}
		catch (ExecaoSintatica ex) {
			System.out.println("Erro Sintatico " + ex.getMessage());
		}
		catch (Exception ex) {
			System.out.println("Erro Generico");
		}

	}

}
