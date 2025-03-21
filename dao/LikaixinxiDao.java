package com.dao;

import com.entity.LikaixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.LikaixinxiVO;
import com.entity.view.LikaixinxiView;


/**
 * 离开信息
 * 
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
public interface LikaixinxiDao extends BaseMapper<LikaixinxiEntity> {
	
	List<LikaixinxiVO> selectListVO(@Param("ew") Wrapper<LikaixinxiEntity> wrapper);
	
	LikaixinxiVO selectVO(@Param("ew") Wrapper<LikaixinxiEntity> wrapper);
	
	List<LikaixinxiView> selectListView(@Param("ew") Wrapper<LikaixinxiEntity> wrapper);

	List<LikaixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<LikaixinxiEntity> wrapper);

	
	LikaixinxiView selectView(@Param("ew") Wrapper<LikaixinxiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<LikaixinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<LikaixinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<LikaixinxiEntity> wrapper);



}
