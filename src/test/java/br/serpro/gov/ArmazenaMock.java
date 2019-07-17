package br.serpro.gov;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArmazenaMock implements Armazenamento {

    private Usuario usuario;
    private List<Usuario> usuarios;

    ArmazenaMock() {
        usuarios = new ArrayList<>();
    }

    @Override
    public String armazenarQtdePontosUsuarioPorTipo(String nome, String tipo, Integer pontos) {
        if (retornarUsuarioExistente(nome) == null) {
            usuario = new Usuario(nome);
            usuario.setPontuacao(TipoPonto.valueOf(tipo), pontos);
            usuarios.add(usuario);
        } else {
            retornarUsuarioExistente(nome).setPontuacao(TipoPonto.valueOf(tipo), pontos);
        }
        String conteudoAGravar = scoresComoTexto();
        try (FileWriter fileWriter = new FileWriter("scores")) {
            fileWriter.write(conteudoAGravar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "o usuário " + nome + " recebeu " + usuario.getPontuacaoPorTipo(tipo) + " pontos do tipo " + tipo;
    }

    private String scoresComoTexto() {
        String texto = "";
        for (String s:retornarTodosUsuariosComAlgumTipoDePonto()) {
            texto = texto.concat(s);
        }
        return texto;
    }

    private Usuario retornarUsuarioExistente(String nome) {
        for (Usuario usuario:usuarios) {
            if (nome.equals(usuario.getNome())) {
                return usuario;
            }
        } return null;
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
            listaPontuacoes.add(usuario.getNome() + ";");
            for (Map.Entry<TipoPonto, Integer> entry:usuario.getPontuacaoGeral().entrySet()) {
                listaPontuacoes.add(entry.getKey().toString() + ";");
                listaPontuacoes.add(entry.getValue().toString() + ";");
            }
            listaPontuacoes.add("\n");
        } return listaPontuacoes;
    }

    @Override
    public List<String> retornarTodosTiposPontoPorUsuario(String nome) {
        List<String> listaPontuacoes = new ArrayList<>();
        for (Usuario usuario:usuarios) {
            if (nome.equals(usuario.getNome())) {
                for (Map.Entry<TipoPonto, Integer> entry : usuario.getPontuacaoGeral().entrySet()) {
                    listaPontuacoes.add(entry.getKey().toString());
                    listaPontuacoes.add(entry.getValue().toString());
                }
            }
        }
        return listaPontuacoes;
    }

}
