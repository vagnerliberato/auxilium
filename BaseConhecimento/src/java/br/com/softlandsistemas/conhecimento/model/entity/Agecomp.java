package br.com.softlandsistemas.conhecimento.model.entity;
// Generated 01/11/2011 21:54:20 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Agecomp generated by hbm2java
 */
public class Agecomp  implements java.io.Serializable {


     private String codigoVisita;
     private String analista;
     private String empresa;
     private Date dataPrev;
     private String horaPrev;
     private Date dataCheg;
     private String horaCheg;
     private Date dataSaida;
     private String horaSaida;
     private Integer horasMensal;
     private byte[] historico;
     private String visita;
     private String status;
     private String contato;
     private Date dataCriacao;

    public Agecomp() {
    }

	
    public Agecomp(String codigoVisita, String analista, String empresa) {
        this.codigoVisita = codigoVisita;
        this.analista = analista;
        this.empresa = empresa;
    }
    public Agecomp(String codigoVisita, String analista, String empresa, Date dataPrev, String horaPrev, Date dataCheg, String horaCheg, Date dataSaida, String horaSaida, Integer horasMensal, byte[] historico, String visita, String status, String contato, Date dataCriacao) {
       this.codigoVisita = codigoVisita;
       this.analista = analista;
       this.empresa = empresa;
       this.dataPrev = dataPrev;
       this.horaPrev = horaPrev;
       this.dataCheg = dataCheg;
       this.horaCheg = horaCheg;
       this.dataSaida = dataSaida;
       this.horaSaida = horaSaida;
       this.horasMensal = horasMensal;
       this.historico = historico;
       this.visita = visita;
       this.status = status;
       this.contato = contato;
       this.dataCriacao = dataCriacao;
    }
   
    public String getCodigoVisita() {
        return this.codigoVisita;
    }
    
    public void setCodigoVisita(String codigoVisita) {
        this.codigoVisita = codigoVisita;
    }
    public String getAnalista() {
        return this.analista;
    }
    
    public void setAnalista(String analista) {
        this.analista = analista;
    }
    public String getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public Date getDataPrev() {
        return this.dataPrev;
    }
    
    public void setDataPrev(Date dataPrev) {
        this.dataPrev = dataPrev;
    }
    public String getHoraPrev() {
        return this.horaPrev;
    }
    
    public void setHoraPrev(String horaPrev) {
        this.horaPrev = horaPrev;
    }
    public Date getDataCheg() {
        return this.dataCheg;
    }
    
    public void setDataCheg(Date dataCheg) {
        this.dataCheg = dataCheg;
    }
    public String getHoraCheg() {
        return this.horaCheg;
    }
    
    public void setHoraCheg(String horaCheg) {
        this.horaCheg = horaCheg;
    }
    public Date getDataSaida() {
        return this.dataSaida;
    }
    
    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }
    public String getHoraSaida() {
        return this.horaSaida;
    }
    
    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }
    public Integer getHorasMensal() {
        return this.horasMensal;
    }
    
    public void setHorasMensal(Integer horasMensal) {
        this.horasMensal = horasMensal;
    }
    public byte[] getHistorico() {
        return this.historico;
    }
    
    public void setHistorico(byte[] historico) {
        this.historico = historico;
    }
    public String getVisita() {
        return this.visita;
    }
    
    public void setVisita(String visita) {
        this.visita = visita;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getContato() {
        return this.contato;
    }
    
    public void setContato(String contato) {
        this.contato = contato;
    }
    public Date getDataCriacao() {
        return this.dataCriacao;
    }
    
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }




}


