package org.worldfinder.mapper;

import java.util.List;

import org.worldfinder.domain.CountryClassVO;
import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.ItemFilterVO;
import org.worldfinder.domain.ItemVO;


public interface ItemMapper {

	public void insertItem(ItemVO ivo);
	public List<ItemVO> getItemList();
	public void updateItem(ItemVO ivo);
	public void removeItem(int item_Idx);
	public int countItemList();
	public List<ItemVO> getListwithPaging(Criteria cri);
	public List<ItemVO> getListwithFilter(ItemFilterVO itemFiltervo);
	public int countApplyFilter(ItemFilterVO itemFiltervo);
	public List<ItemVO> selectWithHotel(ItemFilterVO itemFiltervo);
	public List<ItemVO> selectWithSpot(ItemFilterVO itemFiltervo);


	// 나라불러오기
	public CountryClassVO countryCategory(String country);
}
