package com.ssafy.tnt.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ssafy.tnt.db.entity.News;
import com.ssafy.tnt.db.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsRepository newsRepository;

	int size = 13; // 한 페이지에 제공할 개수
	
	@Override
	public Page<News> findByCategory(String category, int page) {
		PageRequest pageRequest = PageRequest.of(page, this.size);
		return newsRepository.findByCategoryContainingOrderByDateDesc(category, pageRequest);
	}
	@Override
	public Page<News> findByTitle(String title, int page) {
		PageRequest pageRequest = PageRequest.of(page, this.size);
		return newsRepository.findByTitleContainingOrderByDateDesc(title, pageRequest);
	}
	@Override
	public Page<News> findByContent(String content, int page) {
		PageRequest pageRequest = PageRequest.of(page, this.size);
		return newsRepository.findByContentContainingOrderByDateDesc(content, pageRequest);
	}
	@Override
	public Page<News> findByCompany(String company, int page) {
		PageRequest pageRequest = PageRequest.of(page, this.size);
		return newsRepository.findByCompanyContainingOrderByDateDesc(company, pageRequest);
	}
	@Override
	public List<Map<String, Object>> findCategoryCount() {
		return newsRepository.findCategoryCount();
	}
}
