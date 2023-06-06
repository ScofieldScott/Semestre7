package analisadores;

import java.util.ArrayList;
import java.util.List;

public class ArvoreSintatica{
    private String valor;
    private List<ArvoreSintatica> filhos;

    public ArvoreSintatica(String valor) {
        this.valor = valor;
        this.filhos = new ArrayList<>();
    }

    public void adicionarFilho(ArvoreSintatica filho) {
        filhos.add(filho);
    }

    public void imprimir() {
        imprimir("", true);
    }

    private void imprimir(String prefixo, boolean isUltimoFilho) {
        System.out.print(prefixo);
        System.out.print(isUltimoFilho ? "└── " : "├── ");
        System.out.println(valor);

        for (int i = 0; i < filhos.size() - 1; i++) {
            filhos.get(i).imprimir(prefixo + (isUltimoFilho ? "    " : "│   "), false);
        }

        if (filhos.size() > 0) {
            filhos.get(filhos.size() - 1).imprimir(prefixo + (isUltimoFilho ? "    " : "│   "), true);
        }
    }

	public String getValor() {
		return valor;
	}

	public List<ArvoreSintatica> getFilhos() {
		return filhos;
	}
    
    
}
