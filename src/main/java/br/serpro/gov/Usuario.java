package br.serpro.gov;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Usuario implements Comparable<Usuario> {

    private String nome;
    private Map<TipoPonto, Integer> pontuacao;

    Usuario(String nome){
        this.nome=nome;
        this.pontuacao = new EnumMap<>(TipoPonto.class);
        this.pontuacao.put(TipoPonto.MOEDA, 0);
        this.pontuacao.put(TipoPonto.ESTRELA, 0);
        this.pontuacao.put(TipoPonto.TOPICO, 0);
        this.pontuacao.put(TipoPonto.COMENTARIO, 0);
        this.pontuacao.put(TipoPonto.CURTIDA, 0);
    }

    String getNome() {
        return nome;
    }

    Integer getPontuacaoPorTipo(String tipo){
        for (Map.Entry<TipoPonto, Integer> entry:pontuacao.entrySet()) {
            if(tipo.equals(entry.getKey().toString())) {
                return entry.getValue();
            }
        } throw new RuntimeException("Usuário não pontuou ou sem pontos deste tipo");
    }

    Map<TipoPonto, Integer> getPontuacaoGeral() {
        return pontuacao;
    }

    public void setPontuacaoPorTipo(TipoPonto tipo, Integer pontos) {
        pontuacao.put(tipo, pontuacao.get(tipo) + pontos);
    }

    @Override
    public int compareTo(Usuario o) {
        return 0;
    }
}
