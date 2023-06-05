package locadoradeveiculos;

import Facade.LocadoraException;
import Facade.LocadoraFacade;
import java.util.List;
import java.util.Scanner;
import model.cliente.Cliente;
import model.veiculos.Carro;
import model.veiculos.Moto;
import model.veiculos.Veiculo;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.CPFJaCadastradoException;
import repository.veiculos.VeiculoJaCadastradoException;
import repository.veiculos.VeiculoNaoCadastradoException;
import repository1.RepositoryException;

public class LocadoradeVeiculos {
    /*
    private static RepositorioCliente repClientes = new RepositorioClienteLista();
    private static RepositorioVeiculo repVeiculos = new RepositorioVeiculoLista();*/
    
    static LocadoraFacade facade = new LocadoraFacade();
	static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        facade = new LocadoraFacade();
    
        CriaDadosDeTeste();

		int opcao;
		do {
			limpaTela();
			System.out.println("MENU PRINCIPAL");
			System.out.println("==== =========");
			System.out.println();
			System.out.println("<1> Cadastro Clientes");
			System.out.println("<2> Cadastro Carros");
                        System.out.println("<3> Cadastro Motos");
			System.out.println("<4> Aluguel");
			System.out.println("<0> Sair");
			System.out.println();
			System.out.print("Escolha uma opção: ");

			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}

			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				cadastroClientes();
				break;
			case 2:
				cadastroCarros();
				break;
			case 3:
				// Alugar();
				break;
			}
		} while (opcao != 0);

		facade.exit();
		System.out.println("Programa terminado");
        
    }
    
    private static void limpaTela() {
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
	}

	private static void cadastroClientes() {
		int opcao;
		do {
			limpaTela();
			System.out.println("CADASTRO CLIENTES");
			System.out.println("======== ========");
			System.out.println();
			System.out.println("<1> Incluir Cliente");
			System.out.println("<2> Alterar Cliente");
			System.out.println("<3> Excluir Cliente");
			System.out.println("<4> Listar Clientes");
			System.out.println("<0> Menu Principal");
			System.out.println();
			System.out.print("Escolha uma opção: ");

			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}

			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				incluirCliente();
				break;
			case 2:
				alterarCliente();
				break;
			case 3:
				excluirCliente();
				break;
			case 4:
				listarCliente();
				break;
			}
		} while (opcao != 0);
	}

	private static void incluirCliente() {
		limpaTela();
		System.out.println("Cadastro de Cliente");
		System.out.println("===================");
		System.out.print("Nome: ");
		String cpf = scanner.next();
		System.out.print("CPF: ");
		String nome = scanner.next();
                System.out.print("Endereco: ");
		String endereco = scanner.next();
                System.out.print("Telefone: ");
		String fone = scanner.next();
                System.out.print("Data de nascimento: ");
		String nas = scanner.next();
		System.out.print("Sexo (M) Mas (F) Fem: ");
		String sexo = scanner.next();
                System.out.print("Ano CNH: ");
		int cnh = scanner.nextInt();
                

		Cliente cliente = new Cliente(cpf, nome, endereco, fone, nas, sexo, cnh);

		try {
			facade.inserirCliente(cliente);
			System.out.println("Cliente cadastrado!");
		} catch (CPFJaCadastradoException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		scanner.nextLine();
	}

	private static void listarCliente() {
		limpaTela();
		List<Cliente> clientes = facade.getAllClientes();
		System.out.printf("CPF          Nome                 Sexo           Telefone            Data de Nacimento\n");
		System.out.printf("============== ==================== ==== =============== ==== ===============\n");
		for (Cliente cliente : clientes) {
			System.out.printf("%14s ", cliente.getCpf());
			System.out.printf("%-20s ", cliente.getNome());
			System.out.printf("%-4s ", String.valueOf(cliente.getSexo()));
			System.out.printf("%15s\n", cliente.getTelefone());
                        System.out.printf("%20s ", cliente.getAnocnh());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}

	private static void excluirCliente() {
		limpaTela();
		System.out.println("Excluir de Cliente");
		System.out.println("==================");
		System.out.print("CPF: ");
		String cpf = scanner.nextLine();

		try {
			Cliente cliente = facade.buscarCliente(cpf);
			System.out.println();
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("Sexo: " + cliente.getSexo());
			System.out.println("Telefone: " + cliente.getTelefone());
			System.out.println();

			System.out.print("Exclui esse cliente? (s/n)?");
			String resposta = scanner.nextLine();

			if (resposta.equalsIgnoreCase("s")) {
				facade.excluirCliente(cliente);
				System.out.println("Cliente excluído!");
			}

		} catch (ClienteNaoCadastradoException | LocadoraException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}

	private static void alterarCliente() {
		limpaTela();
		System.out.println("Alterar de Cliente");
		System.out.println("==================");
		System.out.print("CPF: ");
		String cpf = scanner.nextLine();

		try {
			Cliente cliente = facade.buscarCliente(cpf);

			System.out.println();
			System.out.println("Nome: " + cliente.getNome());
			System.out.print("Nome (<enter> = Não alterar): ");
			String nome = scanner.nextLine();
			if (!nome.equals("")) {
				cliente.setNome(nome);
			}

			System.out.println("Sexo: " + cliente.getSexo());
			System.out.print("Sexo (<enter> = Não alterar): ");
			String sexo = scanner.nextLine();
			if (!sexo.equals("")) {
				cliente.setSexo(sexo);
			}

			System.out.println("Telefone: " + cliente.getTelefone());
			System.out.print("Telefone (<enter> = Não alterar): ");
			String fone = scanner.nextLine();
			if (!fone.equals("")) {
				cliente.setTelefone(fone);
			}

			System.out.println();

			facade.alterarCliente(cliente);
			System.out.println("Cliente Alterado!");
			System.out.println();

		} catch (ClienteNaoCadastradoException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}

	private static void cadastroCarros() {
		int opcao;
		do {
			limpaTela();
			System.out.println("CADASTRAR CARRO");
			System.out.println("======== ======");
			System.out.println();
			System.out.println("<1> Cadastrar Carro");
			System.out.println("<2> Excluir Carro");
			System.out.println("<3> Consultar Carro");
			System.out.println("<4> Listar Carros");
			System.out.println("<0> Menu Principal");
			System.out.println();
			System.out.print("Escolha uma opção: ");

			try {
				opcao = Integer.valueOf(scanner.nextLine());
			} catch (Exception e) {
				opcao = 0;
			}

			switch (opcao) {
			case 0:
				limpaTela();
				break;
			case 1:
				cadastrarCarro();
			case 2:
				excluirCarro();
				break;
			case 3:
				consultarCarro();
				break;
			case 4:
				listarCarros();
				break;
			}
		} while (opcao != 0);
	}

        public static void cadastrarCarro(){
                limpaTela();
                System.out.println("Cadastro de Carro");
                System.out.println("===================");
                System.out.print("Modelo: ");
                String modelo = scanner.next();
                System.out.print("Marca: ");
                String marca = scanner.next();
                System.out.print("Placa: ");
                String placa = scanner.next();
                System.out.print("Cor: ");
                String cor = scanner.next();
                System.out.print("Ano de Fabricacao: ");
                int adf = scanner.nextInt();
                System.out.print("Quilometragem: ");
                double quilometragem = scanner.nextDouble();
                System.out.print("Categoria: ");
                String categoria = scanner.next();
                System.out.print("Cliente: ");
                String cliente = scanner.next();
                System.out.print("Numero de portas: ");
                int nportas = scanner.nextInt();
                System.out.print("Tipo de Combustivel: ");
                String tcombustivel = scanner.next();
                System.out.print("Capacidade do porta malas: ");
                int cmalas = scanner.nextInt();
                
                Veiculo titular = null;

                try {
                    titular = facade.verificarDisponibilidade(modelo);
                } catch (VeiculoNaoCadastradoException ex) {
                    System.err.println(ex.getMessage());
                }

                if (titular != null) {
                    Carro carro = new Carro(modelo, marca, placa, cor, adf, quilometragem, categoria, nportas, tcombustivel, cmalas);

                    try {
                        facade.inserirConta(conta);
                        System.out.println("Conta cadastrada!");
                    } catch (RepositoryException ex) {
                        System.err.println(ex.getMessage());
                    }
                } else {
                    System.out.println("Titular não encontrado. A conta não pôde ser cadastrada.");
                }

                System.out.println("tecle <enter> para voltar");
                scanner.nextLine();
                scanner.nextLine();
            }
        
        
        public static void excluirCarro(){
		limpaTela();
		System.out.println("Excluir de Conta");
		System.out.println("==================");
		System.out.print("Número: ");
		String numeroConta = scanner.nextLine();

		try {
			Conta conta = facade.buscarConta(numeroConta);
			System.out.println();
			System.out.println("Número da conta: " + conta.getNumero());
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Saldo: " + conta.getSaldo());
			System.out.println();

			System.out.print("Exclui essa conta? (s/n)?");
			String resposta = scanner.nextLine();

			if (resposta.equalsIgnoreCase("s")) {
				facade.excluirConta(conta);
				System.out.println("Cliente excluído!");
			}

		} catch (RepositoryException | BancoException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
        }
        
        public static void consultarCarro(){
            limpaTela();
		System.out.println("Consultar saldo de conta");
		System.out.println("==================");
		System.out.print("Número: ");
		String numeroConta = scanner.nextLine();

		try {
			Conta conta = facade.buscarConta(numeroConta);
			System.out.println();
			System.out.println("Número da conta: " + conta.getNumero());
                        System.out.println("Titular: " + conta.getTitular());
			System.out.println("Saldo: " + conta.getSaldo());
			System.out.println();

		} catch (RepositoryException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
        }
        
        public static void listarCarros(){
            limpaTela();
		List<Conta> contas = facade.getAllContas();
		System.out.printf("Número          Titular                 Saldo\n");
		System.out.printf("============== ==================== ==== ===============\n");
		for (Conta conta : contas) {
			System.out.printf("%14s ", conta.getNumero());
			System.out.printf("%-20s ", conta.getTitular());
			System.out.printf("%15s\n", conta.getSaldo());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
        }
    

    private static void CriaDadosDeTeste() {
        try {
            facade.inserirCliente(new Cliente("Pedro", "024.204.348.45", "Santarenzinho 55", "(55)991919116",
                    "5/5/15", "M", 2018));

            facade.inserirCliente(new Cliente("Ana", "055.465.128.35", "Santana 33", "(55)991654621",
                    "1/4/98", "F", 2020));
            
            
            
            facade.cadastrarVeiculo(new Moto("CG 160 Titan", "Honda", "OSA-6549", "Vermolho", 2023,
            5.5, "Motão", null, 150, "Bicilindrico"));
            
            facade.cadastrarVeiculo(new Carro("Mobi", "Fiat", "ASO-6549", "Preto", 2020,
            55.5, "A", null, 4, "Gasolina", 5));
            
        } catch (CPFJaCadastradoException e1) {
            System.out.println(e1.getMessage());
        } catch (VeiculoJaCadastradoException e2) {
            System.out.println(e2.getMessage());
        }
}
}

