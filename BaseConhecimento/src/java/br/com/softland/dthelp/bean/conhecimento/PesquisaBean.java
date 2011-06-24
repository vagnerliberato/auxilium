package br.com.softland.dthelp.bean.conhecimento;

/**
 *
 * @author arthemus
 */
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "pesquisa")
public class PesquisaBean {

    private String texto = null;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<String> pesquisa(String referencia) {

        List<String> lista = new ArrayList<String>();

        //realizar uma pesquisa LIKE no banco de dados e trazer os valores
        //do campo referencia da tabela CONHECIMENTO

        for (int i = 0; i < 10; i++) {
            lista.add(""+i);
        }

        return lista;
    }
}
