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


import com.dao.TingfangxinxiDao;
import com.entity.TingfangxinxiEntity;
import com.service.TingfangxinxiService;
import com.entity.vo.TingfangxinxiVO;
import com.entity.view.TingfangxinxiView;

@Service("tingfangxinxiService")
public class TingfangxinxiServiceImpl extends ServiceImpl<TingfangxinxiDao, TingfangxinxiEntity> implements TingfangxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TingfangxinxiEntity> page = this.selectPage(
                new Query<TingfangxinxiEntity>(params).getPage(),
                new EntityWrapper<TingfangxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TingfangxinxiEntity> wrapper) {
		  Page<TingfangxinxiView> page =new Query<TingfangxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<TingfangxinxiVO> selectListVO(Wrapper<TingfangxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public TingfangxinxiVO selectVO(Wrapper<TingfangxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<TingfangxinxiView> selectListView(Wrapper<TingfangxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TingfangxinxiView selectView(Wrapper<TingfangxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
