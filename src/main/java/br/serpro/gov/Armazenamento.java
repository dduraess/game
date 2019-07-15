package br.serpro.gov;

import java.io.IOException;
import java.util.List;

public interface Armazenamento {

    String armazenarQtdePontosUsuarioPorTipo(String nome, String tipo, Integer pontos);
    String recuperarQtdePontosUsuarioPorTipo(String nome, String tipo);
    List<String> retornarTodosUsuariosComAlgumTipoDePonto();
    List<String> retornarTodosTiposPontoPorUsuario(String nome);
}
