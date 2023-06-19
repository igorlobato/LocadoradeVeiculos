package repository.aluguel;
public class AluguelJaCadastradoException extends Exception{
    public AluguelJaCadastradoException() {
        super("Aluguel já cadastrado");
    }
}
