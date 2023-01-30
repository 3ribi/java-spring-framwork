package com.LC.LaraCulturaCommunity.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.LC.LaraCulturaCommunity.model.Article;
import com.LC.LaraCulturaCommunity.model.Episode;
import com.LC.LaraCulturaCommunity.repository.ArticleRepository;
import com.LC.LaraCulturaCommunity.service.ArticleService;
import com.LC.LaraCulturaCommunity.service.EpisodeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import java.io.IOException;

import javax.validation.Valid;

@Slf4j
@Controller
public class EpisodeController {



    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;

    public EpisodeController(EpisodeService episodeService,ArticleService articleService) {
    
        this.episodeService =episodeService;
        this.articleService =articleService;
    }


    
    @RequestMapping("/newEpisode")
    public String formNewEpisode(Model model) {

        Iterable<Article> activities = articleService.findAll();

       
        model.addAttribute("articles", activities);
        model.addAttribute("ep", new Episode());

        return "newEpisode.html";
    }

    @PostMapping(value = "/addEpisode")
    public String saveEpisode(@Valid @ModelAttribute("ep") Episode episode, Errors errors) throws IOException{
        System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        if(errors.hasErrors()){
            log.error("Eppisode form validation failed due to : " + errors.toString());
            return "newEpisode.html";
        }
        episode.setPhoto(episode.getImage().getBytes());
        episode.setArticle(articleRepository.findByTitle(episode.getArticle().getTitle()));
        episodeService.saveArticleDetails(episode);
        return "redirect:newEpisode";
    }
    @GetMapping("/addEpisode")
    public String formNewEpisode2(Model model) {
        return formNewEpisode(model);
    }
}
