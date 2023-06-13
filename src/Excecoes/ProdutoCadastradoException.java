package Excecoes;

public class ProdutoCadastradoException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public ProdutoCadastradoException() {
		super("\n Não será possivel cadastrar, verifique voce está cadastrando corretamente! \n");
	}
}
