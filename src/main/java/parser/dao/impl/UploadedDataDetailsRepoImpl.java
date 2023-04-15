package parser.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import parser.config.HibernateUtils;
import parser.config.ParsingStatusAndConstants;
import parser.dao.UploadedDataDetailsRepo;
import parser.entity.UploadedDataDetails;

public class UploadedDataDetailsRepoImpl implements UploadedDataDetailsRepo {

	@Override
	public void saveUploadedDataDetails(UploadedDataDetails uploadedDataDetails) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			populateUploadedDataDetails(uploadedDataDetails);
			session.save(uploadedDataDetails);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public List<UploadedDataDetails> getUploadedDataDetailsByAccountId(Integer id) {
		List<UploadedDataDetails> uploadedDataDetailsList = null;
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<UploadedDataDetails> criteriaQuery = criteriaBuilder.createQuery(UploadedDataDetails.class);
			Root<UploadedDataDetails> root = criteriaQuery.from(UploadedDataDetails.class);
			root.join("fileAttachment",JoinType.LEFT);
			javax.persistence.criteria.Fetch<Object, Object> fileAttachment = root.fetch("fileAttachment");
			criteriaQuery.select(root).distinct(true);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("processingStatus"), 0));
			criteriaQuery.where(criteriaBuilder.equal(root.get("account").get("accountId"), id));
			transaction = session.beginTransaction();
			uploadedDataDetailsList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return uploadedDataDetailsList;
	}


	@Override
	public void getUploadedDataDetailsByAccountIdAndProcessingStatus(Integer accountId, Integer processingStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProcessingStatusParsing(UploadedDataDetails uploadedDataDetails) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			populateUploadedDataDetailsForUpdate(uploadedDataDetails);
			uploadedDataDetails.setProcessingStatus(ParsingStatusAndConstants.PARSING);
			session.save(uploadedDataDetails);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	@Override
	public void setProcessingStatusParsed(UploadedDataDetails uploadedDataDetails) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			populateUploadedDataDetailsForUpdate(uploadedDataDetails);
			uploadedDataDetails.setProcessingStatus(ParsingStatusAndConstants.PARSED);
			session.save(uploadedDataDetails);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	private void populateUploadedDataDetailsForUpdate(UploadedDataDetails dataDetails) {
		dataDetails.setUpdatedDate(new Date());
		dataDetails.setUpdatedUser(ParsingStatusAndConstants.SYSTEM);
		dataDetails.setUpdatedUser(ParsingStatusAndConstants.SYSTEM);
	}

	private void populateUploadedDataDetailsFordelete(UploadedDataDetails dataDetails) {
		dataDetails.setUpdatedDate(new Date());
		dataDetails.setUpdatedUser(ParsingStatusAndConstants.SYSTEM);
		dataDetails.setIsDeleted(ParsingStatusAndConstants.TRUE);
	}

	private static void populateUploadedDataDetails(UploadedDataDetails dataDetails) {
		dataDetails.setCreatedDate(new Date());
		dataDetails.setCreatedUser(ParsingStatusAndConstants.SYSTEM);
		dataDetails.setIsDeleted(ParsingStatusAndConstants.FALSE);
	}


//	@Override
//	public void saveUploadedDataDetailsByCriteria(Integer accountId, UploadedDataDetails uploadedDataDetails) {
//		Transaction transaction = null;
//		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
//			transaction = session.beginTransaction();
////			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//			uploadedDataDetails.getAccount().setAccountId(accountId);
//			populateUploadedDataDetails(uploadedDataDetails);
//			uploadedDataDetails.setProcessingStatus(ParsingStatusAndConstants.PARSING);
//			session.save(uploadedDataDetails);
//			transaction.commit();
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		}
//		
//	}


}
