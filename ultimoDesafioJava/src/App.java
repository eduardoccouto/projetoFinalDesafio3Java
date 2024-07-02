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

        
       while(true){

        System.out.print("\n"   //SEPARAR POR AREA DE ADMINISTRAÇÃO E AREA DE PACIENTE
                + "\n*****************************"
                + "\n*[1] Cadastrar Requisição   *"
                + "\n*[2] Cadastrar Coleta       *"
                + "\n*[3] Cadastrar Laudo        *"
                + "\n*[4] Visualizar exames      *"
                + "\n*[5] Área do Administrador  *"
                + "\n*[0] Sair                   *"
                + "\n*****************************"
                + "\n[OPÇÃO]: ");
        int op = sc.nextInt(); // Lê a opção como uma string e a converte para int

        System.out.println("\nInforme seu CPF para prosseguir: ");
        System.out.print("\nCPF: ");
        String user_cpf = br.readLine(); // Lê a linha inteira e remove espaços em branco

        

        switch(op){
            case 1:
            /*-------------------------------------------------------------------------------------------------------------------- */
            if(validador.retornaLogin(funcionarios, user_cpf) != -1){
                if(funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).verificaPermissao("REQUISICAO")){
                    Exame exame = new Exame();
                    System.out.print("Informe o médico responsável");
                    exame.setMed_reponsavel(br.readLine());
                    System.out.print("Informe o tipo de exame: ");
                    exame.setDesc_requerimento(br.readLine());
                    System.out.print("Informe o CPF do paciente: ");
                    String cpf_paciente = br.readLine();
                    if(validador.retornaLogin(pacientes, cpf_paciente)!= -1)
                        exame.setPaciente(pacientes.get(validador.retornaLogin(pacientes, cpf_paciente)));
                    else{
                        
                        System.out.println("Informe o nome completo do paciente: ");
                        String paciente_nome = br.readLine();
                        controleIDS++;
                        Paciente paciente = new Paciente(paciente_nome, controleIDS, cpf_paciente);
                        exame.setPaciente(paciente);
                        pacientes.add(paciente); //VERIFICAR
                        System.out.println("Paciente cadastrado");

                        //SETTAR O EXAME//

                    }
                    
                        
                      

                }
                else{
                    System.out.println("Você não tem permissão para executar a tarefa.");
                }
                    
                    
            }
            else
            {
                System.out.println("Usuário não encontrado.");
            }
            break;
            /*-------------------------------------------------------------------------------------------------------------------- */

            case 2:
            if(validador.retornaLogin(funcionarios, user_cpf) != -1){
                if(funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).verificaPermissao("COLETA")){
                    System.out.println("Informe o ID do exame: ");
                    int id_busca = br.read();
                    
                    int i = funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).retornaExame(exames, id_busca);
                    if(i < 0){
                        System.out.println("Exame não encontrado.");
                    }
                    else{
                        exames.get(i).setStatus("AGUARDANDO LAUDO");
                        System.out.println("Informe detalhes da coleta: ");
                        exames.get(i).setDetalhe_coleta(br.readLine());
                        //VALIDAR LEITOR
                    }
                    
                }
                else{
                    System.out.println("Você não tem permissão para executar a tarefa.");
                }
            }
            else{
                System.out.println("Usuário não encontrado.");
            }
            break;

            case 3:

            if(validador.retornaLogin(funcionarios, user_cpf) != -1){
                if(funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).verificaPermissao("LAUDO")){
                    System.out.println("Informe o ID do exame: ");
                    int id_busca = br.read();
                    
                    int i = funcionarios.get(validador.retornaLogin(funcionarios, user_cpf)).retornaExame(exames, id_busca);
                    if(i < 0){
                        System.out.println("Exame não encontrado.");
                    }
                    else{
                        exames.get(i).setStatus("LAUDO EMITIDO");
                        System.out.println("Informe a descrição do laudo: ");
                        exames.get(i).setDesc_laudo(br.readLine());
                        //VALIDAR LEITOR
                    }
                }
            }
            else{
                System.out.println("Usuário não encontrado.");
            }
            break;

            case 4: 
            if(validador.retornaLogin(pacientes, user_cpf) != -1){
                procuraExamePaciente procura = new procuraExamePaciente() {}; //VAI DAR CERTO
                procura.retornaExame(exames, user_cpf);
               
            }
            else{
                System.out.println("Paciente não encontrado.");
            }
            break;

            case 5:
                if(user_cpf.equals("05568035075")){
                    System.out.println("[1] Criar login"
                                +"\n[2] Adicionar permissão"
                                +"\n[3] Remover permissão"
                                +"\n[4] Excluir login"
                                +"\n[Opção]: "
                                );
                int op_2 = sc.nextInt();

                switch(op_2){
                    case 1:

                        Funcionario funcionario = new Funcionario();
                        System.out.println("Informe o nome do funcionário");
                        funcionario.setNome_completo(br.readLine());
                        System.out.println("Informe o CPF: ");
                        funcionario.setCpf_identificacao(br.readLine());
                        controleIDS++;
                        funcionario.setId(controleIDS);
                        System.out.println("\nUsuário criado!" + "\n"
                        + funcionario);
                        System.out.println("\nInforme as permissões do usuario"
                        +"\n[1]REQUISIÇÃO"
                        +"\n[2]COLETA"
                        +"\n[3]LAUDO");
                        int opp = sc.nextInt();

                        if(opp == 1)
                            funcionario.adicionaPermissao("REQUISICAO");
                        else if(opp ==2)
                            funcionario.adicionaPermissao("COLETA");
                        else if(opp == 3)
                            funcionario.adicionaPermissao("LAUDO");
                        else
                            System.out.println("\nValor inválido.");

                        funcionarios.add(funcionario);

                    break; //BREAK SUBMENU OP 1

                    case 2:

                        System.out.println("\nInforme o CPF do funcionário: ");
                        String cpf_func = br.readLine();

                        if(validador.retornaLogin(funcionarios, cpf_func)!= -1){
                            System.out.println("\nInforme as permissões do usuário"
                            +"\n[1]REQUISIÇÃO"
                            +"\n[2]COLETA"
                            +"\n[3]LAUDO");
                            int new_op = sc.nextInt();

                            if(new_op == 1)
                                if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("REQUISICAO"))
                                    funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).adicionaPermissao("REQUISICAO");
                                else
                                    System.out.println("Permissão já atribuida ao funcionário.");
                                    
                            else if(new_op == 2)
                                if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("COLETA"))
                                    funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).adicionaPermissao("COLETA");
                                else
                                    System.out.println("Permissão já atribuida ao funcionário.");

                            else if(new_op == 3)
                                if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("LAUDO"))
                                    funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).adicionaPermissao("LAUDO");
                                else
                                    System.out.println("Permissão já atribuida ao funcionário.");
                            else
                                System.out.println("Opção inválida");
                        }
                        else{
                            System.out.println("Funcionário não existe.");
                        } 
                    break;

                    case 3:
                        System.out.println("\nInforme o CPF do funcionário: ");
                        cpf_func = br.readLine();
                        if(validador.retornaLogin(funcionarios, cpf_func)!= -1){
                            System.out.println("\n Selecione para remover as permissões do usuário"
                            +"\n[1] REQUISIÇÃO"
                            +"\n[2] COLETA"
                            +"\n[3] LAUDO");
                            int new_op = br.read();

                        if(new_op == 1)
                            if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("REQUISICAO"))
                                funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).removePermissao("REQUISICAO");
                            else
                                 System.out.println("Funcionário não possui essa liberação.");
                                
                        else if(new_op == 2)
                            if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("COLETA"))
                                funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).removePermissao("COLETA");
                            else
                                System.out.println("Funcionário não possui essa liberação.");

                        else if(new_op == 3)
                            if(!funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).verificaPermissao("LAUDO"))
                                funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)).removePermissao("LAUDO");
                            else
                                 System.out.println("Funcionário não possui essa liberação.");
                        else
                            System.out.println("Opção inválida");

                        }
                        else{
                            System.out.println("Funcionário não existe."); 
                        }
                    break;

                    case 4:
                        System.out.println("\nInforme o CPF do funcionário: ");
                        cpf_func = br.readLine();
                        if(validador.retornaLogin(funcionarios, cpf_func) != -1){
                            admin.removeFuncionario(funcionarios.get(validador.retornaLogin(funcionarios, cpf_func)), funcionarios);
                            System.out.println("Removido com sucesso!");
                        }
                        else{
                            System.out.println("Funcionário não encontrado.");
                        }
                    
                }
                
                }
                else{
                    System.out.println("Usuário não autorizado");
                }
                


            break; 


        }
    }

    }
}
