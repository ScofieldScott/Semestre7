package principal;

import analisadores.AnalisadorLexico;
import analisadores.AnalisadorSintatico;
//import analisadores.Token;
import execoes.ExecaoLexica;
import execoes.ExecaoSintatica;

public class Main {

	public static void main(String[] args) {
		
		try {
			AnalisadorLexico sc = new AnalisadorLexico("input.isi");
			AnalisadorSintatico pa = new AnalisadorSintatico(sc);
			
					pa.Exprecao();
					System.out.println("Sucesso na Compilacao");
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
