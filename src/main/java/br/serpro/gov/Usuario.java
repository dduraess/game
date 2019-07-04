package br.serpro.gov;

import java.util.HashMap;
import java.util.Map;

public class Usuario implements Comparable<Usuario> {



    public enum Tipo {
        MOEDA, ESTRELA, TOPICO, COMENTARIO, CURTIDA;
    }
    private String nome;

    private Map<String, Integer> pontuacao;

    public Usuario(String nome){
        this.nome=nome;
        this.pontuacao = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public Integer getPontuacaoPorTipo(String tipo){
        for (Map.Entry<String, Integer> entry:pontuacao.entrySet()) {
            if(tipo.equals(entry.getKey())) {
                return entry.getValue();
            }
        } throw new RuntimeException("Usuário não pontuou ou sem pontos deste tipo");
    }

    public Map<String, Integer> getPontuacaoGeral() {
        return pontuacao;
    }

    public void setPontuacao(String tipo, Integer pontos) {
        pontuacao.put(tipo, pontos);
    }

    @Override
    public int compareTo(Usuario o) {
        return 0;
    }
}
