import java.util.ArrayList;

public class Admin extends Pessoa {

//Login default
    public Admin(){
        this.nome_completo = "ADMIN";
        this.cpf_identificacao = "05568035075";
        this.id = 999;
        
    }

//remove o funcionario com base no indice do array
    public boolean removeFuncionario(Funcionario funcionario, ArrayList<Funcionario> funcionarios){
        return funcionarios.remove(funcionario);
    }

    
}
