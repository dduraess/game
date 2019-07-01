package br.serpro.gov;

import java.util.HashMap;
import java.util.Map;

public class Usuario {

    private String nome;

    private Map<String, Integer> pontuacao;

    public String getNome() {
        return nome;
    }

    public Usuario(String nome){
        this.nome=nome;
        this.pontuacao = new HashMap<>();
    }

    public Map<String, Integer> getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String tipo, Integer pontos) {
        pontuacao.put(tipo, pontos);
    }
}
