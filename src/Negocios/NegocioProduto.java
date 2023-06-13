package Negocios;

import java.util.Scanner;


import Excecoes.ProdutoCadastradoException;
import Dados.RepositorioProduto;

public class NegocioProduto {
	Scanner input = new Scanner(System.in);
	
	
	public void addProduto( String nomeProduto, String codigoProduto, String descricao, double precoCusto, double precoVenda, int quantidadeVenda, int quantidadeCompra, int quantidadeEstoque, int tipoProduto, RepositorioProduto produtos)
			throws ProdutoCadastradoException {
		Produto NovoProduto = null;
        if (!produtos.conferirCodigo(codigoProduto)){
           
            switch (tipoProduto) {
            case 1:
            	
                NovoProduto = new Produto(nomeProduto, codigoProduto, descricao, precoCusto, precoVenda, quantidadeVenda, quantidadeCompra, quantidadeEstoque, tipoProduto);
                
                produtos.cadastrarProduto(NovoProduto);
                break;
            case 2:
                System.out.print("\nData de validade: ");
                String dataDeValidade = input.next();
                NovoProduto = new ProdutoAlimenticio(nomeProduto, codigoProduto, descricao, precoCusto, precoVenda, quantidadeVenda, quantidadeCompra, quantidadeEstoque, tipoProduto, dataDeValidade);
                
                produtos.cadastrarProduto(NovoProduto);
                break;
            default:
                System.out.print("\nOpcao incorreta!\n");
                System.out.print("\n");
                System.out.print("Cancelando o cadastro...\n");
                System.out.print("\n");
                
                break;
        }
           
        } else {
            throw new ProdutoCadastradoException();
        }
    }
}
