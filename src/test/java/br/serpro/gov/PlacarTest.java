package br.serpro.gov;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple Placar.
 */
public class PlacarTest {
    /**
     * Rigorous Test :-)
     */
    private Armazenamento armazenaMock;
    private Placar placar;

    @Before
    public void inicializa(){
        armazenaMock = new ArmazenaMock();
        placar = new Placar(armazenaMock);
    }

    @Test
    public void testeArmazenarUsuarioRecebeuQtdePontosPorTipo() {
        assertEquals("o usuário GUERRA recebeu 10 pontos do tipo ESTRELA", armazenaMock.armazenarQtdePontosUsuarioPorTipo("GUERRA", "ESTRELA",10));
    }

    @Test
    public void testeRecuperarQtdePontosUsuarioPorTipo(){
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("BATALHA", "MOEDA", 15);
        assertEquals("o usuário BATALHA tem 15 pontos do tipo MOEDA", armazenaMock.recuperarQtdePontosUsuarioPorTipo("BATALHA", "MOEDA"));
    }

    @Test
    public void testeRetornarTodosUsuariosQueReceberamAlgumTipoPonto(){

        armazenaMock.armazenarQtdePontosUsuarioPorTipo("BATALHA", "MOEDA", 15);
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("GUERRA", "ESTRELA",10);

        Usuario usuarioBatalha = new Usuario("BATALHA");
        usuarioBatalha.setPontuacaoPorTipo(TipoPonto.valueOf("MOEDA"), 15);

        Usuario usuarioGuerra = new Usuario("GUERRA");
        usuarioGuerra.setPontuacaoPorTipo(TipoPonto.valueOf("ESTRELA"), 10);

        List<Usuario> usuarios = new ArrayList<>(Arrays.asList(usuarioBatalha, usuarioGuerra));

        for (String s:armazenaMock.retornarTodosUsuariosComAlgumTipoDePonto()) {
            System.out.println(s);
        }

        assertArrayEquals(getStringListPontuacaoGeral(usuarios).toArray(), armazenaMock.retornarTodosUsuariosComAlgumTipoDePonto().toArray());
    }

    @Test
    public void testeRetornarTodosTiposPontoPorUsuario(){

        armazenaMock.armazenarQtdePontosUsuarioPorTipo("GUERRA", "MOEDA", 15);
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("GUERRA", "ESTRELA",10);

        Usuario usuarioGuerra = new Usuario("GUERRA");
        usuarioGuerra.setPontuacaoPorTipo(TipoPonto.valueOf("MOEDA"), 15);
        usuarioGuerra.setPontuacaoPorTipo(TipoPonto.valueOf("ESTRELA"), 10);

        List<Usuario> usuarios = new ArrayList<>(Arrays.asList(usuarioGuerra));

        assertArrayEquals(getStringListPontuacaoPorUsuario(usuarioGuerra.getNome(), usuarios).toArray(), armazenaMock.retornarTodosTiposPontoPorUsuario("GUERRA").toArray());
    }

//    @Test
//    public void testeGravarArquivoSemArquivoLido(){
//        File file = new File("scores");
//        file.delete();
//        armazenaMock.armazenarQtdePontosUsuarioPorTipo("DAVISON", "CURTIDA", 9);
//        assertTrue(file.canRead());
//
//    }
//
//    @Test
//    public void testeGravarArquivoLeituraPontuacaoExistenteDeOutrosUsuarios(){
//        armazenaMock.armazenarQtdePontosUsuarioPorTipo("AUGUSTO", "COMENTARIO", 5);
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("scores"))) {
//            String linha;
//            String testaSaida = "";
//            while ((linha = bufferedReader.readLine())!=null){
//                testaSaida = testaSaida.concat(bufferedReader.readLine());
//            }
//            assertTrue(linha.contains("AUGUSTO"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    private List<String> getStringListPontuacaoGeral(List<Usuario> usuarios) {
        List<String> testaListaUsuariosPontuacoes = new ArrayList<>();
        for (Usuario usuario:usuarios) {
            testaListaUsuariosPontuacoes.addAll(getStringListPontuacaoPorUsuario(usuario.getNome(), usuarios));
        }
        return testaListaUsuariosPontuacoes;
    }

    public List<String> getStringListPontuacaoPorUsuario(String nome, List<Usuario> usuarios) {
        List<String> testaListaPontuacoes = new ArrayList<>();
        for (Usuario usuario:usuarios) {
            if (nome.equals(usuario.getNome())) {
                testaListaPontuacoes.add(usuario.getNome() + ";");
                for (Map.Entry<TipoPonto, Integer> entry : usuario.getPontuacaoGeral().entrySet()) {
                    testaListaPontuacoes.add(entry.getValue().toString() + ";");
                }
//                testaListaPontuacoes.add("\n");
            }
        }
        return testaListaPontuacoes;
    }

}
