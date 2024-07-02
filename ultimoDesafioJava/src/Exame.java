public class Exame {

    private int id_exame;
    private String status;
    private String med_reponsavel;
    private String desc_requerimento;
    private String detalhe_coleta;
    private String desc_laudo;
    private Paciente paciente;
    

    public Exame() {
        this.status = "AGUARDANDO COLETA";
        this.med_reponsavel = "NÃO INFORMADO";
        this.desc_requerimento = "NÃO INFORMADO";
        this.detalhe_coleta = "NÃO INFORMADO";
        this.desc_laudo = "NÃO INFORMADO";
    }
    public String getMed_reponsavel() {
        return med_reponsavel;
    }
    public void setMed_reponsavel(String med_reponsavel) {
        this.med_reponsavel = med_reponsavel;
    }
    public String getDesc_requerimento() {
        return desc_requerimento;
    }
    public void setDesc_requerimento(String desc_requerimento) {
        this.desc_requerimento = desc_requerimento;
    }
    public String getDetalhe_coleta() {
        return detalhe_coleta;
    }
    public void setDetalhe_coleta(String detalhe_coleta) {
        this.detalhe_coleta = detalhe_coleta;
    }
    public String getDesc_laudo() {
        return desc_laudo;
    }
    public void setDesc_laudo(String desc_laudo) {
        this.desc_laudo = desc_laudo;
    }
    public String getPaciente() {
        return paciente.getNome_completo();
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getId_exame() {
        return id_exame;
    }
    public void setId_exame(int id_exame) {
        this.id_exame = id_exame;
    } 
    public String getCpfPaciente(){
        return paciente.getCpf_identificacao();
    }


    
}
