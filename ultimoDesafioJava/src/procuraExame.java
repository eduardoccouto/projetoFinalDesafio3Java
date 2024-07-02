import java.util.ArrayList;

public interface procuraExame {
    
    //retorna o indice do exame com base no id
    default int retornaExame(ArrayList <Exame> exames, int id){
        for(int i = 0; i < exames.size(); i++){
            if(exames.get(i).getId_exame() == id){
                return i;
            }
                
            
        }
        return -1;
    }
    
}
