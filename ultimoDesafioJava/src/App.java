import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        Scanner sc = new Scanner(System.in);
        validaLogin validador = new validaLogin();
        ArrayList<Exame> exames = new ArrayList<>();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Admin admin = new Admin();
        int controleIDS = 0;
        int controle_exames = 0;

        
       while(true){

        System.out.print("""
                         
                         
                         *****************************
                         *[1] Cadastrar Requisição   *
                         *[2] Cadastrar Coleta       *
                         *[3] Cadastrar Laudo        *
                         *[4] Visualizar exames      *
                         *[5] Área do Administrador  *
                         *[0] Sair                   *
                         *****************************
                         [Opção]: """ 
        );
        int op = sc.nextInt(); // Lê a opção como uma string e a converte para int

        if(op == 0){
            System.out.println("Encerrando programa.");
            System.exit(0);
        }

        System.out.print("\nInforme seu CPF para prosseguir: ");
        System.out.print("\nCPF: ");
        String user_cpf = br.readLine(); // Lê a linha inteira e remove espaços em branco

        

        switch(op){
            case 1 -> {
                    /*-------------------------------------------------------------------------------------------------------------------- */
                    if (validador.retornaLogin(funcionarios, user_cpf) != -1) {
                        if(funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).verificaPermissao("REQUISICAO")){
                            controle_exames++;
                            Exame exame = new Exame();
                            exame.setId_exame(controle_exames);
                            System.out.print("Informe o médico responsável: ");
                            exame.setMed_reponsavel(br.readLine());
                            System.out.print("Informe o tipo de exame: ");
                            exame.setDesc_requerimento(br.readLine());
                            System.out.print("Informe o CPF do paciente: ");
                            String cpf_paciente = br.readLine();
                            if(validador.retornaLogin(pacientes, cpf_paciente)!= -1){
                                exame.setPaciente(pacientes.get(validador.retornaLogin(pacientes, cpf_paciente)));
                                exames.add(exame);
                                System.out.print("Requisição criada com sucesso.");
                            }
                                
                            else{
                                
                                System.out.print("Informe o nome completo do paciente: ");
                                String paciente_nome = br.readLine();
                                controleIDS++;
                                Paciente paciente = new Paciente(paciente_nome, controleIDS, cpf_paciente);
                                exame.setPaciente(paciente);
                                pacientes.add(paciente); 
                                System.out.print("Paciente cadastrado! ");
                                exames.add(exame);
                                System.out.print("Requisição criada com sucesso.");

                            }
                            
                            
                            
                            
                        }
                        else{
                            System.out.print("Você não tem permissão para executar a tarefa.");
                        }   } else {
                        System.out.print("Usuário não encontrado.");
                    }
                    /*-------------------------------------------------------------------------------------------------------------------- */
                }
            /*-------------------------------------------------------------------------------------------------------------------- */

            case 2 -> {
                if(validador.retornaLogin(funcionarios, user_cpf) != -1){
                    if(funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).verificaPermissao("COLETA")){
                        System.out.print("Informe o ID do exame: ");
                        int id_busca = sc.nextInt();
                        
                        int i = funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).retornaExame(exames, id_busca);
                        if(i < 0){
                            System.out.print("Exame não encontrado.");
                        }
                        else{
                            exames.get(i).setStatus("AGUARDANDO LAUDO");
                            System.out.print("Informe detalhes da coleta: ");
                            exames.get(i).setDetalhe_coleta(br.readLine());
                            
                        }
                        
                    }
                    else{
                        System.out.print("Você não tem permissão para executar a tarefa.");
                    }
                }
                else{
                    System.out.print("Usuário não encontrado.");
                }   }

            case 3 -> {
                if(validador.retornaLogin(funcionarios, user_cpf) != -1){
                    if(funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).verificaPermissao("LAUDO")){
                        System.out.print("Informe o ID do exame: ");
                        int id_busca = sc.nextInt();
                        
                        int i = funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).retornaExame(exames, id_busca);
                        if(i < 0){
                            System.out.print("Exame não encontrado.");
                        }
                        else{
                            exames.get(i).setStatus("LAUDO EMITIDO");
                            System.out.print("Informe a descrição do laudo: ");
                            exames.get(i).setDesc_laudo(br.readLine());
                            //VALIDAR LEITOR
                        }
                    }
                }
                else{
                    System.out.print("Usuário não encontrado.");
                }   }

            case 4 -> {
                if(validador.retornaLogin(pacientes, user_cpf) != -1){
                    procuraExamePaciente procura = new procuraExamePaciente() {}; //VAI DAR CERTO
                    procura.retornaExame(exames, user_cpf);
                    
                }
                else{
                    System.out.print("Paciente não encontrado.");
                }   }

            case 5 -> {
                if(user_cpf.equals("05568035075")){
                    System.out.print("""
                                                           [1] Criar login
                                                           [2] Adicionar permissão
                                                           [3] Remover permissão
                                                           [4] Excluir login
                                                           [opção: """);
                    int op_2 = sc.nextInt();
                    
                    switch(op_2){
                        case 1 -> {
                            Funcionario funcionario = new Funcionario();
                            System.out.print("Informe o nome do funcionário");
                            funcionario.setNome_completo(br.readLine());
                            System.out.print("Informe o CPF: ");
                            funcionario.setCpf_identificacao(br.readLine());
                            controleIDS++;
                            funcionario.setId(controleIDS);

                            System.out.print( """
                                                                                                    Informe as permissões do usuario
                                                                       [1]REQUISIÇÃO
                                                                       [2]COLETA
                                                                       [3]LAUDO""");
                            int opp = sc.nextInt();
                            
                            switch (opp) {
                                case 1 -> funcionario.adicionaPermissao("REQUISICAO");
                                case 2 -> funcionario.adicionaPermissao("COLETA");
                                case 3 -> funcionario.adicionaPermissao("LAUDO");
                                default -> System.out.print("\nValor inválido.");
                            }
                            funcionarios.add(funcionario);
                            System.out.print("""

                            Usuário criado!

                        """ + funcionario);
                            
                        }
                        case 2 -> {
                            System.out.print("\nInforme o CPF do funcionário: ");
                            String cpf_func = br.readLine();
                            
                            if(validador.retornaLogin(funcionarios, cpf_func)!= -1){
                                System.out.print("""
                                                                                                                Informe as permissoes do usuario:
                                                                               [1]REQUISICAO
                                                                               [2]COLETA
                                                                               [3]LAUDO""");
                                int new_op = sc.nextInt();
                                
                                switch (new_op) {
                                    case 1 -> {
                                        if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("REQUISICAO"))
                                            funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).adicionaPermissao("REQUISICAO");
                                        else
                                            System.out.print("Permissão já atribuida ao funcionário.");
                                    }
                                    case 2 -> {
                                        if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("COLETA"))
                                            funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).adicionaPermissao("COLETA");
                                        else
                                            System.out.print("Permissão já atribuida ao funcionário.");
                                    }
                                    case 3 -> {
                                        if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("LAUDO"))
                                            funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).adicionaPermissao("LAUDO");
                                        else
                                            System.out.print("Permissão já atribuida ao funcionário.");
                                    }
                                    default -> System.out.print("Opção inválida");
                                }
                            }
                            else{
                                System.out.print("Funcionário não existe.");
                            }
                        }
                        
                        case 3 -> {
                            System.out.print("\nInforme o CPF do funcionário: ");
                            String cpf_func = br.readLine();
                            if(validador.retornaLogin(funcionarios, cpf_func)!= -1){
                                System.out.print( """
                                                                                                             Selecione para remover as permissoes do usuario:
                                                                           [1] REQUISICAO
                                                                           [2] COLETA
                                                                           [3] LAUDO""" );
                                int new_op = sc.nextInt();
                                
                                switch (new_op) {
                                    case 1 -> {
                                        if(funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("REQUISICAO")) {
                                            funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).removePermissao("REQUISICAO");
                                            System.out.print("Permissão removida com sucesso!");
                                        }
                                        
                                        else {
                                            System.out.print("Funcionário não possui essa liberação.");
                                        }
                                    }
                                    case 2 -> {
                                        if(funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("COLETA")){
                                            funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).removePermissao("COLETA");
                                        }
                                        else {
                                            System.out.print("Funcionário não possui essa liberação.");
                                        }
                                    }
                                    case 3 -> {
                                        if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("LAUDO")) {
                                            funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).removePermissao("LAUDO");
                                            
                                        }    else{
                                            System.out.print("Funcionário não possui essa liberação.");
                                        }
                                    }
                                    default -> System.out.print("Opção inválida");
                                }
                            }
                            else{
                                System.out.print("Funcionário não existe.");
                            }
                        }
                        
                        case 4 -> {
                            System.out.print("\nInforme o CPF do funcionário: ");
                            String cpf_func = br.readLine();
                            if(validador.retornaLogin(funcionarios, cpf_func) != -1){
                                admin.removeFuncionario(funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)), funcionarios);
                                System.out.print("Removido com sucesso!");
                            }
                            else{
                                System.out.print("Funcionário não encontrado.");
                            }
                        }
                        
                    }
                    
                }
                else{
                    System.out.print("Usuário não autorizado");
                }
                } 

            default -> System.out.print("Opção inválida");

        }
    }

    }
}
