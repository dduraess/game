package br.serpro.gov;

import org.junit.Before;
import org.junit.Test;

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
    public void testeArmazenarUsuarioRecebeuQtdePontosPorTipo() {
        assertEquals("o usuário guerra recebeu 10 pontos do tipo estrela", armazenaMock.armazenarQtdePontosUsuarioPorTipo("guerra", "estrela",10));
    }

    @Test
    public void testeRecuperarQtdePontosUsuarioPorTipo(){
        armazenaMock.armazenarQtdePontosUsuarioPorTipo("batalha", "moeda", 15);
        assertEquals("O usuário batalha tem 15 pontos do tipo moeda", armazenaMock.recuperarQtdePontosUsuarioPorTipo("batalha", "moeda"));
    }
}
