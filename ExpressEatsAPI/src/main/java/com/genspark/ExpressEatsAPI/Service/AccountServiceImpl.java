package com.genspark.ExpressEatsAPI.Service;

import com.genspark.ExpressEatsAPI.Entity.Account;
import com.genspark.ExpressEatsAPI.Entity.Restaurant;
import com.genspark.ExpressEatsAPI.Repository.AccountRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Account create(Account account) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Query query = session.createQuery("from Account where email=:em");
        query.setParameter("em", account.getEmail());

        List<Account> list = query.list();

        if (! list.isEmpty()) {
            throw new RuntimeException("There is already an account registered with the email :: " + account.getEmail());
        } else {
            account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        }

        factory.close();
        session.close();

        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> getAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account getById(Long id) {
        Optional<Account> acc = this.accountRepository.findById(id);
        Account account = null;
        if (acc.isPresent()) {
            account = acc.get();
        } else {
            throw new RuntimeException("Account not found for id :: " + id);
        }

        return account;
    }

    @Override
    public Account getByEmail(String email) throws UsernameNotFoundException {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Query query = session.createQuery("from Account where email=:em");
        query.setParameter("em", email);

        Account account = (Account) query.getSingleResult();

        factory.close();
        session.close();

        return account;
    }

    @Override
    public String deleteById(Long id) {
        this.accountRepository.deleteById(id);

        Optional<Account> account = this.accountRepository.findById(id);

        if (account.isPresent()) {
            return "DELETION UNSUCCESSFUL :: Account still present";
        } else {
            return "DELETION SUCCESSFUL";
        }
    }

    @Override
    public Account update(Long id, Account account) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Optional<Account> currentOptional = this.accountRepository.findById(id);
        Account current = null;
        if (currentOptional.isPresent()) {
            current = currentOptional.get();
        } else {
            throw new RuntimeException("Restaurant not found for id :: " + id);
        }

        if (! account.getEmail().equals(current.getEmail())) {
            current.setEmail(account.getEmail());
        }
        if (account.getRestaurants() != current.getRestaurants()) {
            current.setRestaurants(account.getRestaurants());
        }
        if (account.getCart() != current.getCart()) {
            current.setCart(account.getCart());
        }

        transaction.commit();

        session.close();
        factory.close();

        return current;
    }
}
