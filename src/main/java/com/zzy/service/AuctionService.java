package com.zzy.service;

import com.zzy.entity.Auction;

import java.util.List;

public interface AuctionService {
	List<Auction> getAll(Auction auction);
	
	Auction getOne(int auc_id);
	
	void delete(int id);
	
	void insert(Auction auction);
	
	void update(Auction auction);
}
