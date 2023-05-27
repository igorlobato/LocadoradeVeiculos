package model.cliente;


public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String datadenascimento;
    private String sexo;
    private int anocnh;

    public Cliente(String nome, String cpf, String endereco, String telefone, String datadenascimento, String sexo, int anocnh) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.datadenascimento = datadenascimento;
        this.sexo = sexo;
        this.anocnh = anocnh;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDatadenascimento() {
        return datadenascimento;
    }

    public void setDatadenascimento(String datadenascimento) {
        this.datadenascimento = datadenascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getAnocnh() {
        return anocnh;
    }

    public void setAnocnh(int anocnh) {
        this.anocnh = anocnh;
    }
    
    public void Garagem(){
        
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", telefone=" + telefone + ", datadenascimento=" + datadenascimento + ", sexo=" + sexo + ", anocnh=" + anocnh + '}';
    }
    
}
