package repository.veiculos;

import repository1.RepositoryException;

public class VeiculoNaoCadastradoException extends RepositoryException{
    public VeiculoNaoCadastradoException() {
        super("Veículo não cadastrado");
    }
}
