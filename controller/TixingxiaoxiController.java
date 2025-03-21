package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.TixingxiaoxiEntity;
import com.entity.view.TixingxiaoxiView;

import com.service.TixingxiaoxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 提醒消息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
@RestController
@RequestMapping("/tixingxiaoxi")
public class TixingxiaoxiController {
    @Autowired
    private TixingxiaoxiService tixingxiaoxiService;

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TixingxiaoxiEntity tixingxiaoxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			tixingxiaoxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<TixingxiaoxiEntity> ew = new EntityWrapper<TixingxiaoxiEntity>();
		PageUtils page = tixingxiaoxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tixingxiaoxi), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TixingxiaoxiEntity tixingxiaoxi, 
		HttpServletRequest request){
        EntityWrapper<TixingxiaoxiEntity> ew = new EntityWrapper<TixingxiaoxiEntity>();
		PageUtils page = tixingxiaoxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tixingxiaoxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TixingxiaoxiEntity tixingxiaoxi){
       	EntityWrapper<TixingxiaoxiEntity> ew = new EntityWrapper<TixingxiaoxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tixingxiaoxi, "tixingxiaoxi")); 
        return R.ok().put("data", tixingxiaoxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TixingxiaoxiEntity tixingxiaoxi){
        EntityWrapper< TixingxiaoxiEntity> ew = new EntityWrapper< TixingxiaoxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tixingxiaoxi, "tixingxiaoxi")); 
		TixingxiaoxiView tixingxiaoxiView =  tixingxiaoxiService.selectView(ew);
		return R.ok("查询提醒消息成功").put("data", tixingxiaoxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TixingxiaoxiEntity tixingxiaoxi = tixingxiaoxiService.selectById(id);
        return R.ok().put("data", tixingxiaoxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TixingxiaoxiEntity tixingxiaoxi = tixingxiaoxiService.selectById(id);
        return R.ok().put("data", tixingxiaoxi);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TixingxiaoxiEntity tixingxiaoxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tixingxiaoxi);
        tixingxiaoxiService.insert(tixingxiaoxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TixingxiaoxiEntity tixingxiaoxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tixingxiaoxi);
        tixingxiaoxiService.insert(tixingxiaoxi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TixingxiaoxiEntity tixingxiaoxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tixingxiaoxi);
        tixingxiaoxiService.updateById(tixingxiaoxi);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tixingxiaoxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
