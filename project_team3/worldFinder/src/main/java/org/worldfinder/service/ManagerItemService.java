package org.worldfinder.service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.HotelDetailVO;
import org.worldfinder.domain.ItemFilterConVO;
import org.worldfinder.domain.ItemFilterVO;
import org.worldfinder.domain.ItemVO;

public interface ManagerItemService {
	
	public void registerItem(ItemVO ivo);
	public void updateItem(ItemVO ivo);
	public void removeItem(int item_Idx);

	
	//날짜 선택 안했을 때 리스트 검색
	public List<ItemVO> getListwithFilter(ItemFilterVO itemFiltervo);
	public int countApplyFilter(ItemFilterVO itemFiltervo);
	
	//날짜 선택 했을 때 리스트 검색
	public List<ItemVO> getListwithFilterDate(ItemFilterVO itemFiltervo) throws ParseException;
	public int getCountwithFilterDate(ItemFilterVO itemFiltervo) throws ParseException;
	
	//아이템 세부정보 가져오기
	public ItemVO getItemDetail(ItemFilterConVO icvo) throws ParseException;
	public List<HotelDetailVO> getHotelDetail(ItemFilterConVO icvo);
	
	public List<String> getNoDate(int hotelIdx) throws ParseException;
	
}
