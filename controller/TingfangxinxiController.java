package com.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
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

import com.entity.TingfangxinxiEntity;
import com.entity.view.TingfangxinxiView;

import com.service.TingfangxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import java.io.File;
import com.utils.BaiduUtil;
import org.springframework.util.ResourceUtils;
import java.io.FileNotFoundException;

/**
 * 停放信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
@RestController
@RequestMapping("/tingfangxinxi")
public class TingfangxinxiController {
    @Autowired
    private TingfangxinxiService tingfangxinxiService;






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TingfangxinxiEntity tingfangxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			tingfangxinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<TingfangxinxiEntity> ew = new EntityWrapper<TingfangxinxiEntity>();
		PageUtils page = tingfangxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tingfangxinxi), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TingfangxinxiEntity tingfangxinxi, 
		HttpServletRequest request){
        EntityWrapper<TingfangxinxiEntity> ew = new EntityWrapper<TingfangxinxiEntity>();
		PageUtils page = tingfangxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tingfangxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TingfangxinxiEntity tingfangxinxi){
       	EntityWrapper<TingfangxinxiEntity> ew = new EntityWrapper<TingfangxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tingfangxinxi, "tingfangxinxi")); 
        return R.ok().put("data", tingfangxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TingfangxinxiEntity tingfangxinxi){
        EntityWrapper< TingfangxinxiEntity> ew = new EntityWrapper< TingfangxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tingfangxinxi, "tingfangxinxi")); 
		TingfangxinxiView tingfangxinxiView =  tingfangxinxiService.selectView(ew);
		return R.ok("查询停放信息成功").put("data", tingfangxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TingfangxinxiEntity tingfangxinxi = tingfangxinxiService.selectById(id);
        return R.ok().put("data", tingfangxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TingfangxinxiEntity tingfangxinxi = tingfangxinxiService.selectById(id);
        return R.ok().put("data", tingfangxinxi);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TingfangxinxiEntity tingfangxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(tingfangxinxi);
        tingfangxinxiService.insert(tingfangxinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TingfangxinxiEntity tingfangxinxi, HttpServletRequest request){
        tingfangxinxiService.insert(tingfangxinxi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TingfangxinxiEntity tingfangxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tingfangxinxi);
        tingfangxinxiService.updateById(tingfangxinxi);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tingfangxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,TingfangxinxiEntity tingfangxinxi, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            tingfangxinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<TingfangxinxiEntity> ew = new EntityWrapper<TingfangxinxiEntity>();
        int count = tingfangxinxiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tingfangxinxi), params), params));
        return R.ok().put("data", count);
    }

    /**
     * 文字识别
     */
    @RequestMapping("/baidu/ocr")
    @IgnoreAuth
    public R baiduOcr(@RequestParam("fileName") String fileName,HttpServletRequest request) {
        String ss = "";
        try {
            String decode = URLDecoder.decode(ResourceUtils.getURL("classpath:static").getPath(), "utf-8");
            File path = new File(decode);
            if(!path.exists()) {
                path = new File("");
            }
            File upload = new File(path.getAbsolutePath(),"/upload/");
            if(!upload.exists()) {
                upload.mkdirs();
            }
            File file = new File(upload.getAbsolutePath()+"/"+fileName);
            if(file.exists()){
                ss = BaiduUtil.generalString(file.getAbsolutePath(), false);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return R.ok().put("data", ss);
    }
}
