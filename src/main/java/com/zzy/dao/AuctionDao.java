package com.zzy.dao;

import com.zzy.entity.Auction;

import java.util.List;

public interface AuctionDao {
	List<Auction> selectAll(Auction auction);
	
	Auction selectOne(int id);
	
	void delete(int id);
	
	void insert(Auction auction);
	
	void update(Auction auction);
}
