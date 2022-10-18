package com.genspark.ExpressEatsAPI.Repository;

import com.genspark.ExpressEatsAPI.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query
    Account findByEmail(String email);
}
