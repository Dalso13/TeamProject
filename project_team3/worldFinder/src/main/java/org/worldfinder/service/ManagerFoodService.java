package org.worldfinder.service;

import java.util.List;

import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.FoodPostVO;


public interface ManagerFoodService {

	public List<FoodPostVO> getFoodList();
	public void registerFood(FoodPostVO fvo);
	public void updateFood(FoodPostVO fvo);
	public void removeFood(int fp_Idx);
	public int countFoodList();
	public List<FoodPostVO> getListWithPaging(Criteria cri);
}
