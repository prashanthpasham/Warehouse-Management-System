package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.SalesItemsTrack;

@Repository
public interface SalesItemsTrackRepository extends CrudRepository<SalesItemsTrack, Integer> {

}
