package parser.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import parser.config.HibernateUtils;
import parser.dao.ItemLinePatternRepo;
import parser.entity.ItemLinePattern;

public class ItemLinePatternRepoImpl implements ItemLinePatternRepo {

	@Override
	public List<ItemLinePattern> findItemLinePatternByFileFormatId(Integer fileFormatId) {
		List<ItemLinePattern> itemLinePatternList = null;
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ItemLinePattern> criteriaQuery = criteriaBuilder.createQuery(ItemLinePattern.class);
			Root<ItemLinePattern> root = criteriaQuery.from(ItemLinePattern.class);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("fileFormat").get("fileFormatId"),fileFormatId));
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("sequence")));
			transaction = session.getTransaction();
			itemLinePatternList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		}catch(Exception e){
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return itemLinePatternList;
	}

}
