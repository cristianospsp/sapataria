import br.com.dwd.sapataria.controller.UsuarioController;

/**
 * @author Thatiana Liz S. Oliveira
 *
 * 28 de jan de 2016
 */

public class GerarSenha {

	public static void main(String[] args) {
		//gerador de senha md5
		String password = UsuarioController.criptografiaMd5("umaSenha");
		System.out.println(password);
		
	}

}
