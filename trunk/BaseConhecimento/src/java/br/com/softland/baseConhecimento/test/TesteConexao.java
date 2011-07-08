package br.com.softland.baseConhecimento.test;

import br.com.softland.baseConhecimento.model.connection.ConexaoAgenda;
import java.sql.Connection;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "testeConexao")
public class TesteConexao {
    
    private String conexao = null;

    public String getConexao() {
        return conexao;
    }

    public void setConexao(String conexao) {
        this.conexao = conexao;
    }
    
    public void TestarConexaoComAgenda() {
        Connection con = ConexaoAgenda.getConnection();
        
        if (con != null) {
            setConexao("Conexão com Agenda realizada com sucesso!");
        } else {
            setConexao("Erro ao tentar conectar com a Agenda!");
        }
    }
}
