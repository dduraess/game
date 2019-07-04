package br.serpro.gov;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArmazenaMock implements Armazenamento {

    Usuario usuario;
    List<Usuario> usuarios;

    public ArmazenaMock() {
        usuarios = new ArrayList<>();
    }

    @Override
    public String armazenarQtdePontosUsuarioPorTipo(String nome, String tipo, Integer pontos) {
        usuario = new Usuario(nome);
        usuario.setPontuacao(tipo, pontos);
        usuarios.add(usuario);
        return "o usuário " + nome + " recebeu " + usuario.getPontuacaoPorTipo(tipo) + " pontos do tipo " + tipo;
    }

    @Override
    public String recuperarQtdePontosUsuarioPorTipo(String nome, String tipo) {
        for (Usuario usuario:usuarios) {
            if (usuario.getNome().equals(nome)) {
                usuario.getPontuacaoPorTipo(tipo);
            }
        }
        return "o usuário " + nome + " tem " + usuario.getPontuacaoPorTipo(tipo) + " pontos do tipo " + tipo;
    }
}
