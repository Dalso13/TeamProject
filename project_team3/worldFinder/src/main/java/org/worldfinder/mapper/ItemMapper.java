package org.worldfinder.mapper;

import java.util.List;

import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.ItemVO;


public interface ItemMapper {

	public void insertItem(ItemVO ivo);
	public List<ItemVO> getItemList();
	public void updateItem(ItemVO ivo);
	public void removeItem(int item_Idx);
	public int countItemList();
	public List<ItemVO> getListwithPaging(Criteria cri);
}
