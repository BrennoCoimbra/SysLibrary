package br.com.syslib.core;

import java.sql.SQLException;
import java.util.List;

import br.com.syslib.dominio.EntidadeDominio;


public interface IDAO {
	public void salvar(EntidadeDominio entidade) throws SQLException;
	public void alterar(EntidadeDominio entidade)throws SQLException;
	public void excluir(EntidadeDominio entidade)throws SQLException;
	public boolean verificarCadastro(EntidadeDominio entidade)throws SQLException;
	public List<EntidadeDominio> consultar(EntidadeDominio entidade)throws SQLException;
}
