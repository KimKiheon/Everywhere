package com.ssafy.enjoytrip.trip.model.service;

import com.ssafy.enjoytrip.trip.model.dao.TripDao;
import com.ssafy.enjoytrip.trip.model.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TripServiceImp implements TripService {
    private final TripDao tripDao;

    @Override
    public JSONObject getAttractionList(SearchRequest request) {
        try {
            List<AttractionInfo> attractionList = tripDao.getAttractionList(request);
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (AttractionInfo attractionDto : attractionList) {
                JSONObject dto = new JSONObject();
                dto.put("contentId", attractionDto.getContentId());
                dto.put("title", attractionDto.getTitle());
                dto.put("imgPath", attractionDto.getFirstImage());
                dto.put("address1", attractionDto.getAddress1());
                dto.put("address2", attractionDto.getAddress2());
                dto.put("zipcode", attractionDto.getZipcode());
                dto.put("tel", attractionDto.getTel());
                dto.put("latitude", attractionDto.getLatitude());
                dto.put("longitude", attractionDto.getLongitude());
                dto.put("sidoCode", attractionDto.getSidoCode());
                dto.put("gugunCode", attractionDto.getGugunCode());
                dto.put("overview", attractionDto.getOverview());
                jsonArray.add(dto);
            }

            jsonObject.put("attractionList", jsonArray);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("여행지 리스트 가져오기 실패");
        }
    }

    @Override
    public JSONObject getGugunList(int sidoCode) {
        try {
            List<Gugun> gugunList = tripDao.getGugunList(sidoCode);
            JSONObject jsonObjectGugun = new JSONObject();
            JSONArray jsonArrayGugun = new JSONArray();
            for (Gugun gugunDto : gugunList) {
                JSONObject dto = new JSONObject();
                dto.put("gugunCode", gugunDto.getGugunCode());
                dto.put("gugunName", gugunDto.getGugunName());
                jsonArrayGugun.add(dto);
            }
            jsonObjectGugun.put("gugunList", jsonArrayGugun);
            return jsonObjectGugun;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("구/군 리스트 가져오기 실패");
        }
    }

    @Override
    public List<Sido> getSidoList() {
        try {
            return tripDao.getSidoList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("시/도 리스트 가져오기 실패");
        }
    }

    @Override
    public JSONObject getDetailJSON(int contentId) {
        try {
            AttractionInfo find = tripDao.getDetailInfo(contentId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", find.getTitle());
            jsonObject.put("address1", find.getAddress1());
            jsonObject.put("address2", find.getAddress2());
            jsonObject.put("imgPath", find.getFirstImage());
            jsonObject.put("zipcode", find.getZipcode());
            jsonObject.put("tel", find.getTel());
            jsonObject.put("overview", find.getOverview());

            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("attraction info 가져오기 실패");
        }
    }

    public AttractionInfo getDetail(int contentId) {
        try {
            return tripDao.getDetailInfo(contentId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("attraction info 가져오기 실패");
        }
    }

    @Override
    public List<HotPlaceResponse> getHotPlaces() {
        try {
            return tripDao.getHotPlaces();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripException("핫플레이스 가져오기 실패");
        }
    }

    @Override
    public void hotRegist(HotPlaceRequest request) {
        try {
            if (tripDao.hotSearch(request.getContentId()) == null) {//핫 플레이스에 등록이 되지 않은 관광지라면
                tripDao.hotRegist(request.getContentId()); // hotplace 등록 및 추천수 +1, 추천자 목록에 user 등록
                tripDao.addRecommendedUser(request);
                return;
            }
            //핫 플레이스에 이미 등록이 된 관광지라면
            if (tripDao.checkRecommend(request) > 0) { //user가 이미 추천을 누른 관광지라면
                tripDao.hotUpdateMinus(request.getContentId()); //추천수 -1, 추천자 목록에서 삭제
                tripDao.deleteRecommendUser(request);
                if (tripDao.checkRecommendCount(request.getContentId()) == 0) {
                    tripDao.deleteHotPlace(request.getContentId());
                }
            } else {
                tripDao.hotUpdatePlus(request.getContentId()); //user가 아직 누르지 않았다면
                tripDao.addRecommendedUser(request); //추천수 +1, 추천자 목록에 등록
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("핫플레이스 등록 실패");
        }
    }
    
    @Override
    public int checkRecommend(HotPlaceRequest request) {
        try {
            return tripDao.checkRecommend(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("추천 확인 실패");
        }
    }

    @Override
    public AddressResponse getAddress(int sidoCode, int gugunCode) {
        try {
            return tripDao.getAddress(sidoCode, gugunCode);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripException("주소 가져오기 실패");
        }
    }

    @Override
    public List<AttractionInfo> recommendAttractionList(int sidoCode, int gugunCode) {
        try {
            return tripDao.recommendAttractionList(sidoCode, gugunCode);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripException("추천리스트 조회 실패");
        }
    }

    public void addPlan(TripPlan request) {
        try {
            tripDao.addPlan(request);
            int planId = tripDao.getPlanId();
            List<TripPlanListDto> list = request.getPlanList();
            for (int i = 0; i < list.size(); i++) {
                tripDao.addPlanList(new TripPlanListDto(planId, list.get(i).getContentId(), i + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            throw new TripException("계획 추가 실패");
        }
    }

    @Override
    public void deletePlan(int planId) {
        try {
            tripDao.deletePlan(planId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("계획 삭제 실패");
        }
    }

    @Override
    public TripPlan getPlan(int planId) {
        try {
            TripPlan tp = new TripPlan(tripDao.getPlan(planId), tripDao.getPlanListByPlanId(planId));
            log.debug("{}",tp.getTitle());
            return new TripPlan(tripDao.getPlan(planId), tripDao.getPlanListByPlanId(planId));
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("Plan 불러오기 실패");
        }
    }

    @Override
    public List<TripPlanResponse> getPlanAll(String userId) {
        try {
            return tripDao.getPlanAll(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("Plan 전체 가져오기 실패");
        }
    }

    @Override
    public List<AttractionInfo> getPlanAttractionList(int planId) {
        try {
            List<Integer> contentIdList = tripDao.getPlanAttractionList(planId);
            return contentIdList.stream()
                    .map(id -> {
                        try {
                            return tripDao.getDetailInfo(id);
                        } catch (SQLException e) {
                            throw new TripException("attraction List 조회중 오류 발생");
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new TripException("Plan 관광지 리스트 가져오기 실패");
        }
    }

}
