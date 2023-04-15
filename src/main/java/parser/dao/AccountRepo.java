package parser.dao;

import java.util.List;

import parser.entity.Account;

public interface AccountRepo {
	public Account saveAccount(Account account);
	public List<Account> saveAccounts(List<Account> accountList);
	public Account updateAccount(Account account);
	public List<Account> updateAccounts(List<Account> accountList);
	public Account getAccount(Integer id);
	public List<Account> getAccounts();
	public Boolean deleteAccount(Account account);
	public Boolean deleteAccounts(List<Account> accountList);
	
	public void saveAccountVoid(Account account);
	public void saveAccountsVoid(List<Account> accountList);
	public void updateAccountVoid(Account account);
	public void updateAccountsVoid(List<Account> accountList);
	
	
}
