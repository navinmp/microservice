package com.telstra.codechallenge.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.model.Account;

import com.telstra.codechallenge.service.AccountService;
@RestController
public class AccountController {

	private AccountService accountService;


	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(path = "/oldest/accounts", method = RequestMethod.GET)
	public ArrayList<Account> getHottestRepositories(@RequestParam int count) throws UnsupportedEncodingException
	{
		return accountService.getOldestAccount(count);
	}

}
