package br.com.syslib.core.impl.negocio;

import java.util.List;
import java.util.regex.Pattern;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.UsuarioDAO;
import br.com.syslib.dominio.Cliente;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Usuario;

public class ValidadorSenha implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		StringBuilder erros = new StringBuilder();

		if (entidade instanceof Cliente) {
			Cliente cliente = (Cliente) entidade;
			String senha1 = cliente.getSenha1();
			String senha2 = cliente.getSenha2();
			GeradorHash hash = new GeradorHash();

			if (cliente.getId() == null) {
				if (!"".equals(senha1) && !"".equals(senha2)) {
					if(cliente.getSenha1().equals(cliente.getSenha2())) {
					// Criterio 1:
					if (senha1.length() < 8 || senha2.length() < 8)
						return "Senha deve conter 8 caracteres ou mais.\n";
					// Criterio 2:
					Pattern p2 = Pattern.compile("[A-Z]");
					if (!p2.matcher(senha1).find() || !p2.matcher(senha2).find())
						return "Senha deve conter ter letras maiúsculas.\n";
					// Criterio 3:
					Pattern p3 = Pattern.compile("[a-z]");
					if (!p3.matcher(senha1).find() || !p2.matcher(senha2).find())
						return "Senha  deve conter letras minúsculas.\n";
					// Criterio 4:
					Pattern p4 = Pattern.compile("[0-9]");
					if (!p4.matcher(senha1).find() || !p2.matcher(senha2).find())
						return "Senha deve conter pelo menos um valor numerico.\n";
					// Critério 5:
					Pattern p5 = Pattern.compile("[-+*/=%@$#]");
					if (!p5.matcher(senha1).find() || !p2.matcher(senha2).find())
						return "Senha deve conter um dos seguintes caracteres especiais: + - * / = %\n";
					if (erros.length() > 0) {
						return erros.toString();
					} else {
						cliente.setSenha(hash.senhaHash(cliente.getSenha1()));
						return null;
					}
				} else {
					return "As senhas estão diferentes!";
				}

				} else {
					return "Senha vazia";
				}
			} else {
				return "Deve ser registrado um usuário!";
			}
			
		} else if(entidade instanceof Usuario) {
			Usuario usuario = (Usuario) entidade;
			String senha1 = usuario.getSenha1();
			String senha2 = usuario.getSenha2();
			GeradorHash hash = new GeradorHash();
			Usuario usuarioBD;
			
			if (usuario.getId() != null) {
					UsuarioDAO dao = new UsuarioDAO();
					List<EntidadeDominio> entidadeBD;
				//verificar se a senha é igual e gerando hash dela e comparando com a do BD.
				
				try {
					if(usuario.getSenha1().equals(usuario.getSenha2())) {
						// Criterio 1:
						if (senha1.length() < 8 || senha2.length() < 8)
							return "Senha deve conter 8 caracteres ou mais.\n";
						// Criterio 2:
						Pattern p2 = Pattern.compile("[A-Z]");
						if (!p2.matcher(senha1).find() || !p2.matcher(senha2).find())
							return "Senha deve conter ter letras maiúsculas.\n";
						// Criterio 3:
						Pattern p3 = Pattern.compile("[a-z]");
						if (!p3.matcher(senha1).find() || !p2.matcher(senha2).find())
							return "Senha  deve conter letras minúsculas.\n";
						// Criterio 4:
						Pattern p4 = Pattern.compile("[0-9]");
						if (!p4.matcher(senha1).find() || !p2.matcher(senha2).find())
							return "Senha deve conter pelo menos um valor numerico.\n";
						// Critério 5:
						Pattern p5 = Pattern.compile("[-+*/=%@$#]");
						if (!p5.matcher(senha1).find() || !p2.matcher(senha2).find())
							return "Senha deve conter um dos seguintes caracteres especiais: + - * / = %\n";
						if (erros.length() > 0) {
							return erros.toString();
						}
						usuario.setSenha(hash.senhaHash(usuario.getSenha()));
						}
			
				}catch (Exception e) {
					return "Senha antiga invalida, favor inserir novamente!";
				}
				try {	
					// se deu acerto o primerio try entao gero a nova senha com hash.
					
					if (usuario.getSenha1() != null && usuario.getSenha2() != null) {
						if(usuario.getSenha1().equals(usuario.getSenha2())) {
							entidadeBD = dao.consultar(entidade);
							usuarioBD = (Usuario) entidadeBD.get(0);
							if (usuario.getSenha().equals(usuarioBD.getSenha())) {
								usuario.setSenha1(hash.senhaHash(usuario.getSenha1()));
                                if (!usuario.getSenha1().equals(usuarioBD.getSenha())) {
                                    if (usuario.getId() == usuarioBD.getId()) {
                                        return null;
                                    }
                                } else {
                                    return "Escolha uma senha diferente da atual!";
                                }
                            } else {
                                return "Senha atual incorreta!";
                            }	
						
						
						} else {
							return "Senha repetida não é igual!";
						}
					}
				}catch (Exception e) {
					return "Erro de alteração!";
				}
			}
		
			
		}
		
			return "Objeto não é cliente";
		
		
	}
}
