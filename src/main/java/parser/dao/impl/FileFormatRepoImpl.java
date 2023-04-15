package parser.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import parser.config.HibernateUtils;
import parser.dao.FileFormatRepo;
import parser.entity.FileFormat;

public class FileFormatRepoImpl implements FileFormatRepo {

	@Override
	public List<FileFormat> getFileFormatRepoByAccountId(Integer accountId) {
		List<FileFormat> fileFormatList = null;
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<FileFormat> criteriaQuery = criteriaBuilder.createQuery(FileFormat.class);
			Root<FileFormat> root = criteriaQuery.from(FileFormat.class);
//			root.join("itemLinePatternSet",JoinType.LEFT);
//			root.join("headerFieldParserSet",JoinType.LEFT);
			//root.join("itemLinePattern.itemLinePosition",JoinType.LEFT);
//			root.fetch("itemLinePatternSet",JoinType.LEFT).fetch("itemLinePositionSet",JoinType.LEFT);
			javax.persistence.criteria.Fetch<Object, Object> itemLinePatternSet = root.fetch("itemLinePatternSet");
			javax.persistence.criteria.Fetch<Object, Object> headerFieldParserSet = root.fetch("headerFieldParserSet");
			criteriaQuery.select(root).distinct(true);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("account").get("accountId"),accountId));
			//criteriaQuery.where(criteriaBuilder.equal(root.get("account").get("accountId"),accountId));
			transaction = session.getTransaction();
			fileFormatList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return fileFormatList;
	}
	
	public List<FileFormat> getFileFormatRepoByAccountIdV2(Integer accountId){
		List<FileFormat> fileFormatList = null;
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<FileFormat> criteriaQuery = criteriaBuilder.createQuery(FileFormat.class);
			Root<FileFormat> root = criteriaQuery.from(FileFormat.class);
			root.fetch("headerFieldParserSet",JoinType.LEFT);
			root.fetch("itemLinePatternSet",JoinType.LEFT).fetch("itemLinePositionSet",JoinType.LEFT);
			criteriaQuery.select(root).distinct(true);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("account").get("accountId"),accountId));
			transaction = session.getTransaction();
			fileFormatList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return fileFormatList;
	}
	
	public List<FileFormat> getFileFormatRepoByAccountIdV3(Integer accountId){
		List<FileFormat> fileFormatList = null;
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<FileFormat> criteriaQuery = criteriaBuilder.createQuery(FileFormat.class);
			Root<FileFormat> root = criteriaQuery.from(FileFormat.class);
			Fetch<Object, Object> headerFieldParserSet = root.fetch("headerFieldParserSet");
			Fetch<Object, Object> fetch = root.fetch("itemLinePatternSet").fetch("itemLinePositionSet",JoinType.LEFT);
			criteriaQuery.select(root).distinct(true);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("account").get("accountId"),accountId));
			transaction = session.getTransaction();
			fileFormatList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return fileFormatList;
	}


}
