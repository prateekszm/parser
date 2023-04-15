package parser.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import parser.config.HibernateUtils;
import parser.dao.ClaimRepo;
import parser.entity.Claim;
import parser.entity.FileFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClaimRepoImpl implements ClaimRepo {
    @Override
    public void save(Claim claim) {
        Transaction transaction = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.save(claim);
            transaction.commit();
        }catch(Exception e) {
        	e.printStackTrace();
            if(transaction!=null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void save(List<Claim> claimList) {
        Transaction transaction = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            for(Claim claim:claimList){
                session.save(claim);
            }
            transaction.commit();
        }catch(Exception e) {
            if(transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
