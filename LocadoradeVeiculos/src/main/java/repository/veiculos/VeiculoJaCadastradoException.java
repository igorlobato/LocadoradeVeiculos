package repository.veiculos;

import repository1.RepositoryException;

public class VeiculoJaCadastradoException extends RepositoryException{
    
    public VeiculoJaCadastradoException() {
        super("Veículo já cadastrado");
    }
}
