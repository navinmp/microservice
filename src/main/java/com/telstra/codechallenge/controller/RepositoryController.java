package com.telstra.codechallenge.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.model.Repository;
import com.telstra.codechallenge.service.RepositoryService;

@RestController
public class RepositoryController {

	private RepositoryService repositoryService;

	public RepositoryController(RepositoryService repositoryService) {
		super();
		this.repositoryService = repositoryService;
	}

	@RequestMapping(path = "/hottest/repositories", method = RequestMethod.GET)
	public ArrayList<Repository> getHottestRepositories(@RequestParam int count) throws UnsupportedEncodingException
	{
		return repositoryService.getHottestRepositories(count);
	}

}
