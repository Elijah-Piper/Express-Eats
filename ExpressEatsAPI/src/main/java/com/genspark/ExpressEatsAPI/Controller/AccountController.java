package com.genspark.ExpressEatsAPI.Controller;

import com.genspark.ExpressEatsAPI.Entity.Account;
import com.genspark.ExpressEatsAPI.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    public List<Account> getAll() {
        return this.accountService.getAll();
    }

    @GetMapping("/id/{id}")
    public Account getById(@PathVariable Long id) {
        return this.accountService.getById(id);
    }

    @GetMapping("/email/{email}")
    public Account getByEmail(@PathVariable String email) {
        return this.accountService.getByEmail(email);
    }

    @PutMapping("/create")
    public Account create(@RequestBody Account account) {
        return this.accountService.create(account);
    }

    @PostMapping("/id/{id}/update")
    public Account update(@PathVariable Long id, @RequestBody Account account) {
        return this.accountService.update(id, account);
    }

    @DeleteMapping("/id/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        return this.accountService.deleteById(id);
    }
}
