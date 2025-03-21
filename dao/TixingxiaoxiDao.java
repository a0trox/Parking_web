package com.dao;

import com.entity.TixingxiaoxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.TixingxiaoxiVO;
import com.entity.view.TixingxiaoxiView;


/**
 * 提醒消息
 * 
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
public interface TixingxiaoxiDao extends BaseMapper<TixingxiaoxiEntity> {
	
	List<TixingxiaoxiVO> selectListVO(@Param("ew") Wrapper<TixingxiaoxiEntity> wrapper);
	
	TixingxiaoxiVO selectVO(@Param("ew") Wrapper<TixingxiaoxiEntity> wrapper);
	
	List<TixingxiaoxiView> selectListView(@Param("ew") Wrapper<TixingxiaoxiEntity> wrapper);

	List<TixingxiaoxiView> selectListView(Pagination page,@Param("ew") Wrapper<TixingxiaoxiEntity> wrapper);

	
	TixingxiaoxiView selectView(@Param("ew") Wrapper<TixingxiaoxiEntity> wrapper);
	

}
