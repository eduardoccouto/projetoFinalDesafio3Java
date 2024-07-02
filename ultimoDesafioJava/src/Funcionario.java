import java.util.ArrayList;

public class Funcionario extends Pessoa implements procuraExame{ //TENHO RECEIO
    
    ArrayList<String> permissoes;

    public Funcionario(){
        permissoes = new ArrayList<>();
    }
    //verifica se o usuario tem a permissão necessária;
    public boolean verificaPermissao(String permissao){
        return permissoes.contains(permissao);
    }

    //Adiciona permissão ao usuário
    public boolean adicionaPermissao(String permissao){
        return permissoes.add(permissao);   
    }
    public boolean removePermissao(String permissao){
        return permissoes.remove(permissao);
    }

    

    //VARIFICAR MAIS TARDE
    @Override
    public String toString() {
        return "Funcionario [permissoes=" + permissoes + "]";
    }

    

    



}
