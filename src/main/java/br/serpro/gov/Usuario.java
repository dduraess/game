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
        this.pontuacao.put(TipoPonto.valueOf("MOEDA"), 0);
        this.pontuacao.put(TipoPonto.valueOf("ESTRELA"), 0);
        this.pontuacao.put(TipoPonto.valueOf("TOPICO"), 0);
        this.pontuacao.put(TipoPonto.valueOf("COMENTARIO"), 0);
        this.pontuacao.put(TipoPonto.valueOf("CURTIDA"), 0);
    }

    public String getNome() {
        return nome;
    }

    public Integer getPontuacaoPorTipo(String tipo){
        for (Map.Entry<TipoPonto, Integer> entry:pontuacao.entrySet()) {
            if(tipo.equals(entry.getKey().toString())) {
                return entry.getValue();
            }
        } throw new RuntimeException("Usuário não pontuou ou sem pontos deste tipo");
    }

    public Map<TipoPonto, Integer> getPontuacaoGeral() {
        return pontuacao;
    }

    public void setPontuacao(TipoPonto tipo, Integer pontos) {
        pontuacao.put(tipo, pontuacao.get(tipo) + pontos);
    }

    @Override
    public int compareTo(Usuario o) {
        return 0;
    }
}
