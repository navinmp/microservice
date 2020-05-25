package com.telstra.codechallenge.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.telstra.codechallenge.model.Account;

@Service
public class AccountService {


	@Value("${git.base.url}")
	private String gitBaseUrl;
	private RestTemplate restTemplate;
	public AccountService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	/**
	 * Returns an array of oldest accounts. Taken from https://api.github.com/search/users?.
	 *
	 * @return - a Account array
	 */

	public ArrayList<Account> getOldestAccount(int count) throws UnsupportedEncodingException {
		String url="/search/users?"+"q=followers:0&"+"sort=joined&"+"order=asc";
		String ecodedUrl= URLEncoder.encode(url, StandardCharsets.UTF_8.toString());
		JSONObject result=restTemplate.getForObject(gitBaseUrl+url, JSONObject.class);
		@SuppressWarnings("unchecked")
		ArrayList<LinkedHashMap<String,String>> items= (ArrayList<LinkedHashMap<String,String>>) result.get("items");
		ArrayList<Account> accountList=new ArrayList<>();
		for(int i=0;i<count;i++) {
			LinkedHashMap<String,String> maps=items.get(i);
			Account account =new Account();
			account.setId(String.valueOf(maps.get("id")));
			account.setHtml_url(maps.get("html_url"));
			account.setLogin(maps.get("login"));
			accountList.add(account);
		}
		return accountList;
	}



}
