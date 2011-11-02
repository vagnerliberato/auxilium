package br.com.softland.baseConhecimento.ConfigHibernate;


import org.hibernate.Session;
/**
 *
 * @author VAGNER
 */
public class ConectaHibernateFirebird {
    
    public static void main(String[] args) {
        Session sessao = null;
        try{
            System.out.println("Conectou!!!");
        }finally{
            sessao.close();
        }
    }
}
