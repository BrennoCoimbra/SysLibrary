package br.com.syslib.core.impl.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.GeradorGrafico;
import br.com.syslib.dominio.Relatorio;
import br.com.syslib.enuns.TipoRelatorio;

public class RelatorioDAO extends AbstractJdbcDAO {

	public RelatorioDAO() {
		super("", "");
		
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		List<Relatorio> relatorios;
		Relatorio relatorio = (Relatorio) entidade;
		
		if (relatorio.getTipoRelatorio().equals(TipoRelatorio.DIA)) {
			relatorios = getRelatorioByDia(relatorio);
			GeradorGrafico.geraRelatorioLinhasByDia(relatorios);
		} else if (relatorio.getTipoRelatorio().equals(TipoRelatorio.MES)) {
			relatorios = getRelatorioByMes(relatorio);
			GeradorGrafico.geraRelatorioLinhasByMes(relatorios);
		}else if (relatorio.getTipoRelatorio().equals(TipoRelatorio.ANO)) {
			relatorios = getRelatorioByAno(relatorio);
			GeradorGrafico.geraRelatorioLinhasByAno(relatorios);
		} else if (relatorio.getTipoRelatorio().equals(TipoRelatorio.SEMANAL)) {
			relatorios = getRelatorioBySemanal(relatorio);
			GeradorGrafico.geraRelatorioLinhasBySemanal(relatorios);
		}
		

	}
	
	public List<Relatorio> getRelatorioByDia(Relatorio rel) throws SQLException {
		openConnection();
		PreparedStatement pst;
		ResultSet rs;
		List<Relatorio> relatorios = new ArrayList<Relatorio>();
		Relatorio relatorio;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
		sql.append("T0.item_titulo, ");
		sql.append("T1.ped_dtCadastro, ");
		sql.append("SUM(T0.item_qtde) AS Qtde, ");
		sql.append("SUM(T0.item_subtotal) AS Valor ");
		sql.append("FROM item_pedido T0 ");
		sql.append("INNER JOIN pedido T1 ON T1.ped_id = T0.ped_item_id ");
		sql.append("WHERE T1.ped_dtCadastro BETWEEN ? AND ? ");
		sql.append("GROUP BY T0.item_titulo,T1.ped_dtCadastro");
		
		/*
		sql.append("SELECT ");
		sql.append("T0.item_titulo, ");
		sql.append("SUM(T0.item_qtde) AS Qtde, ");
		sql.append("SUM(T0.item_subtotal) AS Valor ");
		sql.append("FROM item_pedido T0 ");
		sql.append("INNER JOIN pedido T1 ON T1.ped_id = T0.ped_item_id ");
		sql.append("WHERE T1.ped_dtCadastro BETWEEN ? AND ? ");
		sql.append("GROUP BY T0.item_titulo");
		*/
		pst = connection.prepareStatement(sql.toString());
		pst.setDate(1, new java.sql.Date(rel.getDataInicial().getTime()));
		pst.setDate(2, new java.sql.Date(rel.getDataFinal().getTime()));
		
		rs = pst.executeQuery();
		
		while (rs.next()) {
			String titulo = rs.getString("T0.item_titulo");
			Date data = rs.getDate("T1.ped_dtCadastro");
			double qtde = rs.getDouble("Qtde");
			double valor = rs.getDouble("Valor");
			
			relatorio = new Relatorio();
			relatorio.setTitulo(titulo);
			relatorio.setData(data);
			relatorio.setQtde(qtde);
			relatorio.setValor(valor);
			relatorio.setTipoRelatorio(TipoRelatorio.DIA);
			
			relatorios.add(relatorio);
		}
		
	
		rs.close();
		pst.close();
		connection.close();
		
		return relatorios;
	}
	
	private List<Relatorio> getRelatorioByMes(Relatorio rel) throws SQLException {
		openConnection();
		PreparedStatement pst;
		ResultSet rs;
		List<Relatorio> relatorios = new ArrayList<Relatorio>();
		Relatorio relatorio;
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime(rel.getDataInicial());
		
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.setTime(rel.getDataFinal());
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("T0.item_titulo, ");
		sql.append("YEAR(T1.ped_dtCadastro), ");
		sql.append("MONTH(T1.ped_dtCadastro), ");
		sql.append("SUM(T0.item_qtde) AS Qtde, ");
		sql.append("SUM(T0.item_subtotal) AS Valor ");
		sql.append("FROM item_pedido T0 ");
		sql.append("INNER JOIN pedido T1 ON T1.ped_id = T0.ped_item_id ");
		sql.append("WHERE MONTH(T1.ped_dtCadastro) BETWEEN MONTH(?) AND MONTH(?) AND YEAR(T1.ped_dtCadastro) BETWEEN YEAR(?) AND YEAR(?) ");
		sql.append("GROUP BY T0.item_titulo,MONTH(T1.ped_dtCadastro),YEAR(T1.ped_dtCadastro)");
		
		pst = connection.prepareStatement(sql.toString());
		
		pst.setDate(1, new java.sql.Date(rel.getDataInicial().getTime()));
		pst.setDate(2, new java.sql.Date(rel.getDataFinal().getTime()));
		pst.setDate(3, new java.sql.Date(rel.getDataInicial().getTime()));
		pst.setDate(4, new java.sql.Date(rel.getDataFinal().getTime()));
		
		rs = pst.executeQuery();
		
		while (rs.next()) {
			String titulo = rs.getString("T0.item_titulo");
			int mes = rs.getInt("MONTH(T1.ped_dtCadastro)");
			int ano = rs.getInt("YEAR(T1.ped_dtCadastro)");
			double qtde = rs.getDouble("Qtde");
			double valor = rs.getDouble("Valor");
			
			relatorio = new Relatorio();
			relatorio.setTitulo(titulo);
			relatorio.setAno(ano);
			relatorio.setMes(mes);
			relatorio.setQtde(qtde);
			relatorio.setValor(valor);
			relatorio.setTipoRelatorio(TipoRelatorio.MES);
			
			relatorios.add(relatorio);
		}
		
		rs.close();
		pst.close();
		connection.close();
		
		return relatorios;
	}

