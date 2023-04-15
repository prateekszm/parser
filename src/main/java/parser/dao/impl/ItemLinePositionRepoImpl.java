package parser.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import parser.config.HibernateUtils;
import parser.dao.ItemLinePositionRepo;
import parser.entity.ItemLinePattern;
import parser.entity.ItemLinePosition;

public class ItemLinePositionRepoImpl implements ItemLinePositionRepo {

	@Override
	public List<ItemLinePosition> getItemLinePostionByitemLinePatternId(Integer itemLinePatternId) {
		List<ItemLinePosition> itemLinePositionList = null;
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ItemLinePosition> criteriaQuery = criteriaBuilder.createQuery(ItemLinePosition.class);
			Root<ItemLinePosition> root = criteriaQuery.from(ItemLinePosition.class);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("itemLinePattern").get("itemLinePatternId"),itemLinePatternId));
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("sequence")));
			transaction = session.getTransaction();
			itemLinePositionList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		}catch(Exception e){
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return itemLinePositionList;
	}

	@Override
	public List<ItemLinePosition> getItemLinePostionByitemLinePatternList(List<ItemLinePattern> itemLinePatternList) {
		List<ItemLinePosition> itemLinePositionList = null;
		ArrayList<Integer> itemLinePatternIds = new ArrayList<>();
		Transaction transaction = null;
		for(ItemLinePattern linePattern: itemLinePatternList) {
			itemLinePatternIds.add(linePattern.getItemLinePatternId());
		}
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ItemLinePosition> criteriaQuery = criteriaBuilder.createQuery(ItemLinePosition.class);
			Root<ItemLinePosition> root = criteriaQuery.from(ItemLinePosition.class);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			
			//criteriaQuery.where(criteriaBuilder.equal(root.get("itemLinePattern").get("itemLinePatternId").in(itemLinePatternIds)));
			
			
			//criteriaQuery.orderBy(criteriaBuilder.asc(root.get("sequence"))); //giving error
			
			transaction = session.beginTransaction();
			itemLinePositionList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		}catch(Exception e){
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return itemLinePositionList;
	}

	

}
