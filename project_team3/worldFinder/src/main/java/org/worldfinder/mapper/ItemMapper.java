package org.worldfinder.mapper;

import java.util.List;

import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.HotelDetailVO;
import org.worldfinder.domain.ItemFilterConVO;
import org.worldfinder.domain.ItemFilterVO;
import org.worldfinder.domain.ItemVO;
import org.worldfinder.domain.UserOrderVO;


public interface ItemMapper {

	public void insertItem(ItemVO ivo);
	public void updateItem(ItemVO ivo);
	public void removeItem(int item_Idx);
	//public int countItemList();
	public List<ItemVO> getListwithFilter(ItemFilterVO itemFiltervo);
	public int countApplyFilter(ItemFilterVO itemFiltervo);
	
	public List<UserOrderVO> getUserOrder(ItemFilterVO itemFiltervo);
	
	public int countWithSpot(ItemFilterVO itemFiltervo);
	public int countWithHotel(ItemFilterVO itemFiltervo);
	
	public List<ItemVO> listWithNull(ItemFilterVO itemFiltervo);
	public List<ItemVO> listWithSpot(ItemFilterVO itemFiltervo);
	public List<ItemVO> listWithHotel(ItemFilterVO itemFiltervo);
	
	public ItemVO getItemDetail(int idx);
	public List<HotelDetailVO> getHotelDetail(ItemFilterConVO icvo);
	
	public List<UserOrderVO> getNoDate(int hotelIdx);
	

}
