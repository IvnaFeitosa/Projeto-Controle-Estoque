package IUProdutos;

import java.util.Scanner;


import Negocios.Produto;
import Negocios.ProdutoAlimenticio;
import Dados.RepositorioProduto;
import Excecoes.ProdutoCadastradoException;
import Excecoes.ProdutoNaoEncontradoException;
import Fachada.ControleEstoque;


public class InterfacePrincipal {
    
    
	Scanner input = new Scanner(System.in);
    private RepositorioProduto repositorioproduto = new RepositorioProduto();
    private MenuGeral menuGeral = new MenuGeral();
    private ControleEstoque fachada = new ControleEstoque();
    

    public InterfacePrincipal(){

    	//repositorioproduto = new IRepositorioProduto();
        
    }
    
    Produto produtoInserido = null;

    public int menu() throws ProdutoNaoEncontradoException {
    	int opcao;
	    boolean loopMenu = true;
        
        while(loopMenu){
             
        	menuGeral.opcoes();
        	
            System.out.print("Digite a opção: \n");
            
			opcao = input.nextInt(); //opções do menu

            System.out.print("\n");

            switch(opcao) {
                case 0:
                    loopMenu = false;
                    break;
                case 1:
                	System.out.print("Cadastro de Produto (Produto Alimentício/Produto não Alimentício):");
                    cadastrarProduto();
                    break;
		        case 2:
                    consultarProduto();
                    break;
                case 3:
                	excluirProduto();
                    break;
                case 4:
                	editarVenda();
                    break;
                case 5:
                	editarPreco();
                	 break;
                case 6:
                	editarDescricao();
                	 break;
                	
                case 7:
                	editarEstoque();
                	 break;
                case 8:
                	compraProduto();
                	 break;
               
                default:
                    System.out.println("\nOpcao incorreta!");
                    System.out.println("\n");
                    break;
            }
        }
        return 0;
    }
    public void cadastrarProduto() throws ProdutoNaoEncontradoException {
        int tipoProduto;
        String nomeProduto = "";
        String codigoProduto = "";
        String descricao = "";
        double precoCusto = -1;
        double precoVenda = -1;
        int quantidadeCompra = -1;
        int quantidadeVenda = -1;
        int quantidadeEstoque ;

        Produto produto = null;
        
        System.out.print("\n");
        System.out.print("Codigo do produto: \n");
        while(codigoProduto.isEmpty()) {
        	
        	codigoProduto = input.nextLine();
        }
        //if(repositorioproduto.conferirCodigo(codigoProduto)) {
        	
        //	System.out.println("\nO produto já foi cadastrado, você não pode cadastrar o mesmo produto duas vezes ");
		//	System.out.println("\nDigite um código não cadastrado");
		//	cadastrarProduto();
        //}
        	
        System.out.print("\nNome do produto: ");
        while(nomeProduto.isEmpty()) {
        	nomeProduto = input.nextLine();
        }
        
        System.out.print("\nDescricao: ");
        while(descricao.isEmpty()) {
        	descricao = input.nextLine();
        }
        
        System.out.print("\nPreco custo(Informe apenas números): ");
        
        while( precoCusto<0) {
        	precoCusto = input.nextDouble();
        	if(precoCusto<0) {
        		System.out.println("\nValor invalido! Digite um valor válido.");
        	}
        }
        
        
        System.out.println("\nPreco venda (Informe apenas número): ");
        while( precoVenda<0) {
        	precoVenda = input.nextDouble();
        	if(precoVenda<0) {
        		System.out.println("\nValor invalido! Digite um valor válido.");
        	}
        }
                
        System.out.println("\nQuantidade compra: ");
        while( quantidadeCompra<0) {
        	quantidadeCompra = input.nextInt();
        	if(quantidadeCompra<0) {
        		System.out.println("\nValor invalido! Digite um valor válido.");
        	}
        }
        
        System.out.println("\nQuantidade vendida: ");
        while( quantidadeVenda <0) {
        	quantidadeVenda = input.nextInt();
        	if(quantidadeVenda<0 ) {
        		System.out.println("\nValor invalido! Digite um valor válido.");
        	}
        	if(quantidadeVenda>quantidadeCompra) {
        		System.out.println("\nValor invalido! A quantidade vendida não pode ser maior que a quantidade comprada. \nDigite um valor válido.");
        	quantidadeVenda=-1;
        	}
        }
        
        quantidadeEstoque = quantidadeCompra - quantidadeVenda; //inicialmente igual a 0
        

        System.out.print("Digite o tipo de Produto que você deseja cadastrar: \n(1 - Produto não alimentício ou 2 - Produto alimentício: ");
        tipoProduto = input.nextInt();
        input.nextLine(); // Limpar a quebra de linha pendente após nextInt()
        
        try {
            fachada.addProduto(nomeProduto, codigoProduto, descricao, precoCusto, precoVenda, quantidadeVenda, quantidadeCompra, quantidadeEstoque, tipoProduto);
            
            System.out.print("\nProduto - Produto cadastrada com sucesso!\n");
            
            
        } catch (ProdutoCadastradoException e) {
            
            System.out.println(e.getMessage());
        }
        menu();
    }
    
