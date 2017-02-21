package com.teste;

import org.hibernate.Session;

public class ArquivoConfirmacaoDAOImpl implements ArquivoConfirmacaoDAO {

	Session session = HibernateUtil.getSessionFactory().openSession();
	
	@Override
	public void save(ArquivoConfirmacao arquivoConfirmacao){
		session.beginTransaction();
		session.save(arquivoConfirmacao);
		session.getTransaction().commit();
		session.close();
	}	
}
