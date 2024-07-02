public abstract class Pessoa {
    protected String nome_completo;
    protected int id;
    protected String cpf_identificacao;

    public Pessoa() {
    }
    public Pessoa(String nome_completo, int id, String cpf_identificacao) {
        this.nome_completo = nome_completo;
        this.id = id;
        this.cpf_identificacao = cpf_identificacao;
    }
    public String getNome_completo() {
        return nome_completo;
    }
    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCpf_identificacao() {
        return cpf_identificacao;
    }
    public void setCpf_identificacao(String cpf_identificacao) {
        this.cpf_identificacao = cpf_identificacao;
    }

    @Override
    public String toString() {
        return "\nNome completo: " + getNome_completo()
              +"\nUsuário: " + getId()
              +"\nCPF: " + getCpf_identificacao();
    }
    

    
}
