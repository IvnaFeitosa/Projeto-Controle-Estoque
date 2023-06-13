package Fachada;

import Dados.*;
import Negocios.*;
import Excecoes.*;

public class ControleEstoque {
	private NegocioProduto NegocioProduto = new NegocioProduto();
	private RepositorioProduto repositorioProdutos = new RepositorioProduto();
	
	public void addProduto(String nomeProduto, String codigoProduto, String descricao, double precoCusto, double precoVenda, int quantidadeVenda, int quantidadeCompra, int quantidadeEstoque, int tipoProduto) throws ProdutoCadastradoException {
        NegocioProduto.addProduto(nomeProduto, codigoProduto, descricao, precoCusto, precoVenda, quantidadeVenda, quantidadeCompra, quantidadeEstoque, tipoProduto,  repositorioProdutos);
    }
	
	
}
