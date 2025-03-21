package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.XinxiDao;
import com.entity.XinxiEntity;
import com.entity.view.XinxiView;
import com.entity.vo.XinxiVO;
import com.service.XinxiService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("xinxiService")
public class XinxiServiceImpl extends ServiceImpl<XinxiDao, XinxiEntity> implements XinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XinxiEntity> page = this.selectPage(
                new Query<XinxiEntity>(params).getPage(),
                new EntityWrapper<XinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XinxiEntity> wrapper) {
		  Page<XinxiView> page =new Query<XinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<XinxiVO> selectListVO(Wrapper<XinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XinxiVO selectVO(Wrapper<XinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XinxiView> selectListView(Wrapper<XinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XinxiView selectView(Wrapper<XinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
