package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.TixingxiaoxiDao;
import com.entity.TixingxiaoxiEntity;
import com.service.TixingxiaoxiService;
import com.entity.vo.TixingxiaoxiVO;
import com.entity.view.TixingxiaoxiView;

@Service("tixingxiaoxiService")
public class TixingxiaoxiServiceImpl extends ServiceImpl<TixingxiaoxiDao, TixingxiaoxiEntity> implements TixingxiaoxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TixingxiaoxiEntity> page = this.selectPage(
                new Query<TixingxiaoxiEntity>(params).getPage(),
                new EntityWrapper<TixingxiaoxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TixingxiaoxiEntity> wrapper) {
		  Page<TixingxiaoxiView> page =new Query<TixingxiaoxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<TixingxiaoxiVO> selectListVO(Wrapper<TixingxiaoxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public TixingxiaoxiVO selectVO(Wrapper<TixingxiaoxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<TixingxiaoxiView> selectListView(Wrapper<TixingxiaoxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TixingxiaoxiView selectView(Wrapper<TixingxiaoxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
