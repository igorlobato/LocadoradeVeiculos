package repository.cliente;

import repository1.RepositoryException;

public class ClienteNaoCadastradoException extends RepositoryException {

    public ClienteNaoCadastradoException() {
        super("Cliente não cadastrado");
    }
    
    
}
