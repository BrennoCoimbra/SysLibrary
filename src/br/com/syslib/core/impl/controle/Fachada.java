package br.com.syslib.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.syslib.core.IDAO;
import br.com.syslib.core.IFachada;
import br.com.syslib.core.IStrategy;
import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.core.impl.dao.CartaoCreditoDAO;
import br.com.syslib.core.impl.dao.ClienteDAO;
import br.com.syslib.core.impl.dao.EnderecoDAO;
import br.com.syslib.core.impl.dao.EstoqueDAO;
import br.com.syslib.core.impl.dao.LivroDAO;
import br.com.syslib.core.impl.dao.UsuarioDAO;
import br.com.syslib.core.impl.negocio.ComplementarDtCadastro;
import br.com.syslib.core.impl.negocio.ValidadorClienteExistente;
import br.com.syslib.core.impl.negocio.ValidadorDadosObrigatoriosCartaoCredito;
import br.com.syslib.core.impl.negocio.ValidadorDadosObrigatoriosCliente;
import br.com.syslib.core.impl.negocio.ValidadorDadosObrigatoriosEndereco;
import br.com.syslib.core.impl.negocio.ValidadorDadosObrigatoriosLivro;
import br.com.syslib.core.impl.negocio.ValidadorDadosObrigatoriosLogin;
import br.com.syslib.core.impl.negocio.ValidadorSenha;
import br.com.syslib.core.impl.negocio.ValidarCPF;
import br.com.syslib.core.impl.negocio.ValidarLogin;
import br.com.syslib.dominio.CartaoCredito;
import br.com.syslib.dominio.Cliente;
import br.com.syslib.dominio.Endereco;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;
import br.com.syslib.dominio.Livro;
import br.com.syslib.dominio.Usuario;

public class Fachada implements IFachada {
	private Map<String, IDAO> daos;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Resultado resultado;
	
