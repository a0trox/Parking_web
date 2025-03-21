package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.XinxiEntity;
import com.entity.view.XinxiView;
import com.entity.vo.XinxiVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 停放信息
 *
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
public interface XinxiService extends IService<XinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XinxiVO> selectListVO(Wrapper<XinxiEntity> wrapper);
   	
   	XinxiVO selectVO(@Param("ew") Wrapper<XinxiEntity> wrapper);
   	
   	List<XinxiView> selectListView(Wrapper<XinxiEntity> wrapper);
   	
   	XinxiView selectView(@Param("ew") Wrapper<XinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XinxiEntity> wrapper);

   	

}

