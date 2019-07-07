package br.serpro.gov;

import java.io.*;
import java.util.*;

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
        Scanner leitura = null;
        try {
            leitura = new Scanner(new BufferedReader(new FileReader("scores")));
        } catch (FileNotFoundException e) {
//            new BufferedWriter(new FileWriter("scores"));
        }
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

    @Override
    public List<String> retornarTodosUsuariosComAlgumTipoDePonto() {
        List<String> listaPontuacoes = new ArrayList<>();
        for (Usuario usuario:usuarios) {
            for (Map.Entry<String, Integer> entry:usuario.getPontuacaoGeral().entrySet()) {
                listaPontuacoes.add(usuario.getNome());
                listaPontuacoes.add(entry.getKey());
                listaPontuacoes.add(entry.getValue().toString());
            }
        } return listaPontuacoes;
    }

    @Override
    public List<String> retornarTodosTiposPontoPorUsuario(String nome) {
        List<String> listaPontuacoes = new ArrayList<>();
        for (Usuario usuario:usuarios) {
            if (nome.equals(usuario.getNome())) {
                for (Map.Entry<String, Integer> entry : usuario.getPontuacaoGeral().entrySet()) {
                    listaPontuacoes.add(entry.getKey());
                    listaPontuacoes.add(entry.getValue().toString());
                }
            }
        }
        return listaPontuacoes;
    }
}
