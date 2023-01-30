package com.LC.LaraCulturaCommunity.controller;


import com.LC.LaraCulturaCommunity.model.Article;
import com.LC.LaraCulturaCommunity.model.Episode;
import com.LC.LaraCulturaCommunity.repository.ArticleRepository;
import com.LC.LaraCulturaCommunity.repository.EpisodeRepository;
import com.LC.LaraCulturaCommunity.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.validation.Errors;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import java.io.IOException;

import javax.validation.Valid;




@Slf4j
@Controller
@RequestMapping(value={"/activity"})
public class ArticleController {


   
    
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private ArticleService articleService;
   

    public ArticleController(ArticleService articleService) {
    
        this.articleService =articleService;
    }


        // display all articles
    @RequestMapping(value={""})
    public String displayActivities(Model model) throws IOException {
        Iterable<Article> activities = articleService.findAll();

            // transform articles from type iterable to list
          List<Article> articles = StreamSupport
                        .stream(activities.spliterator(), false)
                        .collect(Collectors.toList());     

        model.addAttribute("articles", articles);

        return "/activities";

    }
    @RequestMapping(value={"/article_display"})
    public String modifyActivities(Model model) throws IOException {
        Iterable<Article> activities = articleService.findAll();

            // transform articles from type iterable to list
          List<Article> articles = StreamSupport
                        .stream(activities.spliterator(), false)
                        .collect(Collectors.toList());     

        model.addAttribute("articles", articles);

        return "/article_display";

    }




    @RequestMapping(value={"/{display}"})
    public String displayActivity(@PathVariable String display,Model model) throws IOException {

        Article article = articleRepository.findByTitle(display);

        model.addAttribute("art", article);
        return "activity.html";
    }



        // creat a images path of article entities
    @RequestMapping(value="/image/{display}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String display,Model model) {
        Article article = articleRepository.findByTitle(display);   
        
         // display image page
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(article.getPhoto());
    }



            //creat a image path of episode entities
    @RequestMapping(value="/photo/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPhoto(@PathVariable Integer id,Model model) {
       
        Optional<Episode> episodes = episodeRepository.findById(id);   
            // we can't display optional type to output so we us fonction .get() to episode type to display it
        Episode ep = episodes.get();


                 // display image page
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(ep.getPhoto());
    }



    @RequestMapping("/newArticle")
    public String formNewArticle(Model model) {
      
        model.addAttribute("article", new Article());
        return "newArticle.html";
    }


    @PostMapping("/addArticle")
    public String saveArticle(@Valid @ModelAttribute("article") Article article, Errors errors) throws IOException{

        if(errors.hasErrors()){
            log.error("Article form validation failed due to : " + errors.toString());
            return "newArticle.html";
        }
        // transform type image to type byte for database
        article.setPhoto(article.getImage().getBytes());

        articleService.saveArticleDetails(article);
       
        return "redirect:/activity/newArticle";
    }


    @GetMapping("/addArticle")
    public String formNewArticle2(Model model) {
        return formNewArticle(model);
    }







    
}
