package Excecoes;

public class ProdutoNaoEncontradoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException() {
		super("\n Não foi possivel encontrar o produto! \n");
	}
}
