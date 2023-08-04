package org.worldfinder.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldfinder.domain.*;
import org.worldfinder.mapper.MainMapper;

import java.util.List;
import java.util.Map;

@Log4j
@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private MainMapper mapper;

    @Override
    public List<CountryClassVO> readCountry() {
        return mapper.readCountry();
    }

    @Override
    public int writeRequest(RequestVO vo) {
        return mapper.writeRequest(vo);
    }

    @Override
    public List<ReportVO> readReport(String category , Criteria cri) {
        if (category.equalsIgnoreCase("comment")){
            return mapper.readCommentReport(cri);
        } else {
            return mapper.readUserReport(cri);
        }
    }

    @Override
    public List<RequestVO> readRequest(Criteria cri) {
        return mapper.readRequest(cri);
    }

    @Override
    public List<CountryClassVO> readContinent() {
        return mapper.readContinent();
    }

    @Override
    public List<Map<String,String>> countryList(String details_continet) {
        return mapper.countryList(details_continet);
    }

    @Override
    public int writeCountry(CountryVO vo) {
        return mapper.writeCountry(vo);
    }

    @Override
    public CountryVO readCountryPage(String country) {
        return mapper.readCountryPage(country);
    }

    @Override
    public List<Map<String, String>> readfilter(String filterValue, String category) {

        return mapper.readfilter(filterValue, category);
    }

    @Override
    public int getTotalCount() {
        return mapper.getTotalCount();
    }

    // 나라 페이지 업데이트
    @Override
    public int countryModify(CountryVO vo){
        return mapper.countryModify(vo);
    }

    @Override
    public List<Map<String,String>> clearCountList() {
        return mapper.clearCountList();
    }

    // 나라게시글 삭제
    @Override
    public int deleteCountry(String country) {
        return mapper.deleteCountry(country);
    }
}
