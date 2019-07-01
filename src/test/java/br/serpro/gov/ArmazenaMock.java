package br.serpro.gov;

import java.util.ArrayList;
import java.util.List;

public class ArmazenaMock implements Armazenamento {

    Usuario usuario;
    List<Usuario> usuarios;

    @Override
    public String armazenarQtdePontosUsuarioPorTipo(String nome, String tipo, Integer pontos) {
        usuario = new Usuario(nome);
        usuario.setPontuacao(tipo, pontos);
        List<Integer> listaPontos = new ArrayList<>(usuario.getPontuacao().values());
        List<String> listaTipos = new ArrayList<>(usuario.getPontuacao().keySet());
        return "o usuário " + nome + " recebeu " + listaPontos.get(0).toString() + " pontos do tipo " + listaTipos.get(0);
    }

    @Override
    public String recuperarQtdePontosUsuarioPorTipo(String nome, String tipo) {
        for (Usuario usuario:usuarios) {
            if (usuario.getNome().equals(nome)) {
//                usuario.getPontuacao().forEach(tipo );
            }
        }
        return null;
    }
}
