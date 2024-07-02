import java.util.ArrayList;

public class validaLogin {

    //NECESSÁRIO PARA VALIDAR A POSIÇÃO NO ARRAY DO LOGIN DE QUALQUER USUARIO
    public int retornaLogin(ArrayList<? extends Pessoa> funcionarios, String cpf){
        for(int i = 0; i < funcionarios.size(); i++){
            if(funcionarios.get(i).getCpf_identificacao().equals(cpf)){
                return i;
            }
        }
        return -1;
    }


}
