package com.LC.LaraCulturaCommunity.service;

import com.LC.LaraCulturaCommunity.model.Article;
import com.LC.LaraCulturaCommunity.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class ArticleService {


    @Autowired
    private ArticleRepository articleRepository;


    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }



    //import all the articles from data base
    public List<Article> findAll(){
        List<Article> articles = (List<Article>) articleRepository.findAll();

        return articles;
    }



    //save into data base
    public boolean saveArticleDetails(Article article){
        boolean isSaved = false;
        Article savedArticle = articleRepository.save(article);

        if(null != savedArticle && savedArticle.getArticleId()>0) {
            isSaved = true;
        }
        return isSaved;
    }
}
