package analisadores;

public class AnalisadorSemantico {
	
    public boolean analisar(ArvoreSintatica arvore) {
        return verificarSemantica(arvore);
    }

    private boolean verificarSemantica(ArvoreSintatica arvore) {
        if (arvore.getValor().startsWith("Variável:")) {
            String nomeVariavel = arvore.getValor().substring(arvore.getValor().indexOf(":") + 1).trim();
            if (!existeVariavel(nomeVariavel)) {
                System.out.println("Erro semântico: Variável '" + nomeVariavel + "' não foi declarada.");
                return false;
            }
        }

        for (ArvoreSintatica filho : arvore.getFilhos()) {
            if (!verificarSemantica(filho)) {
                return false;
            }
        }

        return true;
    }

    private boolean existeVariavel(String nomeVariavel) {
        // Verificar se a variável existe no escopo atual
        // Implemente sua lógica de verificação de variáveis aqui
        // Retorna true se a variável existir, false caso contrário
        return false;
    }
}
