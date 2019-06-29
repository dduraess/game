package br.serpro.gov;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple Placar.
 */
public class PlacarTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void armazenarUsuarioRecebeuQtdePontosPorTipo() {
        Placar placar = new Placar(new ArmazenaMock());
        assertEquals("o usuário guerra recebeu 10 pontos do tipo estrela", placar.obterPontosUsuarioTipo("guerra", "estrela"));
    }
}
