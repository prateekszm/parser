package parser.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import parser.config.HibernateUtils;
import parser.dao.AccountRepo;
import parser.entity.Account;

public class AccountRepoImpl implements AccountRepo {
	private static final String SYSTEM = "system";
	private static final Boolean FALSE = false;
	private static final Boolean TRUE = true;

	public Account saveAccount(Account account) {
		Transaction transaction = null;
		Account savedAccount = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			populateAccount(account);
			transaction = session.beginTransaction();
			Integer id = (Integer) session.save(account);
			savedAccount = session.get(Account.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return savedAccount;
	}

	@Override
	public List<Account> saveAccounts(List<Account> accountList) {
		Transaction transaction = null;
		ArrayList<Account> savedAccountList = new ArrayList<Account>();
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			for (Account account : accountList) {
				populateAccount(account);
				Integer id = (Integer) session.save(account);
				savedAccountList.add(session.get(Account.class, id));
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return savedAccountList;
	}

	@Override
	public Account updateAccount(Account account) {
		Transaction transaction = null;
		Account updatedAccount = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			populateAccountForUpdate(account);
			transaction = session.beginTransaction();
			session.update(account);
			updatedAccount = session.get(Account.class, account.getAccountId());
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return updatedAccount;

	}

	@Override
	public List<Account> updateAccounts(List<Account> accountList) {
		Transaction transaction = null;
		ArrayList<Account> updatedAccountList = new ArrayList<Account>();
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			for (Account account : accountList) {
				populateAccountForUpdate(account);
				session.update(account);
				updatedAccountList.add(session.get(Account.class, account.getAccountId()));
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return updatedAccountList;
	}

	@Override
	public Account getAccount(Integer id) {
		Account account = null;
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			account = session.get(Account.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public List<Account> getAccounts() {
		List<Account> accountList = null;
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
			Root<Account> root = criteriaQuery.from(Account.class);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("isDeleted"), 0));
			accountList = session.createQuery(criteriaQuery).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return accountList;
	}

	@Override
	public Boolean deleteAccount(Account account) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			populateAccountFordelete(account);
			transaction = session.beginTransaction();
			session.update(account);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public Boolean deleteAccounts(List<Account> accountList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAccountVoid(Account account) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			populateAccount(account);
			transaction = session.beginTransaction();
			session.save(account);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	@Override
	public void saveAccountsVoid(List<Account> accountList) {
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			for (Account account : accountList) {
				populateAccount(account);
				session.save(account);
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void updateAccountVoid(Account account) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccountsVoid(List<Account> accountList) {
		// TODO Auto-generated method stub

	}

	private static void populateAccount(Account account) {
		account.setCreatedDate(new Date());
		account.setCreatedUser(SYSTEM);
		account.setIsDeleted(FALSE);
	}

	private void populateAccountForUpdate(Account account) {
		account.setUpdatedDate(new Date());
		account.setUpdatedUser(SYSTEM);
	}

	private void populateAccountFordelete(Account account) {
		account.setUpdatedDate(new Date());
		account.setUpdatedUser(SYSTEM);
		account.setIsDeleted(TRUE);
	}

}
