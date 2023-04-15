package parser.dao.impl;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import parser.config.HibernateUtils;
import parser.dao.FileAttachmentRepo;
import parser.entity.FileAttachment;

public class FileAttachmentRepoImpl implements FileAttachmentRepo{

	@Override
	public void saveFileAttachment(FileAttachment fielAttachement) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			session.save(fielAttachement);
			transaction.commit();
		}catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}
	

	@Override
	public void saveFileAttachment(List<FileAttachment> fileAttachmentList) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			for(int i = 0 ; i < fileAttachmentList.size();i++) {
				session.save(fileAttachmentList.get(i));
			}
			transaction.commit();
		}catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}
	

	@Override
	public void updateFileAttachment(List<FileAttachment> fileAttachmentList) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			for(int i = 0 ; i < fileAttachmentList.size();i++) {
				session.saveOrUpdate(fileAttachmentList.get(i));
			}
			transaction.commit();
		}catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}

	@Override
	public List<FileAttachment> getFileAttachment(Integer accountId, Integer uploadedDataDetailsId) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void updateFileAttachment(FileAttachment fielAttachement) {
		// TODO Auto-generated method stub
		
	}

	

}
