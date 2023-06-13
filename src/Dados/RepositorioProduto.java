package Dados;

import java.util.ArrayList;


import Negocios.Produto;
import Excecoes.ProdutoNaoEncontradoException;
import Excecoes.ProdutoCadastradoException;

public class RepositorioProduto implements IRepositorioProduto{
	ArrayList<Produto> arrayListProduto;

    public RepositorioProduto() {
        arrayListProduto = new ArrayList<Produto>();
    }

    
	public ArrayList<Produto> getListProduto() {
        return arrayListProduto;
    }

    public void cadastrarProduto(Produto produto) {
        this.arrayListProduto.add(produto);
    }

    public Produto selecionarProduto(String codigo) throws  ProdutoNaoEncontradoException{
        if(!arrayListProduto.isEmpty()) {
            for(int i = 0; i < arrayListProduto.size(); i++) {
                if(arrayListProduto.get(i).getCodigoProduto().equals(codigo)) {
                    return arrayListProduto.get(i);
                }
            }
        }
        throw new ProdutoNaoEncontradoException();//nao achou
    }
    public boolean conferirCodigo(String codigo){
        if(!arrayListProduto.isEmpty()) {
            for(int i = 0; i < arrayListProduto.size(); i++) {
                if(arrayListProduto.get(i).getCodigoProduto().equals(codigo)) {
                    return true;
                }
            }
        }
        return false;
    }
  
    public void excluirProduto(Produto produto) {
        this.arrayListProduto.remove(produto);
    }
    
    public String getAllProducts() {
    	String result = "";
    		
    	for(Produto p: arrayListProduto) {
    		result += p.toString();
    	}
    	
    	return result;
    }
    
	
}
