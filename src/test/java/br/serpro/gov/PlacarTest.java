package br.serpro.gov;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
    public void armazenarUsuarioRecebeuQtdePontosPorTipo() {
        assertEquals("o usuário guerra recebeu 10 pontos do tipo estrela", armazenaMock.armazenarQtdePontosUsuarioPorTipo("guerra", "estrela",10));
    }

    @Test
    public void recuperarQtdePontosUsuarioPorTipo(){
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("batalha", "moeda", 15);
        assertEquals("o usuário batalha tem 15 pontos do tipo moeda", armazenaMock.recuperarQtdePontosUsuarioPorTipo("batalha", "moeda"));
    }

    @Test
    public void retornarTodosUsuariosQueReceberamAlgumTipoPonto(){

        armazenaMock.armazenarQtdePontosUsuarioPorTipo("batalha", "moeda", 15);
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("guerra", "estrela",10);

        Usuario usuarioBatalha = new Usuario("batalha");
        usuarioBatalha.setPontuacao("moeda", 15);

        Usuario usuarioGuerra = new Usuario("guerra");
        usuarioGuerra.setPontuacao("estrela", 10);

        List<Usuario> usuarios = new ArrayList<>(Arrays.asList(usuarioBatalha, usuarioGuerra));

        assertArrayEquals(getStrings(usuarios).toArray(), armazenaMock.retornarTodosUsuariosComAlgumTipoDePonto().toArray());
    }

    private List<String> getStrings(List<Usuario> usuarios) {
        List<String> testaListaPontuacoes = null;
        for (Usuario usuario:usuarios) {
            testaListaPontuacoes = new ArrayList<>(Arrays.asList(usuario.getNome()));
            for (Map.Entry<String, Integer> entry: usuario.getPontuacaoGeral().entrySet()) {
                testaListaPontuacoes.add(entry.getKey());
                testaListaPontuacoes.add(entry.getValue().toString());
            }
        }
        return testaListaPontuacoes;
    }

}
