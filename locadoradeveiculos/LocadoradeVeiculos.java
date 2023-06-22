package locadoradeveiculos;

import Facade.LocadoraException;
import Facade.LocadoraFacade;
import java.util.List;
import java.util.Scanner;
import model.aluguel.Aluguel;
import model.cliente.Cliente;
import model.veiculos.Carro;
import model.veiculos.Categoria;
import model.veiculos.Moto;
import model.veiculos.Veiculo;
import repository.categoria.CategoriaJaCadastradaException;
import repository.categoria.CategoriaNaoCadastradaException;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.CPFJaCadastradoException;
import repository.veiculos.VeiculoJaCadastradoException;
import repository.veiculos.VeiculoNaoCadastradoException;
import repository1.RepositoryException;

class LocadoradeVeiculos {
    
    private static LocadoraFacade facade;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Carregando o sistema aguade um momento...");
        facade = new LocadoraFacade();
    
        CriaDadosDeTeste();

		int opcao;
		do {
			limpaTela();
			System.out.println("MENU PRINCIPAL");
			System.out.println("==== =========");
			System.out.println();
			System.out.println("<1> Cadastro Clientes");
			System.out.println("<2> Cadastro Veiculos");
                        System.out.println("<3> Cadastro Categorias");
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
				cadastroVeiculos();
				break;
                        case 3:
				cadastroCategoria();
				break;
			case 4:
				aluguel();
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
            System.out.println("CLIENTES CADASTRADOS");
            System.out.println("=============================================================================");
            System.out.printf("%-14s %-20s %-4s %-15s %-20s\n",
                    "CPF", "Nome", "Sexo", "Telefone", "Data de Nascimento");

            for (Cliente cliente : clientes) {
                System.out.printf("%-14s %-20s %-4s %-15s %-20s\n",
                        cliente.getCpf(), cliente.getNome(), cliente.getSexo(), cliente.getTelefone(), cliente.getAnocnh());
            }

            System.out.println("=============================================================================");
            System.out.println();
            System.out.println("Pressione <enter> para voltar");
            scanner.nextLine();
        }


	private static void excluirCliente() {
		limpaTela();
		System.out.println("Excluir Clientes");
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

			System.out.print("Excluir esse cliente? (s/n)?");
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
		System.out.println("Alterar Cliente");
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
        
        
        private static void cadastroVeiculos(){
            int opcao;
		do {
			limpaTela();
			System.out.println("CADASTRO VEICULOS");
			System.out.println("======== ========");
			System.out.println();
			System.out.println("<1> Cadastro Carros");
			System.out.println("<2> Cadastro Motos");
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
				cadastroCarros();
				break;
			case 2:
				cadastroMotos();
				break;
			}
		} while (opcao != 0);
        }
        
        
        

