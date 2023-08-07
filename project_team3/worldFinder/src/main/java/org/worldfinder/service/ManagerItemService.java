package org.worldfinder.service;

import java.util.List;

import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.ItemFilterVO;
import org.worldfinder.domain.ItemVO;

public interface ManagerItemService {
	
	public List<ItemVO> getItemList();
	public void registerItem(ItemVO ivo);
	public void updateItem(ItemVO ivo);
	public void removeItem(int item_Idx);
	public int countItemList();
	public List<ItemVO> getListWithPaging(Criteria cri);
	public List<ItemVO> getListwithFilter(ItemFilterVO itemFiltervo);
	public int countApplyFilter(ItemFilterVO itemFiltervo);
}
