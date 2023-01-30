package com.LC.LaraCulturaCommunity.service;



import com.LC.LaraCulturaCommunity.model.Episode;

import com.LC.LaraCulturaCommunity.repository.EpisodeRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;




import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;

import java.util.List;




@Service
public class EpisodeService {


    @Autowired
    private EpisodeRepository episodeRepository;


    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }


    public Episode findById(Integer id){
        Episode episodes =episodeRepository.findByIdEp(id);
        return episodes;
    }
    //import all the articles from data base
    public List<Episode> findAll(){
        List<Episode> episodes = (List<Episode>) episodeRepository.findAll();

        return episodes;
    }
    public BufferedImage toImage(byte[] imageInByteArray) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageInByteArray);
        return ImageIO.read(bais);
    }



    // transform image to byte to save it in the database
    public byte[] toByte(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }
      


    //save into data base
    public boolean saveArticleDetails(Episode episode){
        boolean isSaved = false;
        Episode savedEpisode = episodeRepository.save(episode);
        System.out.println(savedEpisode);

        return isSaved;
    }
}

