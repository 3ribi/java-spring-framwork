package com.LC.LaraCulturaCommunity.repository;


import com.LC.LaraCulturaCommunity.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    
    Article findByTitle(String title);
}