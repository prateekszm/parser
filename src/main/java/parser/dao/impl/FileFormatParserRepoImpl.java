package parser.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import parser.config.HibernateUtils;
import parser.dao.FileFormatParserRepo;
import parser.entity.FileFormatParser;

public class FileFormatParserRepoImpl implements FileFormatParserRepo {

	@Override
	public FileFormatParser getFileFormatParserByFileFormatId(Integer fileFormatId) {
		List<FileFormatParser> fileFormatParserList = null;
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<FileFormatParser> criteriaQuery = criteriaBuilder.createQuery(FileFormatParser.class);
			Root<FileFormatParser> root = criteriaQuery.from(FileFormatParser.class);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("fileFormat").get("fileFormatId"),fileFormatId));
			transaction = session.getTransaction();
			fileFormatParserList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
			return fileFormatParserList.get(0);
	}

	@Override
	public List<FileFormatParser> getFileFormatParserByAccountId(Integer accountId) {
		List<FileFormatParser> fileFormatParserList = null;
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<FileFormatParser> criteriaQuery = criteriaBuilder.createQuery(FileFormatParser.class);
			Root<FileFormatParser> root = criteriaQuery.from(FileFormatParser.class);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("account").get("accountId"),accountId));
			transaction = session.getTransaction();
			transaction.begin();
			fileFormatParserList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return fileFormatParserList;
	}


}
