package org.worldfinder.service;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.HotelDetailVO;
import org.worldfinder.domain.ItemFilterConVO;
import org.worldfinder.domain.ItemFilterVO;
import org.worldfinder.domain.ItemVO;
import org.worldfinder.domain.UserOrderVO;
import org.worldfinder.mapper.ItemMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ManagerItemServiceImpl implements ManagerItemService{
	
	@Autowired
	private ItemMapper mapper;
	
//	@Override
//	public int countItemList() {
//		return mapper.countItemList();
//	}
	
//	@Override
//	public List<ItemVO> getItemList() {
//		log.info("service getItemList...");
//		return mapper.getItemList();
//	}
	
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
	
//	@Override
//	public List<ItemVO> getListWithPaging(Criteria cri) {
//		log.info("service getListWithPaging...");
//		return mapper.getListwithPaging(cri);
//	}
	
	@Override
	public List<ItemVO> getListwithFilter(ItemFilterVO itemFiltervo) {
		log.info("service getListwithFilter...");
		System.out.println("service getListwithFilter...");
		
	
		return mapper.getListwithFilter(itemFiltervo);
	}
	
	@Override
	public int countApplyFilter(ItemFilterVO itemFiltervo) {
		log.info("service countApplyFilter...");
		return mapper.countApplyFilter(itemFiltervo);
	}
	
	//날짜 검색 기능 추가한 getList
	@Override
	public List<ItemVO> getListwithFilterDate(ItemFilterVO itemFiltervo) throws ParseException {  
		
		
		log.info("service getListwithFilterDate...");

		itemFiltervo = setFilter(itemFiltervo);   
		
		//날짜를 선택하지 않았을 때
		if(itemFiltervo.getStartDay() == null && 
				itemFiltervo.getEndDay() == null) {
			return mapper.getListwithFilter(itemFiltervo);
		}
		else {	//날짜 하나 이상을 선택하고
			
			//카테고리를 선택하지 않았을 때
			if(itemFiltervo.getItem_Category() == null || itemFiltervo.getItem_Category().equals("")) {
				return mapper.listWithNull(itemFiltervo);
			}
			//관광지 카테고리를 선택했을 때
			else if(itemFiltervo.getItem_Category().equals("spot")) {
				return mapper.listWithSpot(itemFiltervo);
			}
			//숙소 카테고리를 선택했을 때
			else {
				return mapper.listWithHotel(itemFiltervo);
			}
			
		}

	}
	
	//날짜 검색 기능 추가한 getCount (전체 게시글 수 불러오기 위함)
	@Override
	public int getCountwithFilterDate(ItemFilterVO itemFiltervo) throws ParseException {  
		
		
		log.info("service getCountwithFilterDate...");
				
		itemFiltervo = setFilter(itemFiltervo);
		
		//날짜를 선택하지 않았을 때
		if(itemFiltervo.getStartDay() == null && 
				itemFiltervo.getEndDay() == null) {
			
			return mapper.countApplyFilter(itemFiltervo);
		}
		else {	//날짜 하나 이상을 선택하고

						
			//카테고리를 선택하지 않았을 때
			if(itemFiltervo.getItem_Category() == null || itemFiltervo.getItem_Category().equals("")) {
				return (mapper.countWithSpot(itemFiltervo) + mapper.countWithHotel(itemFiltervo));
			}
			//관광지 카테고리를 선택했을 때
			else if(itemFiltervo.getItem_Category().equals("spot")) {
				return mapper.countWithSpot(itemFiltervo);
			}
			//숙소 카테고리를 선택했을 때
			else {
				return mapper.countWithHotel(itemFiltervo);
			}
			
		}

	}
	
	//날짜에 null값 들어왔을 때 set
	private ItemFilterVO setFilter(ItemFilterVO itemFiltervo) throws ParseException {
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
		
		//같은 날 선택시 호텔 검색 결과는 안나옴
		if(	itemFiltervo.getStartDay() != null &&
				itemFiltervo.getEndDay() != null &&
				itemFiltervo.getStartDay().equals(itemFiltervo.getEndDay()) &&
				(itemFiltervo.getItem_Category() == null || itemFiltervo.getItem_Category().equals(""))) {
			itemFiltervo.setItem_Category("spot");
		}
		
		if(itemFiltervo.getStartDay() != null &&
				itemFiltervo.getEndDay() == null) {
			
			itemFiltervo.setStartDayHotel(itemFiltervo.getStartDay());
			itemFiltervo.setStartDaySpot(itemFiltervo.getStartDay());
			
			String hotelEndDay = fmt.format(itemFiltervo.getStartDay());
			hotelEndDay = AddDate(hotelEndDay, 0, 0, 1);
			itemFiltervo.setEndDayHotel(Date.valueOf(hotelEndDay));
			
			String spotEndDay = fmt.format(itemFiltervo.getStartDay());
			spotEndDay = AddDate(spotEndDay, 0, 1, 0);
			itemFiltervo.setEndDaySpot(Date.valueOf(spotEndDay));	
		}
		else if(itemFiltervo.getEndDay() != null &&
				itemFiltervo.getStartDay() == null) {
			
			itemFiltervo.setEndDayHotel(itemFiltervo.getEndDay());
			itemFiltervo.setEndDaySpot(itemFiltervo.getEndDay());
			
			String hotelStartDay = fmt.format(itemFiltervo.getEndDay());
			hotelStartDay = AddDate(hotelStartDay, 0, 0, -1);
			itemFiltervo.setStartDayHotel(Date.valueOf(hotelStartDay));
			
			String spotStartDay = fmt.format(itemFiltervo.getEndDay());
			spotStartDay = AddDate(spotStartDay, 0, 0, -1);
			itemFiltervo.setStartDaySpot(Date.valueOf(spotStartDay));
			
		}
		else {
			itemFiltervo.setStartDayHotel(itemFiltervo.getStartDay());
			itemFiltervo.setStartDaySpot(itemFiltervo.getStartDay());
			
			itemFiltervo.setEndDayHotel(itemFiltervo.getEndDay());
			itemFiltervo.setEndDaySpot(itemFiltervo.getEndDay());
		}
		
		
		return itemFiltervo;
	}

	private String AddDate(String strDate, int year, int month, int day) throws ParseException {
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
        
		Calendar cal = Calendar.getInstance();
        
		java.util.Date dt = dtFormat.parse(strDate);
        
		cal.setTime(dt);
        
		cal.add(Calendar.YEAR,  year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DATE,  day);
        
		return dtFormat.format(cal.getTime());
	}
	
	@Override
	public List<HotelDetailVO> getHotelDetail(ItemFilterConVO icvo) {
		return mapper.getHotelDetail(icvo);
	}
	
	@Override
	public ItemVO getItemDetail(ItemFilterConVO icvo) throws ParseException {
		ItemVO ivo = mapper.getItemDetail(icvo.getIdx());	
		
		//날짜 선택 셋팅
		icvo = setIcvoDate(icvo);
				
		ivo.setHotel_detail_list(mapper.getHotelDetail(icvo));		
		return ivo;
	}
	
	private ItemFilterConVO setIcvoDate(ItemFilterConVO icvo) throws ParseException {
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = fmt.format(icvo.getStartDay());
		String endDay = fmt.format(icvo.getEndDay());
		
		
		if(startDay.equals("1900-01-01") && !endDay.equals("1900-01-01")) {
			System.out.println("앞날짜 없음");
			startDay = fmt.format(icvo.getEndDay());
			icvo.setStartDay(Date.valueOf(AddDate(startDay, 0, 0, -1)));
		}
		else if(endDay.equals("1900-01-01") && !startDay.equals("1900-01-01")){
			System.out.println("뒷날짜 없음");
			endDay = fmt.format(icvo.getStartDay());
			icvo.setEndDay(Date.valueOf(AddDate(endDay, 0, 0, 1)));
		}
		
		return icvo;
	}
	
	@Override
	public List<String> getNoDate(int hotelIdx) throws ParseException {
		
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		List<UserOrderVO> list = mapper.getNoDate(hotelIdx);
		System.out.println("service start");
		System.out.println("service list : " + list);
		
		List<String> result = new ArrayList<String>();
		String inStr = "", outStr = "";
		System.out.println("service start2");
		for(UserOrderVO uvo : list) {
			inStr = fmt.format(uvo.getCheck_In_Date());
			outStr = fmt.format(uvo.getCheck_Out_Date());
			
			System.out.println("service start3");
			while(true) {
				
				System.out.println("inStr : " + inStr);
				result.add(deleteZero(inStr));
				AddDate(inStr, 0, 0, 1);
				
				
				if(dateToInt(inStr) > dateToInt(outStr)) {
					break;
				}	
			}
		}
		System.out.println("service result : " + result);
		
		return result;
	}
	
	//날짜 형식을 2023-01-01 => 2023-1-1로 변경
	private String deleteZero(String dateStr) {
		
		String y = dateStr.substring(0, 4);
		String m = dateStr.substring(5, 7);
		String d = dateStr.substring(8, 10);
		
		if(isNumeric(m) && Integer.parseInt(m) < 10) {
			m.replaceAll("0", "");
		}
		if(isNumeric(d) && Integer.parseInt(d) < 10) {
			d.replaceAll("0", "");
		}
		dateStr = y + "-" + m + "-" + d;
		
		return dateStr;
	}
	
	//문자열이 숫자인지 확인
	private boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	private int dateToInt(String s) {
		
		int n;
		s.replace("-", "");
		if(isNumeric(s)) {
			n = Integer.parseInt(s);
		}
		else {
			n = 21000101;
		}
		
		return n;
	}
	
	
	
	
	
	

	

}
