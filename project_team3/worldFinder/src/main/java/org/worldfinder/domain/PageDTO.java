package org.worldfinder.domain;

import lombok.Data;

@Data
public class PageDTO {

	private int startPage;			//시작 페이지 번호
	private int endPage;			//끝 페이지 번호
	private boolean prev, next;		//이전, 다음페이지 이동
	private int total;				//전체 게시물 수
	private  Criteria cri;			
	
	//페이지 번호를 10개씩 본다는 가정
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum() / (double)cri.getAmount())) * cri.getAmount();
		//1 페이지 : Math.ceil(0.1) * 10 = 10
		
		this.startPage = this.endPage - (cri.getAmount() - 1);
		//만약 10개씩 보여준다면 시작 번호는 무조건 끝 번호에서 9만큼 빼면 된다.
		
		int realEnd = (int)(Math.ceil((total*1.0) / cri.getAmount()));
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		//전체 데이터 수 (tatal)가 80이면 끝 번호(endPage)는 8 이 된다.
		//만약 끝 번호 (endPage)와 한 페이지 당 출력되는 데이터 수 (amount)의 곱이
		//전체 데이터 수 (total) 보다 크면 ex)11 * 10 > 80
		//다시 total을 이용해서 계산해야 한다.
		
		this.prev = this.startPage > 1;	//1보다 크면 true
		this.next = this.endPage < realEnd;	//현재 끝 번호보다 실제 끝 페이지가 더 크면 true
	}
	
}