package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.StockRecieptSkusTrack;
@Repository
public interface StockRecieptSkusTrackRepository extends CrudRepository<StockRecieptSkusTrack, Integer> {

}
