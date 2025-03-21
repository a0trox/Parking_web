package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.XinxiEntity;
import com.entity.view.XinxiView;
import com.entity.vo.XinxiVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 停放信息
 * 
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
public interface XinxiDao extends BaseMapper<XinxiEntity> {
	
	List<XinxiVO> selectListVO(@Param("ew") Wrapper<XinxiEntity> wrapper);
	
	XinxiVO selectVO(@Param("ew") Wrapper<XinxiEntity> wrapper);
	
	List<XinxiView> selectListView(@Param("ew") Wrapper<XinxiEntity> wrapper);

	List<XinxiView> selectListView(Pagination page,@Param("ew") Wrapper<XinxiEntity> wrapper);

	
	XinxiView selectView(@Param("ew") Wrapper<XinxiEntity> wrapper);

	

}
