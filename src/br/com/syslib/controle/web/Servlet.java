package br.com.syslib.controle.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.syslib.controle.web.command.impl.AddCommand;
import br.com.syslib.controle.web.command.impl.AlterarCommand;
import br.com.syslib.controle.web.command.impl.BuscarCommand;
import br.com.syslib.controle.web.command.impl.ConsultarCommand;
import br.com.syslib.controle.web.command.impl.ExcluirCommand;
import br.com.syslib.controle.web.command.impl.ICommand;
import br.com.syslib.controle.web.command.impl.RemoveCommand;
import br.com.syslib.controle.web.command.impl.SairCommand;
import br.com.syslib.controle.web.command.impl.SalvarCommand;
import br.com.syslib.controle.web.command.impl.VisualizarCommand;
import br.com.syslib.controle.web.vh.impl.AnaliseViewHelper;
import br.com.syslib.controle.web.vh.impl.CartaoCreditoViewHelper;
import br.com.syslib.controle.web.vh.impl.ClienteViewHelper;
import br.com.syslib.controle.web.vh.impl.CupomViewHelper;
import br.com.syslib.controle.web.vh.impl.EnderecoViewHelper;
import br.com.syslib.controle.web.vh.impl.EstoqueViewHelper;
import br.com.syslib.controle.web.vh.impl.FreteViewHelper;
import br.com.syslib.controle.web.vh.impl.IViewHelper;
import br.com.syslib.controle.web.vh.impl.LivroViewHelper;
import br.com.syslib.controle.web.vh.impl.PedidoViewHelper;
import br.com.syslib.controle.web.vh.impl.RelatorioViewHelper;
import br.com.syslib.controle.web.vh.impl.UsuarioViewHelper;
import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.dominio.EntidadeDominio;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;

	/**
	 * Default constructor.
	 */
	public Servlet() {
		/**
		 * Utilizando o command para chamar a fachada e indexando cada command pela
		 * operação garantimos que esta servelt atenderá qualquer operação
		 */
		commands = new HashMap<String, ICommand>();
		
		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CANCEL", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("VISUALIZAR", new VisualizarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("SAIR", new SairCommand());
		commands.put("BUSCAR", new BuscarCommand());
		commands.put("ADD", new AddCommand());
		commands.put("REMOVE", new RemoveCommand());
		commands.put("CUPOMDESCONTO", new ConsultarCommand());
		commands.put("FRMPGTO", new ConsultarCommand());
		/**
		 * Utilizando o ViewHelper para tratar especificações de qualquer tela e
		 * indexando cada viewhelper pela url em que esta servlet é chamada no form
		 * garantimos que esta servelt atenderá qualquer entidade
		 */
		vhs = new HashMap<String, IViewHelper>();
		
		/**
		 * A chave do mapa é o mapeamento da servlet para cada form que está configurado
		 * no web.xml e sendo utilizada no action do html
		 */
		vhs.put("/SysLibrary/autenticado/adm/SalvarLivro", new LivroViewHelper());
		vhs.put("/SysLibrary/autenticado/adm/SalvarCliente", new ClienteViewHelper());
		vhs.put("/SysLibrary/SalvarUsuario", new UsuarioViewHelper());
		vhs.put("/SysLibrary/SalvarCliente", new ClienteViewHelper());
		vhs.put("/SysLibrary/autenticado/SalvarCliente", new ClienteViewHelper());
		vhs.put("/SysLibrary/autenticado/AtualizarPerfil", new UsuarioViewHelper());
		vhs.put("/SysLibrary/autenticado/AlterarSenha", new UsuarioViewHelper());
		vhs.put("/SysLibrary/autenticado/Login", new UsuarioViewHelper());
		vhs.put("/SysLibrary/SairSys", new UsuarioViewHelper());
		vhs.put("/SysLibrary/autenticado/SalvarEndereco", new EnderecoViewHelper());
		vhs.put("/SysLibrary/autenticado/SalvarCartao", new CartaoCreditoViewHelper());
		vhs.put("/SysLibrary/autenticado/adm/SalvarEstoque", new EstoqueViewHelper());
		vhs.put("/SysLibrary/SalvarCarrinho", new PedidoViewHelper());
		vhs.put("/SysLibrary/autenticado/CalcularFrete", new FreteViewHelper());
		vhs.put("/SysLibrary/autenticado/CalcularFrmPgto", new PedidoViewHelper());
		vhs.put("/SysLibrary/ValidarCupomPromocional", new PedidoViewHelper());
		vhs.put("/SysLibrary/autenticado/ValidarCupomTroca", new CupomViewHelper());
		vhs.put("/SysLibrary/autenticado/GerarCupomTroca", new CupomViewHelper());
		vhs.put("/SysLibrary/autenticado/adm/GerarCupomTroca", new CupomViewHelper());
		vhs.put("/SysLibrary/autenticado/adm/ADD", new CupomViewHelper());
		vhs.put("/SysLibrary/autenticado/ConsultarCupomTroca", new CupomViewHelper());
		vhs.put("/SysLibrary/autenticado/adm/ConsultarCupomTroca", new CupomViewHelper());
		vhs.put("/SysLibrary/autenticado/BuscarCupomTroca", new CupomViewHelper());
		vhs.put("/SysLibrary/autenticado/SalvarPedido", new PedidoViewHelper());
		vhs.put("/SysLibrary/autenticado/adm/SalvarPedido", new PedidoViewHelper());
		vhs.put("/SysLibrary/autenticado/adm/SalvarGrafico", new RelatorioViewHelper());
		vhs.put("/SysLibrary/autenticado/adm/SalvarAnalise", new AnaliseViewHelper());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcessRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcessRequest(req, resp);
	}

	protected void doProcessRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
				// Obtêm a uri que invocou esta servlet (O que foi definido no methdo do form html)
				String uri = request.getRequestURI();
				
				// Obtêm um viewhelper indexado pela uri que invocou esta servlet
				IViewHelper vh = vhs.get(uri);
				
				// O viewhelper retorna a entidade especifica para a tela que chamou esta
				// servlet
				EntidadeDominio entidade = vh.getEntidade(request);
				
				// Obtêm a operação executada
				String operacao = request.getParameter("operacao");
				
				// Obtêm o command para executar a respectiva operação
				ICommand command = commands.get(operacao);
				
				/*
				 * Executa o command que chamará a fachada para executar a operação requisitada
				 * o retorno é uma instância da classe resultado que pode conter mensagens derro
				 * ou entidades de retorno
				 */
				Resultado resultado = command.execute(entidade);
				
				/*
				 * Executa o método setView do view helper específico para definir como deverá
				 * ser apresentado o resultado para o usuário
				 */
				vh.setView(resultado, request, response);
	}
}
