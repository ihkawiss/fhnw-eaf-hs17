package bank;

import java.util.List;

public interface AccountService {
	void insertAccount(Account acc);
	List<Account> getAccounts(String name);
}
