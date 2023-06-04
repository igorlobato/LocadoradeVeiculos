package repository.veiculos;
public class VeiculoJaCadastradoException extends Exception{
    
    public VeiculoJaCadastradoException() {
        super("Veículo já cadastrado");
    }
}
