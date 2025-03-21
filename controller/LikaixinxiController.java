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

import com.entity.LikaixinxiEntity;
import com.entity.view.LikaixinxiView;

import com.service.LikaixinxiService;
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
 * 离开信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
@RestController
@RequestMapping("/likaixinxi")
public class LikaixinxiController {
    @Autowired
    private LikaixinxiService likaixinxiService;

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,LikaixinxiEntity likaixinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			likaixinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<LikaixinxiEntity> ew = new EntityWrapper<LikaixinxiEntity>();

		PageUtils page = likaixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, likaixinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,LikaixinxiEntity likaixinxi, 
		HttpServletRequest request){
        EntityWrapper<LikaixinxiEntity> ew = new EntityWrapper<LikaixinxiEntity>();
		PageUtils page = likaixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, likaixinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( LikaixinxiEntity likaixinxi){
       	EntityWrapper<LikaixinxiEntity> ew = new EntityWrapper<LikaixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( likaixinxi, "likaixinxi")); 
        return R.ok().put("data", likaixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LikaixinxiEntity likaixinxi){
        EntityWrapper< LikaixinxiEntity> ew = new EntityWrapper< LikaixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( likaixinxi, "likaixinxi")); 
		LikaixinxiView likaixinxiView =  likaixinxiService.selectView(ew);
		return R.ok("查询离开信息成功").put("data", likaixinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        LikaixinxiEntity likaixinxi = likaixinxiService.selectById(id);
        return R.ok().put("data", likaixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        LikaixinxiEntity likaixinxi = likaixinxiService.selectById(id);
        return R.ok().put("data", likaixinxi);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LikaixinxiEntity likaixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(likaixinxi);
        likaixinxiService.insert(likaixinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LikaixinxiEntity likaixinxi, HttpServletRequest request){
        likaixinxiService.insert(likaixinxi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LikaixinxiEntity likaixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(likaixinxi);
        likaixinxiService.updateById(likaixinxi);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        likaixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * （按值统计）
     */
//    @RequestMapping("/value/{xColumnName}/{yColumnName}")
//    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("xColumn", xColumnName);
//        params.put("yColumn", yColumnName);
//        EntityWrapper<LikaixinxiEntity> ew = new EntityWrapper<LikaixinxiEntity>();
//		String tableName = request.getSession().getAttribute("tableName").toString();
//		if(tableName.equals("yonghu")) {
//            ew.eq("yonghuzhanghao", (String)request.getSession().getAttribute("username"));
//		}
//        List<Map<String, Object>> result = likaixinxiService.selectValue(params, ew);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        for(Map<String, Object> m : result) {
//            for(String k : m.keySet()) {
//                if(m.get(k) instanceof Date) {
//                    m.put(k, sdf.format((Date)m.get(k)));
//                }
//            }
//        }
//        return R.ok().put("data", result);
//    }

    /**
     * （按值统计(多)）
     */
//    @RequestMapping("/valueMul/{xColumnName}")
//    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
//        String[] yColumnNames = yColumnNameMul.split(",");
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("xColumn", xColumnName);
//        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        EntityWrapper<LikaixinxiEntity> ew = new EntityWrapper<LikaixinxiEntity>();
//        String tableName = request.getSession().getAttribute("tableName").toString();
//        if(tableName.equals("yonghu")) {
//            ew.eq("yonghuzhanghao", (String)request.getSession().getAttribute("username"));
//        }
//        for(int i=0;i<yColumnNames.length;i++) {
//            params.put("yColumn", yColumnNames[i]);
//            List<Map<String, Object>> result = likaixinxiService.selectValue(params, ew);
//            for(Map<String, Object> m : result) {
//                for(String k : m.keySet()) {
//                    if(m.get(k) instanceof Date) {
//                        m.put(k, sdf.format((Date)m.get(k)));
//                    }
//                }
//            }
//            result2.add(result);
//        }
//        return R.ok().put("data", result2);
//    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        EntityWrapper<LikaixinxiEntity> ew = new EntityWrapper<LikaixinxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("yonghuzhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = likaixinxiService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
//    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
//    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
//        String[] yColumnNames = yColumnNameMul.split(",");
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("xColumn", xColumnName);
//        params.put("timeStatType", timeStatType);
//        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        EntityWrapper<LikaixinxiEntity> ew = new EntityWrapper<LikaixinxiEntity>();
//        String tableName = request.getSession().getAttribute("tableName").toString();
//        if(tableName.equals("yonghu")) {
//            ew.eq("yonghuzhanghao", (String)request.getSession().getAttribute("username"));
//        }
//        for(int i=0;i<yColumnNames.length;i++) {
//            params.put("yColumn", yColumnNames[i]);
//            List<Map<String, Object>> result = likaixinxiService.selectTimeStatValue(params, ew);
//            for(Map<String, Object> m : result) {
//                for(String k : m.keySet()) {
//                    if(m.get(k) instanceof Date) {
//                        m.put(k, sdf.format((Date)m.get(k)));
//                    }
//                }
//            }
//            result2.add(result);
//        }
//        return R.ok().put("data", result2);
//    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        EntityWrapper<LikaixinxiEntity> ew = new EntityWrapper<LikaixinxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("yonghuzhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = likaixinxiService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,LikaixinxiEntity likaixinxi, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            likaixinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<LikaixinxiEntity> ew = new EntityWrapper<LikaixinxiEntity>();
        int count = likaixinxiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, likaixinxi), params), params));
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
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return R.ok().put("data", ss);
    }
}