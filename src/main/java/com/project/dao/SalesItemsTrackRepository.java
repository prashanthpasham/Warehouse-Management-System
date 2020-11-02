package com.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.SalesItemsTrack;

@Repository
public interface SalesItemsTrackRepository extends CrudRepository<SalesItemsTrack, Integer> {

	public List<SalesItemsTrack> findByItemsSalesItemsId(Integer salesItemsId);

}
