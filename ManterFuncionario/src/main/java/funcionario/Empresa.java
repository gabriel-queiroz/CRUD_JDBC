package funcionario;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import db.FuncionarioDAO;

public class Empresa {


	private Scanner inputN = new Scanner(System.in);
	private Scanner inputS = new Scanner(System.in);


	public Empresa(){


	}

	public void menu(){



		System.out.println("bem vindo a tela a Empresa JBS \n");



		int ops = 0;

		do{


			System.out.println("1.Cadastra empregado");
			System.out.println("2.Listar Empregado");
			System.out.println("3.Excluir empregado");
			System.out.println("4.Atualizar Empregado");
			System.out.println("5.Mostrar soma dos salarios");
			System.out.println("6.Pesquisar Empregado");
			System.out.println("7.Sair");

			ops = LerDadoInteger(ops);



			switch(ops){

			case 1:{

				cadastrarEmpregado();

				break;
			}
			case 2:{


				listar();

				break;
			}

			case 3:{


				excluir();

				break;
			}

			case 4:{

				atualizar();

				break;
			}
			case 5:{

				mostrarSomaSalario();

				break;
			}

			case 6:{

				mostrarInformacaoes();

				break;
			}
			case 7:{
				
				break;
			}

			default:{

					System.out.println("opção invalida");
					ops = 1;
				

			}

			}


		}while(ops >=1 && ops <=6);

	}

	private void cadastrarEmpregado(){



		System.out.println(" ===== Bem vindo ao cadastro de clientes =====");

		System.out.println("Digite Matricula do Empregado");

		String matricula = inputS.nextLine();

		Funcionario f = mostrarInformacaoes(matricula);

		if(f == null){

			System.out.println("digite Nome do Empregado");

			String nome = inputS.nextLine();


			System.out.println("Digite Salario do Empregado");
			double salario = 0;

			salario  = LerDadoDouble(salario);

			new FuncionarioDAO().cadastrarUsuario(new Funcionario(nome,matricula,salario));

			System.out.println("Empregado Cadastrado com sucesso");

			System.out.println("===================================");
		}
		else{
			
			System.out.println("Usúario Já cadastrado");
			
		}
	}
	private void listar(){

		new FuncionarioDAO().listar().forEach(System.out::println);

		
		System.out.println("====================================");
	
	}
	
	private void excluir(){


		System.out.println("Digite matricula do Empregado");

		String matricula = inputS.nextLine();

		Funcionario f = mostrarInformacaoes(matricula);

		if(f == null){

			System.out.println("Funcionário com matricula : " + matricula + " não existe");

			return;
		}

		new FuncionarioDAO().excluirUsuario(matricula);

		System.out.println("Usúario Excluído com sucesso!!!");
		System.out.println("=================================");
	
	}
	private void atualizar(){


		int op = 0;

		System.out.println("digite matricula do Empregado");
		String matricula = inputS.nextLine();


		Funcionario f = mostrarInformacaoes(matricula);

		if(f == null){
			System.out.println("Usuário não cadastrado deseja cadastrar (s/n)?");
			String opcao = inputS.nextLine();

			if(opcao.equalsIgnoreCase("S")){

				cadastrarEmpregado();

				return;
			}
			else{
				return;
			}
		}

		System.out.println("1. Atualizar nome");
		System.out.println("2. Atualizar salario");
		op = LerDadoInteger(op);


		if(op == 1){


			System.out.println("Digite Novo nome");
			String nome = inputS.nextLine();

			new FuncionarioDAO().atualizarFuncionario(matricula,nome,0);


		}
		if(op ==2){


			System.out.println("Digite Novo salario");
			double salario = 0; 
			salario = LerDadoDouble(salario);

			new FuncionarioDAO().atualizarFuncionario(matricula, null, salario);



		}

		System.out.println("Empregado atualizado com Sucesso");
		System.out.println("=================================");
	}
	private void mostrarInformacaoes(){



		System.out.println("digite matricula do Empregado");
		String matricula = inputS.nextLine();


		Funcionario f = new FuncionarioDAO().pesquisarUsuario(matricula);

		if(f== null){
			System.out.println("Usuário não cadastrado, deseja cadastrar (s/n)?");
			String n = inputS.nextLine();

			if(n.equalsIgnoreCase("S")){

				cadastrarEmpregado();

				return;
			}
			else{
				return;
			}
		}

		System.out.println(f);


		System.out.println("=========================================");
	}


	private Funcionario mostrarInformacaoes(String matricula){

		Funcionario f = new FuncionarioDAO().pesquisarUsuario(matricula);


		return f;
	}

	private void mostrarSomaSalario(){


		List<Funcionario> funcionarios = new FuncionarioDAO().listar();
		
		// calculando a media de salario dos funcionarios vindo do BD		
		
			double media =	funcionarios.stream()
				
						.mapToDouble(Funcionario::getSalario)
						.average()
						.getAsDouble();
		
		// calculando a soma dos salario dos funcionarios vindo do BD 					
		
		double soma = funcionarios.stream()
					
						.mapToDouble(Funcionario::getSalario)
						.sum();
			

		System.out.println("Soma dos Salários  é" + soma);
		System.out.println("Média dos salários " + media);



		System.out.println("=======================================");
	}

	private int LerDadoInteger(int n){

		boolean flag = true;


		while(flag){
			try{

				n = new Scanner(System.in).nextInt();
				flag = false;
			}catch(InputMismatchException c){

				System.out.println("digite um Número Valido");

			}
		}
		return n;



	}
	private double LerDadoDouble(double n){

		boolean flag = true;

		while(flag){

			try{

				n = new Scanner(System.in).nextDouble();
				flag = false;
			}catch(InputMismatchException c){

				System.out.println("digite um Número Valido");

			}
		}
		return n;
	}



}
