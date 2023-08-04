package org.worldfinder.mapper;

import java.util.List;

import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.FoodPostVO;


public interface FoodPostMapper {
	
	
	public void insertFood(FoodPostVO fvo);
	public List<FoodPostVO> getFoodList();
	public void updateFood(FoodPostVO fvo);
	public void removeFood(int fp_Idx);
	public int countItemList();
	public List<FoodPostVO> getListwithPaging(Criteria cri);

}
