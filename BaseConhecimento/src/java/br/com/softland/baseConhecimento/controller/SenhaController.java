package br.com.softland.baseConhecimento.controller;

import br.com.softland.baseConhecimento.bean.AnalistaBean;
import br.com.softland.baseConhecimento.model.dao.SenhaDAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "senhaController")
public class SenhaController {

    private String nome = null;
    private String senha = null;
    private String erro = null;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public void validaAnalista(ActionEvent action) throws Exception {
        SenhaDAO dao = new SenhaDAO();
        AnalistaBean analista = dao.consultaAnalista(getNome(), getSenha());

        if (analista == null) {
            setErro("Analista " + getNome() + " não encontrado");
        }
    }

    private String encriptString(String texto) {

        int c1 = 52845;
        int c2 = 22719;
        int i = 1;
        int key = 0;
        String textoEnc = null;

        key = 7666;

        for (i = 1; i < texto.length(); i++) {
            //textoEnc = textoEnc + Integer.toHexString()
                    
        }

        return null;
    }
}
//    function Encrypt(const ATexto: String): String;
//    const
//      C1 = 52845;
//      C2 = 22719;
//    var
//      I: Byte;
//      Key: Word;
//    begin
//      Result := '';
//
//      Key := 7666;
//
//      for I := 1 to Length(ATexto) do
//      begin
//        Result := Result + IntToHex(byte(char(byte(ATexto[I]) xor (Key shr 8))), 2);
//        Key := (byte(char(byte(ATexto[I]) xor (Key shr 8))) + Key) * C1 + C2;
//      end;
//    end;