package com.genspark.ExpressEatsAPI.Security;

import com.genspark.ExpressEatsAPI.Entity.Account;
import com.genspark.ExpressEatsAPI.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmailDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Account account = accountRepository.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("No account found with email :: " + username);
        }

        UserDetails user = User.withUsername(account.getEmail()).password(account.getPassword()).authorities("USER").build();
        return user;
    }
}