    public void consultarProduto(){
    	if(!repositorioproduto.getListProduto().isEmpty()){
    		System.out.println(repositorioproduto.getAllProducts());
    	} else {
    		System.out.print("\nNão existe cadastrado no sistema!\n");
    	}
    }

    
    public void editarVenda() throws ProdutoNaoEncontradoException{

        
        int quantidadeVenda = -1;
        int quantidadeEstoque = 0;
        
            System.out.print("\nPara realizar a alteração, informe o código do produto: ");
            String codigo = input.next();

            System.out.print("\n");

            Produto produto = repositorioproduto.selecionarProduto(codigo);

            if(produto != null){
                System.out.print("Realize a alteração:");
                System.out.print("\n");

                System.out.println("\nQuantidade vendida: ");
                while( quantidadeVenda <0) {
                	quantidadeVenda = input.nextInt();
                	if(quantidadeVenda<0) {
                		System.out.println("\nValor invalido! Digite um valor válido.");
                	}
                	quantidadeEstoque = produto.getQuantidadeEstoque();
                	if(quantidadeVenda>quantidadeEstoque) {
                		System.out.println("\nValor invalido! A quantidade vendida não pode ser maior que a quantidade em estoque. \nDigite um valor válido.");
                	quantidadeVenda=-1;
                	}
                }
                
                quantidadeEstoque = quantidadeEstoque-quantidadeVenda;
                produto.setQuantidadeEstoque(quantidadeEstoque);
                quantidadeVenda = quantidadeVenda + produto.getQuantidadeVenda();
                produto.setQuantidadeVenda(quantidadeVenda);
                
                System.out.print("\nQuantidade vendida do produto alterada com sucesso!\n");
            } else {
            	System.out.print("O produto que você deseja editar não existe no sitema");
            }
    }

    public void excluirProduto() throws ProdutoNaoEncontradoException{
    	String codigo;

        System.out.print("Para realizar a exclusão, informe o codigo do produto: ");
        codigo = input.next();

        System.out.print("\n");

        Produto produto = repositorioproduto.selecionarProduto(codigo);

        if(produto != null){
        	repositorioproduto.excluirProduto(produto);
            System.out.println("Produto excluida com sucesso!");
        }else {
        	System.out.print("\nO produto não existe\n");
        	System.out.print("\n");
        	System.out.print("Cancelando exclusão...\n");
        	System.out.print("\n");
        }
    }
    public void editarEstoque() throws ProdutoNaoEncontradoException {
    	int quantidadeEstoque = -1;
    	
    	String codigo;

        System.out.print("Informe o codigo do produto que deseja alterar o estoque: ");
   		codigo = input.next();

   		System.out.print("\n");

   		Produto produto = repositorioproduto.selecionarProduto(codigo);

   		if(produto != null){
   			int quantidadeCompra = produto.getQuantidadeCompra();
        	System.out.println("Quantidade de Estoque ");
        	do{
        		quantidadeEstoque = input.nextInt();
        	}while(quantidadeEstoque<0);
        	
        	if (quantidadeEstoque<quantidadeCompra) {
        		produto.setQuantidadeEstoque(quantidadeEstoque);
        		System.out.println("Estoque do produto alterado com sucesso ");
        	}else {
        		System.out.println("Cadastro inválido, \nvocê não pode ter em estoque mais produtos do que você comprou. \nSe você comprou mais produtos, cadastre uma compra. ");
        	}
                    	
        }else {
        	System.out.print("O produto que você deseja editar não existe no sitema");
        }
    }
    
    public void editarPreco() throws ProdutoNaoEncontradoException {
    	double precoVenda = -1;
    	
        System.out.print("\nPara realizar a alteração, informe o código do produto: ");
        String codigo = input.next();

        System.out.print("\n");

        Produto produto = repositorioproduto.selecionarProduto(codigo);

        if(produto != null){
      		System.out.print("Realize a alteração:");
      		System.out.print("\n");

            System.out.println("\nPreco venda (Informe apenas número): ");
      		while( precoVenda<0) {
      			precoVenda = input.nextDouble();
      			if(precoVenda<0) {
      			System.out.println("\nValor invalido! Digite um valor válido.");
      			}
            }
                           
            produto.setPrecoVenda(precoVenda);

            System.out.print("\nQuantidade vendida do produto alterada com sucesso!\n");
            } else {
            	System.out.print("O produto que você deseja editar não existe no sitema");
            }
    }
    public void editarDescricao() throws ProdutoNaoEncontradoException{

        String descricao = "";
        
        System.out.print("\nPara realizar a alteração, informe o código do produto: ");
        String codigo = input.next();

        System.out.print("\n");

        Produto produto = repositorioproduto.selecionarProduto(codigo);

        if(produto != null){
                System.out.print("Realize a alteração:");
                System.out.print("\n");

                			
                System.out.print("\nDescricao: ");
                while(descricao.isEmpty()) {
                	descricao = input.nextLine();
                }
               
                produto.setDescricao(descricao);
                
                
                
                
                System.out.print("\nQuantidade vendida do produto alterada com sucesso!\n");
            } else {
            	System.out.print("O produto que você deseja editar não existe no sitema");
            }
        
    }
    public void compraProduto() throws ProdutoNaoEncontradoException{

        int quantidadeCompra = -1;
        int quantidadeEstoque;
        
     
        System.out.print("\n Informe o código do produto: ");
        String codigo = input.next();

        System.out.print("\n");

        Produto produto = repositorioproduto.selecionarProduto(codigo);

        if(produto != null){
        	System.out.println("\nQuantidade compra: ");
	        while( quantidadeCompra<0) {
	        	quantidadeCompra = input.nextInt();
	          	if(quantidadeCompra<0) {
	          		System.out.println("\nValor invalido! Digite um valor válido.");
	          	}
	        }
          	quantidadeEstoque = produto.getQuantidadeEstoque();
          	quantidadeEstoque += quantidadeCompra;
            produto.setQuantidadeCompra(quantidadeCompra);
            produto.setQuantidadeEstoque(quantidadeEstoque);
            
            System.out.print("\nQuantidade comprada do produto cadastrada com sucesso!\n");
        } else {
        	System.out.print("O produto que você deseja editar não existe no sitema");
        }
        
    }
}