	private static void cadastroCarros() {
		int opcao;
		do {
			limpaTela();
			System.out.println("CADASTRO DE CARRO");
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
                                break;
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
                String categoriaNome = scanner.next();
                Categoria categoria = null;
                try {
                    categoria = facade.verificarDescricao(categoriaNome);
                } catch (CategoriaNaoCadastradaException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.print("Numero de portas: ");
                int nportas = scanner.nextInt();
                System.out.print("Tipo de Combustivel: ");
                String tcombustivel = scanner.next();
                System.out.print("Capacidade do porta malas: ");
                int cmalas = scanner.nextInt();
                

                Carro carro = new Carro(modelo, marca, placa, cor, adf, quilometragem, categoria, nportas, tcombustivel, cmalas);

                try {
                    facade.cadastrarVeiculo(carro);
                    System.out.println("Veiculo cadastrado!");
                } catch (VeiculoJaCadastradoException ex) {
                    System.err.println(ex.getMessage());
                }

                System.out.println("tecle <enter> para voltar");
                scanner.nextLine();
                scanner.nextLine();
            }
        
        
        public static void excluirCarro(){
		limpaTela();
		System.out.println("Excluir de Conta");
		System.out.println("==================");
		System.out.print("Placa: ");
		String placa = scanner.nextLine();

		try {
			Veiculo carro = facade.verificarDisponibilidade(placa);
			System.out.println();
                        System.out.println("Marca: " + carro.getMarca());
			System.out.println("Modelo: " + carro.getModelo());
			System.out.println("Cor: " + carro.getCor());
			System.out.println();

			System.out.print("Exclui essa carro? (s/n)?");
			String resposta = scanner.nextLine();

			if (resposta.equalsIgnoreCase("s")) {
				facade.deletarVeiculo(carro);
				System.out.println("Carro excluído!");
			}

		} catch (VeiculoNaoCadastradoException | LocadoraException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
        }
        
        public static void consultarCarro(){
            limpaTela();
		System.out.println("Consultar carro");
		System.out.println("==================");
		System.out.print("Placa: ");
		String placa = scanner.nextLine();
                //trocar veiculo por carro quando possível
		try {
			Veiculo carro = facade.verificarDisponibilidade(placa);
                        System.out.println();
                        System.out.println("Marca: " + carro.getMarca());
                        System.out.println("Modelo: " + carro.getModelo());
                        System.out.println("Placa: " + carro.getPlaca());
                        System.out.println("Cor: " + carro.getCor());
                        System.out.println("Ano de Fabricação: " + carro.getAnodefabricao());
                        System.out.println("Quilometragem: " + carro.getQuilometragem());
                        System.out.println("Categoria: " + carro.getCategoria());
                        if (carro instanceof Carro) {
                            Carro carroDetalhes = (Carro) carro;
                            System.out.println("Número de Portas: " + carroDetalhes.getNumerodeportas());
                            System.out.println("Tipo de Combustível: " + carroDetalhes.getTipodecombustivel());
                            System.out.println("Capacidade do Porta-malas: " + carroDetalhes.getCapacidadedoportamalas());
                        }
                        System.out.println("Esta alugado: " + carro.isAlugado());
                        System.out.println();
		} catch (VeiculoNaoCadastradoException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
        }
        
        private static void listarCarros() {
            limpaTela();
            List<Veiculo> veiculos = facade.getAllVeiculos();
            System.out.println("CARROS CADASTRADOS");
            System.out.println("============================================================================================================="
                    + "==========================================================================");
            System.out.printf("%-15s %-15s %-15s %-10s %-20s %-15s %-15s %-20s %-20s %-20s\n",
                    "Marca", "Modelo", "Placa", "Cor", "Ano de Fabricação", "Quilometragem",
                    "Categoria", "Número de Portas", "Tipo de Combustível", "Capacidade do Porta-malas");

            for (Veiculo veiculo : veiculos) {
                if (veiculo instanceof Carro) {
                    Carro carro = (Carro) veiculo;
                    System.out.printf("%-15s %-15s %-15s %-10s %-20s %-15s %-15s %-20d %-20s %-20s\n",
                            carro.getMarca(), carro.getModelo(), carro.getPlaca(), carro.getCor(),
                            carro.getAnodefabricao(), carro.getQuilometragem(), carro.getCategoria(),
                            carro.getNumerodeportas(), carro.getTipodecombustivel(), carro.getCapacidadedoportamalas());
                }
            }

            System.out.println("============================================================================================================="
                    + "==========================================================================");
            System.out.println();
            System.out.println("Pressione <enter> para voltar");
            scanner.nextLine();
        }

    
    private static void cadastroMotos() {
		int opcao;
		do {
			limpaTela();
			System.out.println("CADASTRO DE MOTOS");
			System.out.println("======== ======");
			System.out.println();
			System.out.println("<1> Cadastrar Moto");
			System.out.println("<2> Excluir Moto");
			System.out.println("<3> Consultar Moto");
			System.out.println("<4> Listar Motos");
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
				cadastrarMoto();
                                break;
			case 2:
				excluirMoto();
				break;
			case 3:
				consultarMoto();
				break;
			case 4:
				listarMotos();
				break;
			}
		} while (opcao != 0);
	}

        public static void cadastrarMoto(){
                limpaTela();
                System.out.println("Cadastro de Moto");
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
                String categoriaNome = scanner.next();
                Categoria categoria = null;
                try {
                    categoria = facade.verificarDescricao(categoriaNome);
                } catch (CategoriaNaoCadastradaException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.print("Cilindrada: ");
                int cilindrada = scanner.nextInt();
                System.out.print("Tipo de Motor: ");
                String tMotor = scanner.next();
                
                Moto moto = new Moto(modelo, marca, placa, cor, adf, quilometragem, categoria, cilindrada, tMotor);

                try {
                    facade.cadastrarVeiculo(moto);
                    System.out.println("Moto cadastrada!");
                } catch (VeiculoJaCadastradoException ex) {
                    System.err.println(ex.getMessage());
                }
                 
                
                System.out.println();
                System.out.println("tecle <enter> para voltar");
                scanner.nextLine();
            }
        
        
        public static void excluirMoto(){
		limpaTela();
		System.out.println("Excluir Moto");
		System.out.println("==================");
		System.out.print("Placa: ");
		String placa = scanner.nextLine();

		try {
			Veiculo moto = facade.verificarDisponibilidade(placa);
			System.out.println();
			System.out.println("Marca: " + moto.getMarca());
			System.out.println("Modelo: " + moto.getModelo());
			System.out.println("Cor: " + moto.getCor());
			System.out.println();

			System.out.print("Exclui essa Moto? (s/n)?");
			String resposta = scanner.nextLine();

			if (resposta.equalsIgnoreCase("s")) {
				facade.deletarVeiculo(moto);
				System.out.println("Cliente excluído!");
			}

		} catch (VeiculoNaoCadastradoException | LocadoraException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
        }
        
        public static void consultarMoto(){
            limpaTela();
		System.out.println("Consultar Moto");
		System.out.println("==================");
		System.out.print("Placa: ");
		String placa = scanner.nextLine();

		try {
			Veiculo moto = facade.verificarDisponibilidade(placa);
			System.out.println();
			System.out.println("Marca: " + moto.getMarca());
                        System.out.println("Modelo: " + moto.getModelo());
                        System.out.println("Placa: " + moto.getPlaca());
                        System.out.println("Cor: " + moto.getCor());
                        System.out.println("Ano de Fabricação: " + moto.getAnodefabricao());
                        System.out.println("Quilometragem: " + moto.getQuilometragem());
                        System.out.println("Categoria: " + moto.getCategoria());
                        if (moto instanceof Moto) {
                            Moto motoDetalhes = (Moto) moto;
                            System.out.println("Cilindrada: " + motoDetalhes.getCilindrada());
                            System.out.println("Tipo de Motor: " + motoDetalhes.getTipodemotor());
                        }
                        System.out.println("Esta alugado: " + moto.isAlugado());

		} catch (VeiculoNaoCadastradoException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
        }
        
        private static void listarMotos() {
            limpaTela();
            List<Veiculo> veiculos = facade.getAllVeiculos();
            System.out.println("MOTOS CADASTRADAS");
            System.out.println("============================================================================================================="
                    + "========================================================================================");
            System.out.printf("%-15s %-15s %-15s %-10s %-20s %-15s %-15s %-20s %-20s \n",
                    "Marca", "Modelo", "Placa", "Cor", "Ano de Fabricação", "Quilometragem",
                    "Categoria", "Cilindrada", "Tipo de Motor");

            for (Veiculo veiculo : veiculos) {
                if (veiculo instanceof Moto) {
                    Moto moto = (Moto) veiculo;
                    System.out.printf("%-15s %-15s %-15s %-10s %-20s %-15s %-15s %-20s %-20s\n",
                            moto.getMarca(), moto.getModelo(), moto.getPlaca(), moto.getCor(),
                            moto.getAnodefabricao(), moto.getQuilometragem(), moto.getCategoria(),
                            moto.getCilindrada(), moto.getTipodemotor());
                }
            }

    System.out.println("============================================================================================================="
            + "========================================================================================");
    System.out.println();
    System.out.println("Pressione <enter> para voltar");
    scanner.nextLine();
}

        private static void cadastroCategoria() {
		int opcao;
		do {
			limpaTela();
			System.out.println("CADASTRO CATEGORIAS");
			System.out.println("======== ========");
			System.out.println();
			System.out.println("<1> Cadastrar Categoria");
			System.out.println("<2> Alterar Categoria");
			System.out.println("<3> Excluir Categoria");
			System.out.println("<4> Listar Categorias");
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
				cadastrarCategoria();
				break;
			case 2:
				alterarCategoria();
				break;
			case 3:
				excluirCategoria();
				break;
			case 4:
				listarCategorias();
				break;
			}
		} while (opcao != 0);
	}

        private static void cadastrarCategoria() {
		limpaTela();
		System.out.println("Cadastro de Categoria");
		System.out.println("===================");
		System.out.print("Categoria: ");
		String categorian = scanner.next();
		System.out.print("Descricao: ");
		String descricao = scanner.next();
                System.out.print("Valor da diaria: ");
		Double valorDiaria = scanner.nextDouble();
                System.out.print("Para (Carros ou Motos): ");
		String para = scanner.next();
                

		Categoria categoria = new Categoria(categorian, descricao, valorDiaria, para);

		try {
			facade.cadastrarCategoria(categoria);
			System.out.println("Categoria Cadastrada!");
		} catch (CategoriaJaCadastradaException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
		scanner.nextLine();
	}

	private static void listarCategorias() {
            limpaTela();
            List<Categoria> categorias = facade.getAllCategorias();
            System.out.println("CATEGORIAS CADASTRADAS");
            System.out.println("=============================================================================");
            System.out.printf("%-20s %-80s %-20s %-15s\n",
                    "Categoria", "Descricao", "Valor da diaria", "Para");

            for (Categoria categoria : categorias) {
                String valorDiariaFormatado = String.format("R$ %.2f", categoria.getValorDiaria());
                System.out.printf("%-20s %-80s %-20s %-15s\n",
                        categoria.getCategoria(), categoria.getDescricao(), valorDiariaFormatado, categoria.getPara());
            }

            System.out.println("=============================================================================");
            System.out.println();
            System.out.println("Pressione <enter> para voltar");
            scanner.nextLine();
        }



	private static void excluirCategoria() {
		limpaTela();
		System.out.println("Excluir Categoria");
		System.out.println("==================");
		System.out.print("Categoria: ");
		String categorian = scanner.nextLine();

		try {
			Categoria categoria = facade.verificarDescricao(categorian);
			System.out.println();
			System.out.println("Categoria: " + categoria.getCategoria());
			System.out.println("Descricao: " + categoria.getDescricao());
			System.out.println("Valor Diaria: " + categoria.getValorDiaria());
                        System.out.println("Para: " + categoria.getPara());
			System.out.println();

			System.out.print("Excluir essa categoria? (s/n)?");
			String resposta = scanner.nextLine();

			if (resposta.equalsIgnoreCase("s")) {
				facade.deletarCategoria(categoria);
				System.out.println("Categoria excluída!");
			}

		} catch (CategoriaNaoCadastradaException | LocadoraException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}

	private static void alterarCategoria() {
		limpaTela();
		System.out.println("Alterar Categoria");
		System.out.println("==================");
		System.out.print("Categoria: ");
		String categorian = scanner.nextLine();

		try {
			Categoria categoria = facade.verificarDescricao(categorian);

			System.out.println();
			System.out.println("Categoria: " + categoria.getCategoria());
			System.out.print("Categoria (<enter> = Não alterar): ");
			String categorianova = scanner.nextLine();
			if (!categorianova.equals("")) {
				categoria.setCategoria(categorianova);
			}

			System.out.println("Descricao: " + categoria.getDescricao());
			System.out.print("Descricao (<enter> = Não alterar): ");
			String descricao = scanner.nextLine();
			if (!descricao.equals("")) {
				categoria.setDescricao(descricao);
			}

			System.out.println("Valor da Diaria: " + categoria.getValorDiaria());
			System.out.print("Valor da Diaria (<enter> = Não alterar): ");
			String novovalor = scanner.nextLine();

                        if (!novovalor.isEmpty()) {
                            double novoValor = Double.parseDouble(novovalor);
                            categoria.setValorDiaria(novoValor);
                        }
                        
                        System.out.println("Veiculo: " + categoria.getPara());
			System.out.print("Veiculo (<enter> = Não alterar): ");
			String vei = scanner.nextLine();

                        if (!vei.equals("")) {
				categoria.setPara(vei);
			}

			System.out.println();

			facade.alterarCategoria(categoria);
			System.out.println("Categoria Alterada!");
			System.out.println();

		} catch (CategoriaNaoCadastradaException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
	}
        
        
        private static void aluguel() {
		int opcao;
		do {
			limpaTela();
			System.out.println("ALUGUEL");
			System.out.println("======== ========");
			System.out.println();
			System.out.println("<1> Alugar Veiculo");
			System.out.println("<2> Devolver Veiculo");
			System.out.println("<3> Ver Veiculos Alugados");
			System.out.println("<4> Listar Veiculos por Categoria");
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
				//alugarVeiculo();
				break;
			case 2:
				//devolverVeiculo();
				break;
			case 3:
				//listarAlugueis();
				break;
			case 4:
				//listarVeiculosCategoria();
				break;
			}
		} while (opcao != 0);
	}
        
        public static void alugarVeiculo(){
                /*limpaTela();
                System.out.println("Alugar Veiculo");
                System.out.println("===================");
                System.out.print("Digite a placa do veículo: ");
                String veiculo = scanner.next();
                System.out.print("Digite a data que o veiculo vai ser alugado: ");
                String dataSaida = scanner.next();
                System.out.print("Digite a data que o veiculo vai ser devolvido: ");
                String dataDevolver = scanner.next();
                System.out.print("Quantidade de dias que o veiculo sera alugado: ");
                String dias = scanner.next();
                System.out.print("Categoria: ");
                String categoria = scanner.next();
                System.out.print("Cliente: ");
                String cliente = scanner.next();
                

                Carro carro = new Carro(modelo, marca, placa, cor, adf, quilometragem, categoria, nportas, tcombustivel, cmalas);

                try {
                    facade.cadastrarVeiculo(carro);
                    System.out.println("Veiculo cadastrado!");
                } catch (VeiculoJaCadastradoException ex) {
                    System.err.println(ex.getMessage());
                }

                System.out.println("tecle <enter> para voltar");
                scanner.nextLine();
                scanner.nextLine();*/
                
                /*limpaTela();
		System.out.println("Novo Aluguel");
		System.out.println("=================");

		try {
			System.out.print("CPF do titular: ");
			String cpf = scanner.nextLine();
			Cliente titular = facade.buscarCliente(cpf);
			System.out.println("Cliente: " + titular.getNome());

			Aluguel aluguel = null;
			System.out.print("Digite a placa do veículo: ");
                        String veiculo = scanner.next();
                        System.out.print("Digite a data que o veiculo vai ser alugado: ");
                        String dataSaida = scanner.next();
                        System.out.print("Digite a data que o veiculo vai ser devolvido: ");
                        String dataDevolver = scanner.next();
                        System.out.print("Quantidade de dias que o veiculo sera alugado: ");
                        String dias = scanner.next();
                        System.out.print("Categoria: ");
                        String categoria = scanner.next();
                        System.out.print("Cliente: ");
                        String cliente = scanner.next();
                

                        Carro carro = new Carro(modelo, marca, placa, cor, adf, quilometragem, categoria, nportas,
                                tcombustivel, cmalas);

			if (conta != null) {
				conta = facade.inserirConta(conta);
				System.out.println("Conta " + conta.getTipo() + " #" + conta.getNumero() + " criada!");
			} else {
				System.out.println("Nenhuma conta criada");
			}

		} catch (ClienteNaoCadastradoException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();*/
            }
        
        
        public static void devolverVeiculo(){
		limpaTela();
		System.out.println("Excluir de Conta");
		System.out.println("==================");
		System.out.print("Placa: ");
		String placa = scanner.nextLine();

		try {
			Veiculo carro = facade.verificarDisponibilidade(placa);
			System.out.println();
                        System.out.println("Marca: " + carro.getMarca());
			System.out.println("Modelo: " + carro.getModelo());
			System.out.println("Cor: " + carro.getCor());
			System.out.println();

			System.out.print("Exclui essa carro? (s/n)?");
			String resposta = scanner.nextLine();

			if (resposta.equalsIgnoreCase("s")) {
				facade.deletarVeiculo(carro);
				System.out.println("Carro excluído!");
			}

		} catch (VeiculoNaoCadastradoException | LocadoraException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
        }
        
        public static void listarAlugueis(){
            limpaTela();
		System.out.println("Consultar carro");
		System.out.println("==================");
		System.out.print("Placa: ");
		String placa = scanner.nextLine();
                //trocar veiculo por carro quando possível
		try {
			Veiculo carro = facade.verificarDisponibilidade(placa);
                        System.out.println();
                        System.out.println("Marca: " + carro.getMarca());
                        System.out.println("Modelo: " + carro.getModelo());
                        System.out.println("Placa: " + carro.getPlaca());
                        System.out.println("Cor: " + carro.getCor());
                        System.out.println("Ano de Fabricação: " + carro.getAnodefabricao());
                        System.out.println("Quilometragem: " + carro.getQuilometragem());
                        System.out.println("Categoria: " + carro.getCategoria());
                        if (carro instanceof Carro) {
                            Carro carroDetalhes = (Carro) carro;
                            System.out.println("Número de Portas: " + carroDetalhes.getNumerodeportas());
                            System.out.println("Tipo de Combustível: " + carroDetalhes.getTipodecombustivel());
                            System.out.println("Capacidade do Porta-malas: " + carroDetalhes.getCapacidadedoportamalas());
                        }
                        System.out.println("Esta alugado: " + carro.isAlugado());
                        System.out.println();
		} catch (VeiculoNaoCadastradoException ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println();
		System.out.println("tecle <enter> para voltar");
		scanner.nextLine();
        }
        
        private static void listarVeiculosCategoria() {
            limpaTela();
            List<Veiculo> veiculos = facade.getAllVeiculos();
            System.out.println("CARROS CADASTRADOS");
            System.out.println("============================================================================================================="
                    + "==========================================================================");
            System.out.printf("%-15s %-15s %-15s %-10s %-20s %-15s %-15s %-20s %-20s %-20s\n",
                    "Marca", "Modelo", "Placa", "Cor", "Ano de Fabricação", "Quilometragem",
                    "Categoria", "Número de Portas", "Tipo de Combustível", "Capacidade do Porta-malas");

            for (Veiculo veiculo : veiculos) {
                if (veiculo instanceof Carro) {
                    Carro carro = (Carro) veiculo;
                    System.out.printf("%-15s %-15s %-15s %-10s %-20s %-15s %-15s %-20d %-20s %-20s\n",
                            carro.getMarca(), carro.getModelo(), carro.getPlaca(), carro.getCor(),
                            carro.getAnodefabricao(), carro.getQuilometragem(), carro.getCategoria(),
                            carro.getNumerodeportas(), carro.getTipodecombustivel(), carro.getCapacidadedoportamalas());
                }
            }

            System.out.println("============================================================================================================="
                    + "==========================================================================");
            System.out.println();
            System.out.println("Pressione <enter> para voltar");
            scanner.nextLine();
        }

    private static void CriaDadosDeTeste() {
        try {
            facade.inserirCliente(new Cliente("Pedro Ferreira", "024.204.348.45", "Santarenzinho 55", "(55)991919116",
                    "5/5/15", "M", 2018));

            facade.inserirCliente(new Cliente("Ana Carolina", "055.465.128.35", "Santana 33", "(55)991654621",
                    "1/4/98", "F", 2020));
            
            
            
            facade.cadastrarVeiculo(new Moto("CG 160 Titan", "Honda", "OSA-6549", "Vermelho", 2021,
            102.7, facade.verificarDescricao("Naked"), 162, "Monocilindrico"));
            
            facade.cadastrarVeiculo(new Moto("S1000RR", "BMW", "ZZZ-6899", "Cinza", 2023,
            32.5, facade.verificarDescricao("Touring"), 999, "Tetracilindrico"));
            
            
            facade.cadastrarVeiculo(new Carro("Mobi", "Fiat", "ASO-6549", "Preto", 2020,
            55.5, facade.verificarDescricao("B"), 4, "Gasolina", 5));
            
            facade.cadastrarVeiculo(new Carro("Fusca", "Volkswagen", "JDK-1234", "Branco", 1987,
            450.5, facade.verificarDescricao("E"), 2, "Gasolina", 310));
            
            facade.cadastrarCategoria(new Categoria("A", "Subcompacto/Mini", 150, "Carro"));
            
            facade.cadastrarCategoria(new Categoria("B", "Compacto", 195, "Carro"));
            
            facade.cadastrarCategoria(new Categoria("C", "Médio", 240, "Carro"));
            
            facade.cadastrarCategoria(new Categoria("D", "Grande/Familiar", 290, "Carro"));
            
            facade.cadastrarCategoria(new Categoria("E", "Luxo/Executivo", 530, "Carro"));
            
            facade.cadastrarCategoria(new Categoria("SUV", "Utilitário Esportivo", 340, "Carro"));
            
            facade.cadastrarCategoria(new Categoria("MPV", "Minivan/Van", 390, "Carro"));
            
            
            
            facade.cadastrarCategoria(new Categoria("Scooters", "Motos com estrutura de plataforma plana e transmissão automática.",
                    140, "Moto"));
            
            facade.cadastrarCategoria(new Categoria("Naked", "Motos sem carenagem, exibindo o motor e outras partes mecânicas.",
                    190, "Moto"));
            
            facade.cadastrarCategoria(new Categoria("Esportivas", "Motos sem carenagem, exibindo o motor e outras partes mecânicas.",
                    290, "Moto"));
            
            facade.cadastrarCategoria(new Categoria("Touring", "Motos projetadas para viagens de longa distância e conforto.",
                    390, "Moto"));
            
            facade.cadastrarCategoria(new Categoria("Crossovers/Adventure", "Motos versáteis para uso tanto em estradas asfaltadas quanto fora de estrada.",
                    440, "Moto"));
            
            facade.cadastrarCategoria(new Categoria("Cruisers", "Motos com estilo retrô e foco no conforto em viagens longas.",
                    340, "Moto"));
            
            facade.cadastrarCategoria(new Categoria("Off-Road", "MMotos projetadas para trilhas e uso fora de estrada.",
                    290, "Moto"));
            
        } catch (CPFJaCadastradoException e1) {
            System.out.println(e1.getMessage());
        } catch (VeiculoJaCadastradoException e2) {
            System.out.println(e2.getMessage());
        } catch (CategoriaJaCadastradaException e2) {
            System.out.println(e2.getMessage());
        }catch (CategoriaNaoCadastradaException e3) {
            System.out.println(e3.getMessage());
    
    }
  }
}

