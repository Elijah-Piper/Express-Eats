package com.genspark.ExpressEatsAPI.Service;

import com.genspark.ExpressEatsAPI.Entity.Account;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface AccountService {

    Account create(Account account);

    List<Account> getAll();

    Account getById(Long id);

    Account getByEmail(String email) throws UsernameNotFoundException;

    String deleteById(Long id);

    Account update(Long id, Account account);
}
