import java.util.ArrayList;

public interface procuraExamePaciente {

//procura o exame com base no cpf e printa todas as informações
        default void retornaExame(ArrayList <Exame> exames, String cpf){
        for(int i = 0; i < exames.size(); i++){
            if(exames.get(i).getCpfPaciente().equals(cpf)){
                System.out.println(exames.get(i));

            }
                
            
        }
        
    }
}
