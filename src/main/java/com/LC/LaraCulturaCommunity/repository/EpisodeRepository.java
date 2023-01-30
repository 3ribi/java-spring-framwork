package com.LC.LaraCulturaCommunity.repository;


import com.LC.LaraCulturaCommunity.model.Episode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EpisodeRepository extends CrudRepository<Episode, Integer>  {
    Episode findByIdEp(Integer id);
}
