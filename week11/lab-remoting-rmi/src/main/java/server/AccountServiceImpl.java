package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bank.Account;
import bank.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private final Map<String, List<Account>> map = new HashMap<>();

	public List<Account> getAccounts(String name) {
		return map.get(name);
	}

	public void insertAccount(Account acc) {
		String name = acc.getName();
		if (map.get(name) == null) {
			map.put(name, new ArrayList<>());
		}
		map.get(name).add(acc);
	}

}
