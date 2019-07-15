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
        try {
            for (Map.Entry<String, Integer> entry:lerPontuacaoGravadaUsuario(nome).entrySet()) {
                usuario.setPontuacao(entry.getKey(),entry.getValue());
            }
        } catch (FileNotFoundException e) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("scores");
                fileWriter.write(iniciaPontuacao(tipo, pontos));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
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

    private String iniciaPontuacao(String tipo, Integer pontos) {
        String saida = usuario.getNome() + ";";
        List<String> listaTiposPonto = new ArrayList<>(Arrays.asList("moeda", "estrela", "topico", "comentario", "curtida"));
        for (String s:listaTiposPonto) {
            if (s.equals(tipo)) {
                usuario.setPontuacao(tipo, pontos);
            } else {
                usuario.setPontuacao(s, 0);
            }
            saida = saida.concat(usuario.getPontuacaoPorTipo(s).toString() + "\n");
        } return saida;
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

    private Map<String, Integer> lerPontuacaoGravadaUsuario(String nome) throws FileNotFoundException {
        Map<String, Integer> pontuacaoLida = new HashMap<>();
        List<String> listaTiposPonto = new ArrayList<>(Arrays.asList("moeda", "estrela", "topico", "comentario", "curtida"));
        for (String s:lerConteudoAquivo()) {
            if(s.contains(nome)){
                String pontosSemNomeUsuario = s.replaceAll(nome + ";", "");
                Scanner scanner = new Scanner(pontosSemNomeUsuario);
                scanner.useDelimiter(";");
                Integer indiceLeituraPorTipo = 0;
                while (scanner.hasNext()) {
                    pontuacaoLida.put(listaTiposPonto.get(indiceLeituraPorTipo),Integer.valueOf(scanner.next()));
                    indiceLeituraPorTipo++;
                }
            }
        } return pontuacaoLida;
    }

    private List<String> lerConteudoAquivo() throws FileNotFoundException {
        List<String> linhas = new ArrayList<>();
        Scanner leitura = new Scanner(new BufferedReader(new FileReader("scores")));
        leitura.useDelimiter("\n");
        while(leitura.hasNext()){
            linhas.add(leitura.next());
        } return linhas;
    }
}
