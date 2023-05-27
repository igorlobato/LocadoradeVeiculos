package repository.veiculos;
public class VeiculoNaoCadastradoException extends Exception{
    public VeiculoNaoCadastradoException() {
        super("Veículo não cadastrado");
    }
}
