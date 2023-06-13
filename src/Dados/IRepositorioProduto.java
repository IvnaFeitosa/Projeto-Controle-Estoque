package Dados;

import java.util.ArrayList;

import Excecoes.ProdutoNaoEncontradoException;

import Negocios.Produto;

public interface IRepositorioProduto {

	public void cadastrarProduto(Produto produto);

	public void excluirProduto(Produto produto);

    public ArrayList<Produto> getListProduto();

    public String getAllProducts();
    
    public Produto selecionarProduto(String codigo) throws ProdutoNaoEncontradoException;
    
    public boolean conferirCodigo(String codigo);
    
    
 
}
