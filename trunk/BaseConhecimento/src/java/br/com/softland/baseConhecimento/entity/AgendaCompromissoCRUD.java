package br.com.softland.baseConhecimento.entity;

import AgendaUtil.HibernateUtil;
import org.hibernate.*;

public class AgendaCompromissoCRUD {

    public void salvar(Agecomp compromisso) {
        Session sessao = null;
        Transaction transacao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.save(compromisso);
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("Erro ao Inserir Compromisso. Erro: " + e.getMessage());
        } finally {

            try {
                sessao.close();
            } catch (Throwable r) {
                System.out.println("Erro ao encerrar operação de Inserção. Erro: " + r.getMessage());
            }
        }
    }

    public void atualizar(Agecomp compromisso) {
        Session sessao = null;
        Transaction transacao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.update(compromisso);
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("Erro ao Alterar um Compromisso. Erro: " + e.getMessage());
        } finally {
            try {
                sessao.close();
            } catch (Throwable r) {
                System.out.println("Erro ao encerrar operação de Alteração de Compromisso. Erro: " + r.getMessage());
            }
        }
    }

    public void excluir(Agecomp compromisso) {
        Session sessao = null;
        Transaction transacao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.delete(compromisso);
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("Erro ao excluir um Compromisso. Erro: " + e.getMessage());
        } finally {
            try {
                sessao.close();
            } catch (Throwable r) {
                System.out.println("Erro ao encerrar operação de Excluir Compromisso. Erro: " + r.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        AgendaCompromissoCRUD age = new AgendaCompromissoCRUD();
        
    }
}
