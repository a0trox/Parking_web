package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TixingxiaoxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.TixingxiaoxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.TixingxiaoxiView;


/**
 * 提醒消息
 *
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
public interface TixingxiaoxiService extends IService<TixingxiaoxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TixingxiaoxiVO> selectListVO(Wrapper<TixingxiaoxiEntity> wrapper);
   	
   	TixingxiaoxiVO selectVO(@Param("ew") Wrapper<TixingxiaoxiEntity> wrapper);
   	
   	List<TixingxiaoxiView> selectListView(Wrapper<TixingxiaoxiEntity> wrapper);
   	
   	TixingxiaoxiView selectView(@Param("ew") Wrapper<TixingxiaoxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TixingxiaoxiEntity> wrapper);

   	

}

