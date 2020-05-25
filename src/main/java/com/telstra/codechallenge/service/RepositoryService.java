package com.telstra.codechallenge.service;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.telstra.codechallenge.model.Repository;

@Service
public class RepositoryService {

	@Value("${git.base.url}")
	private String gitBaseUrl;
	private RestTemplate restTemplate;
	public RepositoryService(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	/**
	 * Returns an array of hottest repositories. Taken from https://api.github.com/search/repositories?.
	 *
	 * @return - a Repository array
	 */

	public ArrayList<Repository> getHottestRepositories(int count) throws UnsupportedEncodingException {
		String url=gitBaseUrl+"/search/repositories?"+"q=created&"+"sort=stars&"+"order=desc";
		String encodedURL= URLEncoder.encode(url, StandardCharsets.UTF_8.toString());	 
		JSONObject result=restTemplate.getForObject(url, JSONObject.class);
		@SuppressWarnings("unchecked")
		ArrayList<LinkedHashMap<String,String>> items= (ArrayList<LinkedHashMap<String,String>>) result.get("items");
		ArrayList<Repository> repoList=new ArrayList<>();
		for(int i=0;i<count;i++) {
			LinkedHashMap<String,String> maps=items.get(i);
			Repository repository=new Repository();
			repository.setHtml_url(maps.get("html_url"));
			repository.setLanguage(maps.get("language"));
			repository.setDescription(maps.get("description"));
			repository.setName(maps.get("name"));
			repository.setWatchers_count(String.valueOf(maps.get("watchers_count")));
			repoList.add(repository);		
		}
		return repoList;
	}
}
