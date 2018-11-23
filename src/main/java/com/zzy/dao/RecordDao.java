package com.zzy.dao;

import com.zzy.entity.Auction_record;

import java.util.List;

public interface RecordDao {
	List<Auction_record> selectByAuc_id(int auction_id);

	void insert(int user_id, int auction_id, double auction_price);

	double getmaxPrice(int acution_id);

	void delete(int id);
}
