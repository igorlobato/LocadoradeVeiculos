package repository.cliente;

import repository1.RepositoryException;

public class CPFJaCadastradoException extends RepositoryException {

    public CPFJaCadastradoException() {
        super("CPF já cadastrado");
    }
}