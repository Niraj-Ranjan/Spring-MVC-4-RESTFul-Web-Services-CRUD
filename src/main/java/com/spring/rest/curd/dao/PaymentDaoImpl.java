package com.spring.rest.curd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.rest.curd.model.Payment;

@Repository
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public String payNow(Payment payment) {
		getSession().save(payment);
		return "Payment successfull with amount : " + payment.getAmount();
	}

	private Session getSession() {
		Session session = null;
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException ex) {
			session = factory.openSession();
		}
		return session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getTransactionInfo(String vendor) {
		return getSession().createCriteria(Payment.class).add(Restrictions.eq("vendor", vendor)).list();

	}

	@Override
	public String getTransactionCOunt() {
		
		 Session session = factory.getCurrentSession();
		 Criteria criteria = session.createCriteria(Payment.class);
		 criteria.setProjection(Projections.rowCount());
		 List payment = criteria.list();
		 long rowCount = 0;
		 if (payment!=null) {
			 rowCount = (long) payment.get(0);
			 
		 }
		 String numberAsString = Long.toString(rowCount);
		 return numberAsString;
		 }

	@Override
	public String gettranbyjson() {
		
		Session session = factory.getCurrentSession();
		 Criteria criteria = session.createCriteria(Payment.class);
		 criteria.setProjection(Projections.rowCount());
		 List payment = criteria.list();
		 long rowCount = 0;
		 if (payment!=null) {
			 rowCount = (long) payment.get(0);
			 
		 }
		 String numberAsString = Long.toString(rowCount);
		 return numberAsString;
		
	}


		
	}
