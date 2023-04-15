package parser.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import parser.config.HibernateUtils;
import parser.dao.HeaderFieldParserRepo;
import parser.entity.HeaderFieldParser;

public class HeaderFieldParserRepoImpl implements HeaderFieldParserRepo {

	@Override
	public List<HeaderFieldParser> getHeaderFieldParserByFileFormatId(Integer fileFormatId) {
		List<HeaderFieldParser> headerFieldParserList = null;
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession())
		{
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<HeaderFieldParser> criteriaQuery = criteriaBuilder.createQuery(HeaderFieldParser.class);
			Root<HeaderFieldParser> root = criteriaQuery.from(HeaderFieldParser.class);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("fileFormat").get("fileFormatId"),fileFormatId));
			transaction = session.getTransaction();
			headerFieldParserList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
			
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			
		}
		return headerFieldParserList;
	}

}
