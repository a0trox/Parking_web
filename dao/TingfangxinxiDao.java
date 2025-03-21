package com.dao;

import com.entity.TingfangxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.TingfangxinxiVO;
import com.entity.view.TingfangxinxiView;


/**
 * 停放信息
 * 
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
public interface TingfangxinxiDao extends BaseMapper<TingfangxinxiEntity> {
	
	List<TingfangxinxiVO> selectListVO(@Param("ew") Wrapper<TingfangxinxiEntity> wrapper);
	
	TingfangxinxiVO selectVO(@Param("ew") Wrapper<TingfangxinxiEntity> wrapper);
	
	List<TingfangxinxiView> selectListView(@Param("ew") Wrapper<TingfangxinxiEntity> wrapper);

	List<TingfangxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<TingfangxinxiEntity> wrapper);

	
	TingfangxinxiView selectView(@Param("ew") Wrapper<TingfangxinxiEntity> wrapper);
	

}
