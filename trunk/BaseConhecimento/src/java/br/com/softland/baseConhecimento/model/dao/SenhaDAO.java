package br.com.softland.baseConhecimento.model.dao;

import br.com.softland.baseConhecimento.bean.AnalistaBean;
import br.com.softland.baseConhecimento.model.connection.ConexaoAgenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SenhaDAO {

    private Connection conexao = null;

    public SenhaDAO() throws Exception {
        this.conexao = ConexaoAgenda.getConnection();

        if (conexao == null) {
            new Exception("Erro na conexão com o Banco");
        }
    }
    
    public AnalistaBean consultaAnalista(String nome, String senha) throws Exception {
        AnalistaBean analista = new AnalistaBean();
        String sql = "select * from analista a where a.descricao = ? and a.analista_pass = ?";

        PreparedStatement stm = conexao.prepareStatement(sql);
        stm.setString(1, nome);
        stm.setString(2, senha);

        ResultSet result = stm.executeQuery();

        if (result.next()) {
            analista.setCodigo(result.getString("CODIGO"));
            analista.setNome(result.getString("DESCRICAO"));
            //analista.setSenha(result.getString("ANALISTA_PASS"));
            //analista.setAtivo(result.getInt("ATIVO") == 1 ? true : false);
            //analista.setDesenvolvedor(result.getInt("DESENVOLVEDOR") == 1 ? true : false);

            return analista;
        } else {
            return null;
        }
    }
}
