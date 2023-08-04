package org.worldfinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.ItemVO;
import org.worldfinder.mapper.ItemMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ManagerItemServiceImpl implements ManagerItemService{
	
	@Autowired
	private ItemMapper mapper;
	
	@Override
	public int countItemList() {
		return mapper.countItemList();
	}
	
	@Override
	public List<ItemVO> getItemList() {
		log.info("service getItemList...");
		return mapper.getItemList();
	}
	
	@Override
	public void registerItem(ItemVO ivo) {
		log.info("service registeritem...");
		mapper.insertItem(ivo);
	}
	
	@Override
	public void removeItem(int item_Idx) {
		log.info("service removeitem...");
		mapper.removeItem(item_Idx);
	}
	
	@Override
	public void updateItem(ItemVO ivo) {
		log.info("service updateItem...");
		mapper.updateItem(ivo);
	}
	
	@Override
	public List<ItemVO> getListWithPaging(Criteria cri) {
		log.info("service getListWithPaging...");
		return mapper.getListwithPaging(cri);
	}
	
	

}
