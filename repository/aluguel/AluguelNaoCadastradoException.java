package repository.aluguel;
public class AluguelNaoCadastradoException extends Exception{
    public AluguelNaoCadastradoException() {
        super("Aluguel não cadastrado");
    }
}