	public Fachada() {

		/* Intânciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Intânciando o Map de Regras de Negócio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();

		
		/* Criando instâncias dos DAOs a serem utilizados */
		LivroDAO livroDAO = new LivroDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
	
		
		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		daos.put(Livro.class.getName(), livroDAO);
		daos.put(Usuario.class.getName(), usuarioDAO);
		daos.put(Cliente.class.getName(), clienteDAO);
		daos.put(Endereco.class.getName(), enderecoDAO);
		daos.put(CartaoCredito.class.getName(), cartaoCreditoDAO);
		daos.put(Estoque.class.getName(), estoqueDAO);
		
	
		
		//usuario
		ValidarLogin vrLogin = new ValidarLogin();
		ValidadorDadosObrigatoriosLogin vrDadosObrigatoriosLogin = new ValidadorDadosObrigatoriosLogin();	
		ValidadorSenha vrNovaSenha = new ValidadorSenha();
		List<IStrategy> rnsVerificarLogin = new ArrayList<IStrategy>();
		List<IStrategy> rnsValidarLogin = new ArrayList<IStrategy>();
		List<IStrategy> rnsValidarNovaSenha = new ArrayList<IStrategy>();
		rnsValidarLogin.add(vrLogin);
		rnsVerificarLogin.add(vrDadosObrigatoriosLogin);
		rnsValidarNovaSenha.add(vrNovaSenha);
		Map<String, List<IStrategy>> rnsUsuario = new HashMap<String, List<IStrategy>>();
		rnsUsuario.put("CONSULTAR", rnsVerificarLogin);
		rnsUsuario. put("CONSULTAR", rnsValidarLogin);
		rnsUsuario. put("ALTERAR", rnsValidarNovaSenha);
		
		//cliente
		ValidadorClienteExistente vrClienteExistente = new ValidadorClienteExistente();
		ValidadorDadosObrigatoriosCliente vrDadosObrigatoriosCliente = new ValidadorDadosObrigatoriosCliente();
		ValidadorSenha vrSenha = new ValidadorSenha();
		ValidarCPF vrCPF = new ValidarCPF();
		List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarCliente = new ArrayList<IStrategy>();
		rnsSalvarCliente.add(vrClienteExistente);
		rnsSalvarCliente.add(vrDadosObrigatoriosCliente);
		rnsSalvarCliente.add(vrCPF);
		rnsSalvarCliente.add(vrSenha);
		rnsAlterarCliente.add(vrDadosObrigatoriosCliente);
		rnsAlterarCliente.add(vrCPF);
		Map<String, List<IStrategy>> rnsCliente = new HashMap<String, List<IStrategy>>();
		rnsCliente.put("SALVAR", rnsSalvarCliente);
		rnsCliente.put("ALTERAR", rnsAlterarCliente);		
					

		//endereco cliente
		ValidadorDadosObrigatoriosEndereco vrDadosObrigatoriosEndereco = new ValidadorDadosObrigatoriosEndereco();
		//ValidadorPreferenciaEnd vrPrefEndereco = new ValidadorPreferenciaEnd();
		List<IStrategy> rnsSalvarEndereco = new ArrayList<IStrategy>();
		rnsSalvarEndereco.add(vrDadosObrigatoriosEndereco);
		//rnsSalvarEndereco.add(vrPrefEndereco);
		Map<String, List<IStrategy>> rnsEndereco = new HashMap<String, List<IStrategy>>();
		rnsEndereco.put("SALVAR", rnsSalvarEndereco);
		rnsEndereco.put("ALTERAR", rnsSalvarEndereco);
		
		//cartao credito cliente
		ValidadorDadosObrigatoriosCartaoCredito vrDadosObrigatorioCartaoCredito = new ValidadorDadosObrigatoriosCartaoCredito();
		List<IStrategy> rnsSalvarCartaoCredito = new ArrayList<IStrategy>();
		rnsSalvarCartaoCredito.add(vrDadosObrigatorioCartaoCredito);
		Map<String, List<IStrategy>> rnsCartaoCredito = new HashMap<String, List<IStrategy>>();
		rnsCartaoCredito.put("SALVAR", rnsSalvarCartaoCredito);
		rnsCartaoCredito.put("ALTERAR", rnsSalvarCartaoCredito);
		
		//estoque
		List<IStrategy> rnsSalvarEstoque = new ArrayList<IStrategy>();
		Map<String, List<IStrategy>> rnsEstoque = new HashMap<String, List<IStrategy>>();
		rnsEstoque.put("SALVAR", rnsSalvarEstoque);
		
		/* Criando instâncias de regras de negócio a serem utilizados */
		ValidadorDadosObrigatoriosLivro vrDadosObrigatoriosLivro = new ValidadorDadosObrigatoriosLivro();
		ComplementarDtCadastro cDtCadastro = new ComplementarDtCadastro();
		
		/*
		 * Criando uma lista para conter as regras de negócio de livro quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarLivro = new ArrayList<IStrategy>();

		
		
		/* Adicionando as regras a serem utilizadas na operação salvar do livro/usuario */
		rnsSalvarLivro.add(vrDadosObrigatoriosLivro);
		rnsSalvarLivro.add(cDtCadastro);
		

		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do livro/usuario
		 */
		Map<String, List<IStrategy>> rnsLivro = new HashMap<String, List<IStrategy>>();
		
		
		
		
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do livro (lista
		 * criada na linha 61)
		 */
		rnsLivro.put("SALVAR", rnsSalvarLivro);
		rnsLivro.put("ALTERAR", rnsSalvarLivro);
		

		/*
		 * Adiciona o mapa(criado a partir da linha 74) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade
		 */
		rns.put(Livro.class.getName(), rnsLivro);
		rns.put(Usuario.class.getName(), rnsUsuario);
		rns.put(Cliente.class.getName(), rnsCliente);
		rns.put(Endereco.class.getName(), rnsEndereco);
		rns.put(CartaoCredito.class.getName(), rnsCartaoCredito);
		rns.put(EstoqueDAO.class.getName(), rnsEstoque);
	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "ALTERAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "CONSULTAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>());
		resultado.getEntidades().add(entidade);
		
		return resultado;
		}
	
	public Resultado buscar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>());
		resultado.getEntidades().add(entidade);
		
		return resultado;
		}

	private String executarRegras(EntidadeDominio entidade, String operacao) {
        String nmClasse = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();
        Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
        if (regrasOperacao != null) {
            List<IStrategy> regras = regrasOperacao.get(operacao);
            if (regras != null) {
                for (IStrategy s : regras) {
                    String m = s.processar(entidade);
                    if (m != null) {
                        msg.append(m);
                    }
                }
            }
        }
        if (msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }

}
