package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TingfangxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.TingfangxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.TingfangxinxiView;


/**
 * 停放信息
 *
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
public interface TingfangxinxiService extends IService<TingfangxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TingfangxinxiVO> selectListVO(Wrapper<TingfangxinxiEntity> wrapper);
   	
   	TingfangxinxiVO selectVO(@Param("ew") Wrapper<TingfangxinxiEntity> wrapper);
   	
   	List<TingfangxinxiView> selectListView(Wrapper<TingfangxinxiEntity> wrapper);
   	
   	TingfangxinxiView selectView(@Param("ew") Wrapper<TingfangxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TingfangxinxiEntity> wrapper);

   	

}