	private List<Relatorio> getRelatorioByAno(Relatorio rel) throws SQLException {
		openConnection();
		PreparedStatement pst;
		ResultSet rs;
		List<Relatorio> relatorios = new ArrayList<Relatorio>();
		Relatorio relatorio;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("T0.item_titulo, ");
		sql.append("YEAR(T1.ped_dtCadastro), ");
		sql.append("SUM(T0.item_qtde) AS Qtde, ");
		sql.append("SUM(T0.item_subtotal) AS Valor ");
		sql.append("FROM item_pedido T0 ");
		sql.append("INNER JOIN pedido T1 ON T1.ped_id = T0.ped_item_id ");
		sql.append("WHERE YEAR(T1.ped_dtCadastro) BETWEEN YEAR(?) AND YEAR(?) ");
		sql.append("GROUP BY T0.item_titulo,YEAR(T1.ped_dtCadastro)");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setDate(1, new java.sql.Date(rel.getDataInicial().getTime()));
		pst.setDate(2, new java.sql.Date(rel.getDataFinal().getTime()));
		
		rs = pst.executeQuery();
		
		while (rs.next()) {
			String titulo = rs.getString("T0.item_titulo");
			int ano = rs.getInt("YEAR(T1.ped_dtCadastro)");
			double qtde = rs.getDouble("Qtde");
			double valor = rs.getDouble("Valor");
			
			relatorio = new Relatorio();
			relatorio.setTitulo(titulo);
			relatorio.setAno(ano);
			relatorio.setQtde(qtde);
			relatorio.setValor(valor);
			relatorio.setTipoRelatorio(TipoRelatorio.ANO);
			
			relatorios.add(relatorio);
		}
		
		rs.close();
		pst.close();
		connection.close();
		
		return relatorios;
	}
	
	
	private List<Relatorio> getRelatorioBySemanal(Relatorio rel) throws SQLException {
		openConnection();
		PreparedStatement pst;
		ResultSet rs;
		List<Relatorio> relatorios = new ArrayList<Relatorio>();
		Relatorio relatorio;
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime(rel.getDataInicial());
		
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.setTime(rel.getDataFinal());
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("T0.item_titulo, ");
		sql.append("YEAR(T1.ped_dtCadastro), ");
		sql.append("WEEK(T1.ped_dtCadastro), ");
		sql.append("SUM(T0.item_qtde) AS Qtde, ");
		sql.append("SUM(T0.item_subtotal) AS Valor ");
		sql.append("FROM item_pedido T0 ");
		sql.append("INNER JOIN pedido T1 ON T1.ped_id = T0.ped_item_id ");
		sql.append("WHERE WEEK(T1.ped_dtCadastro) BETWEEN WEEK(?) AND WEEK(?) AND YEAR(T1.ped_dtCadastro) BETWEEN YEAR(?) AND YEAR(?) ");
		sql.append("GROUP BY T0.item_titulo,WEEK(T1.ped_dtCadastro),YEAR(T1.ped_dtCadastro)");
		
		pst = connection.prepareStatement(sql.toString());
		
		pst.setDate(1, new java.sql.Date(rel.getDataInicial().getTime()));
		pst.setDate(2, new java.sql.Date(rel.getDataFinal().getTime()));
		pst.setDate(3, new java.sql.Date(rel.getDataInicial().getTime()));
		pst.setDate(4, new java.sql.Date(rel.getDataFinal().getTime()));
		
		rs = pst.executeQuery();
		
		while (rs.next()) {
			String titulo = rs.getString("T0.item_titulo");
			int mes = rs.getInt("WEEK(T1.ped_dtCadastro)");
			int ano = rs.getInt("YEAR(T1.ped_dtCadastro)");
			double qtde = rs.getDouble("Qtde");
			double valor = rs.getDouble("Valor");
			
			relatorio = new Relatorio();
			relatorio.setTitulo(titulo);
			relatorio.setAno(ano);
			relatorio.setMes(mes);
			relatorio.setQtde(qtde);
			relatorio.setValor(valor);
			relatorio.setTipoRelatorio(TipoRelatorio.MES);
			
			relatorios.add(relatorio);
		}
		
		rs.close();
		pst.close();
		connection.close();
		
		return relatorios;
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		

	}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		
		return false;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		
		return null;
	}

}
