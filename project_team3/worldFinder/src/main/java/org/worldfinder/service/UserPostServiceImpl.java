package org.worldfinder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldfinder.domain.Criteria;
import org.worldfinder.domain.LikeVO;
import org.worldfinder.domain.ReportVO;
import org.worldfinder.domain.ScrapVO;
import org.worldfinder.domain.UserPostVO;
import org.worldfinder.mapper.UserPostMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserPostServiceImpl implements UserPostService {
   
   @Autowired
   private UserPostMapper mapper;
   
   @Override
   public List<UserPostVO> getList(Criteria cri, String country) {
      log.info("post list..");
  
      Map<String, String> map2 = new HashMap<String, String>();
      
      map2.put("country", country);
      
      
      Map<String, Object> map = new HashMap<String, Object>();
      
      map.put("cri", cri);
      map.put("map2", map2);
      
      
      return mapper.getAllList(map);
   }
   
   @Override
	public int getTotal() {
	   log.info("post total..");
	   return mapper.getTotalCount();
	}
   
   @Override
   public void postRegister(UserPostVO vo) {   // 유저 게시글 등록
      log.info("post register.." + vo);
      mapper.postInsert(vo);
   }
   
   @Override
   public UserPostVO postView(long up_idx) {
      log.info("post view.." + up_idx);
      return mapper.postGet(up_idx);
   }
   
   @Override
   public boolean postModify(UserPostVO vo) {
      log.info("post update.." + vo);
      return mapper.postUpdate(vo) == 1;
   }
   
   @Override
   public boolean postRemove(long up_idx) {
      log.info("post delete.." + up_idx);
      return mapper.postDelete(up_idx) == 1;
   }
   
   @Override
   public void modifyHit(UserPostVO vo) {
	  mapper.updateHit(vo);
   }

   
   // 좋아요 ============================
   @Override
   public void modifyLike(UserPostVO vo) {
	  mapper.updateLike(vo);
   }
   
   @Override
   public void like(LikeVO vo) {
	  log.info("like.." + vo);
	   
	  // 좋아요 상태 확인
	  //boolean isLiked = mapper.checkLike(vo);
	   
	  if(!mapper.checkLike(vo)) {	// 이미 좋아요 누른 경우는 무시
		  mapper.like(vo);	
	  } else {
		  mapper.dislike(vo);
	  }
	   
		/*
		 * if (isLiked) { // true: 데이터에 값 있음 & 좋아요 누름 mapper.dislike(vo); return
		 * "dislike"; } else { // false : 데이터에 값 없음 & 좋아요 안 누름 mapper.like(vo); return
		 * "like"; }
		 */
   }
   
   @Override
   public void dislike(LikeVO vo) {
	  log.info("dislike.." + vo);
	  System.out.println("dislike 실행");
	   
	  // 좋아요 상태 확인
	  //boolean isLiked = mapper.checkLike(vo);
	   
	  if (mapper.checkLike(vo)) {	// 좋아요 누른 경우만 취소
		  mapper.dislike(vo);
	  }
	   
		/*
		 * if (isLiked) { mapper.dislike(vo); // 좋아요 취소 return "dislike"; } else {
		 * return "none"; // 이미 좋아요 취소 }
		 */
   }
   
   @Override
   public boolean checkLikeStatus(long up_idx, String u_writer) {
	  LikeVO vo = new LikeVO(up_idx, u_writer);
	  return mapper.checkLike(vo);
   }
   
   
   @Override
   public int getLikeCount(long up_idx) {
	  log.info("post getLikeCount..." + up_idx);
	  return mapper.getLikeCount(up_idx);
   }
   
   
   
   // 스크랩 ============================
   @Override
   public void scrap(ScrapVO vo) {
	   log.info("scrap.....");
	   
	   if (!mapper.checkScrap(vo)) {
		   mapper.scrap(vo);
	   } else {
		   mapper.scrapCancle(vo);
	   }
   }
   
   @Override
   public void scrapCancle(ScrapVO vo) {
	   log.info("scrapCancle....");
	   
	   if (mapper.checkScrap(vo)) {
		   mapper.scrapCancle(vo);
	   }
   }
   
   @Override
   public boolean checkScrapStatus(long up_idx, String u_writer) {
	   ScrapVO vo = new ScrapVO(u_writer, up_idx);
	   return mapper.checkScrap(vo);
   }
   
   
   // 신고 ==========================
   @Override
   public int postReport(ReportVO vo) {
	   log.info("post Report....." + vo);
	   return mapper.postReport(vo);
   }
}