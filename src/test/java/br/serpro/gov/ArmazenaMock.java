package br.serpro.gov;

import java.io.*;
import java.util.*;

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
            usuario.setPontuacaoPorTipo(TipoPonto.valueOf(tipo), pontos);
            usuarios.add(usuario);
        } else {
            usuario = retornarUsuarioExistente(nome);
            usuario.setPontuacaoPorTipo(TipoPonto.valueOf(tipo), pontos);
        }
        gravarArquivo();
        return "o usuário " + nome + " recebeu " + usuario.getPontuacaoPorTipo(tipo) + " pontos do tipo " + tipo;
    }

    private void gravarArquivo() {
        try (FileWriter fileWriter = new FileWriter("scores", true)) {
            for (String s:retornarTodosUsuariosComAlgumTipoDePonto()) {
                fileWriter.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        List<String> listaUsuariosPontuacoes = new ArrayList<>();
        for (Usuario usuario:usuarios) {
            listaUsuariosPontuacoes.addAll(retornarTodosTiposPontoPorUsuario(usuario.getNome()));
        } return listaUsuariosPontuacoes;
    }

    @Override
    public List<String> retornarTodosTiposPontoPorUsuario(String nome) {
        List<String> listaPontuacoes = new ArrayList<>();
        for (Usuario usuario:usuarios) {
            if (nome.equals(usuario.getNome())) {
                listaPontuacoes.add(usuario.getNome() + ";");
                for (Map.Entry<TipoPonto, Integer> entry : usuario.getPontuacaoGeral().entrySet()) {
                    atribuirPontosGravadosUsuario(nome);
                    listaPontuacoes.add(entry.getValue().toString() + ";");
                }
//                listaPontuacoes.add("\n");
            }
        }
        return listaPontuacoes;
    }

    private void atribuirPontosGravadosUsuario(String nome) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("scores"))) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                if (linha.contains(nome)) {

                    String linhaLidaSemNome = linha.replaceAll(nome + ";", "");
                    Scanner leituraComSeparador = new Scanner(linhaLidaSemNome);
                    leituraComSeparador.useDelimiter(";");

                    List<String> listaTiposPonto = new ArrayList<>(Arrays.asList("MOEDA", "ESTRELA", "TOPICO", "COMENTARIO", "CURTIDA"));
                    int indiceTipo = 0;

                    while (leituraComSeparador.hasNext()){
                        if(retornarUsuarioExistente(nome)==null) {
                            usuario = new Usuario(nome);
                            usuario.setPontuacaoPorTipo(TipoPonto.valueOf(listaTiposPonto.get(indiceTipo)), Integer.valueOf(leituraComSeparador.next()));
                            usuarios.add(usuario);
                        } else {
                            usuario.setPontuacaoPorTipo(TipoPonto.valueOf(listaTiposPonto.get(indiceTipo)), Integer.valueOf(leituraComSeparador.next()));
                        }
//                        System.out.println(leituraComSeparador.next());
                        indiceTipo++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
