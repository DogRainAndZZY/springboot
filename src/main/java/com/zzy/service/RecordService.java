package com.zzy.service;

import com.zzy.entity.Auction_record;

import java.util.List;

public interface RecordService {
	List<Auction_record> selectByAuc_id(int auction_id);
	
	void addRecord(Auction_record record);
	
	double getMaxPrice(int auction_id);
}
