package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.LikaixinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.LikaixinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.LikaixinxiView;


/**
 * 离开信息
 *
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
public interface LikaixinxiService extends IService<LikaixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<LikaixinxiVO> selectListVO(Wrapper<LikaixinxiEntity> wrapper);
   	
   	LikaixinxiVO selectVO(@Param("ew") Wrapper<LikaixinxiEntity> wrapper);
   	
   	List<LikaixinxiView> selectListView(Wrapper<LikaixinxiEntity> wrapper);
   	
   	LikaixinxiView selectView(@Param("ew") Wrapper<LikaixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<LikaixinxiEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<LikaixinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<LikaixinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<LikaixinxiEntity> wrapper);



}

