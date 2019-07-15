package br.serpro.gov;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
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
        assertEquals("o usuário guerra recebeu 10 pontos do tipo estrela", armazenaMock.armazenarQtdePontosUsuarioPorTipo("guerra", "estrela",10));
    }

    @Test
    public void testeRecuperarQtdePontosUsuarioPorTipo(){
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("batalha", "moeda", 15);
        assertEquals("o usuário batalha tem 15 pontos do tipo moeda", armazenaMock.recuperarQtdePontosUsuarioPorTipo("batalha", "moeda"));
    }

    @Test
    public void testeRetornarTodosUsuariosQueReceberamAlgumTipoPonto(){

        armazenaMock.armazenarQtdePontosUsuarioPorTipo("batalha", "moeda", 15);
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("guerra", "estrela",10);

        Usuario usuarioBatalha = new Usuario("batalha");
        usuarioBatalha.setPontuacao("moeda", 15);

        Usuario usuarioGuerra = new Usuario("guerra");
        usuarioGuerra.setPontuacao("estrela", 10);

        List<Usuario> usuarios = new ArrayList<>(Arrays.asList(usuarioBatalha, usuarioGuerra));

        assertArrayEquals(getStringListPontuacaoGeral(usuarios).toArray(), armazenaMock.retornarTodosUsuariosComAlgumTipoDePonto().toArray());
    }

    @Test
    public void testeRetornarTodosTiposPontoPorUsuario(){

        armazenaMock.armazenarQtdePontosUsuarioPorTipo("guerra", "moeda", 15);
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("guerra", "estrela",10);

        Usuario usuarioGuerra = new Usuario("guerra");
        usuarioGuerra.setPontuacao("moeda", 15);
        usuarioGuerra.setPontuacao("estrela", 10);

        List<Usuario> usuarios = new ArrayList<>(Arrays.asList(usuarioGuerra));

        assertArrayEquals(getStringListPontuacaoPorUsuario(usuarioGuerra.getNome(), usuarios).toArray(), armazenaMock.retornarTodosTiposPontoPorUsuario("guerra").toArray());
    }

    @Test
    public void testeGravarArquivoSemArquivoLido(){
        File file = new File("scores");
        file.delete();
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("davison", "curtida", 9);
        assertTrue(file.canRead());

    }



    private List<String> getStringListPontuacaoGeral(List<Usuario> usuarios) {
        List<String> testaListaPontuacoes = new ArrayList<>();
        for (Usuario usuario:usuarios) {
            for (Map.Entry<String, Integer> entry: usuario.getPontuacaoGeral().entrySet()) {
                testaListaPontuacoes.add(usuario.getNome());
                testaListaPontuacoes.add(entry.getKey());
                testaListaPontuacoes.add(entry.getValue().toString());
            }
        }
        return testaListaPontuacoes;
    }

    public List<String> getStringListPontuacaoPorUsuario(String nome, List<Usuario> usuarios) {
        List<String> testaListaPontuacoes = new ArrayList<>();
        for (Usuario usuario:usuarios) {
            if (nome.equals(usuario.getNome())) {
                for (Map.Entry<String, Integer> entry : usuario.getPontuacaoGeral().entrySet()) {
                    testaListaPontuacoes.add(entry.getKey());
                    testaListaPontuacoes.add(entry.getValue().toString());
                }
            }
        }
        return testaListaPontuacoes;
    }

}
