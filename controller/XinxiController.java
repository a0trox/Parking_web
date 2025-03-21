package com.controller;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.*;
import com.entity.view.XinxiView;
import com.service.*;
import com.utils.BaiduUtil;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 停放信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
@RestController
@RequestMapping("/xinxi")
public class XinxiController {
    @Autowired
    private XinxiService xinxiService;






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XinxiEntity xinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			xinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<XinxiEntity> ew = new EntityWrapper<XinxiEntity>();
		PageUtils page = xinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xinxi), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XinxiEntity xinxi, 
		HttpServletRequest request){
        EntityWrapper<XinxiEntity> ew = new EntityWrapper<XinxiEntity>();
		PageUtils page = xinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XinxiEntity xinxi){
       	EntityWrapper<XinxiEntity> ew = new EntityWrapper<XinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xinxi, "xinxi")); 
        return R.ok().put("data", xinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XinxiEntity xinxi){
        EntityWrapper< XinxiEntity> ew = new EntityWrapper< XinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xinxi, "xinxi")); 
		XinxiView xinxiView =  xinxiService.selectView(ew);
		return R.ok("查询停放信息成功").put("data", xinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XinxiEntity xinxi = xinxiService.selectById(id);
        return R.ok().put("data", xinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XinxiEntity xinxi = xinxiService.selectById(id);
        return R.ok().put("data", xinxi);
    }

    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private TingfangxinxiService tingfangxinxiService;
    @Autowired
    private CheweixinxiService cheweixinxiService;
    @Autowired
    private LikaixinxiService likaixinxiService;
    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TingfangxinxiEntity xinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(xinxi);
        if (xinxi.getId() != null) {
            XinxiEntity xinxiEntity = xinxiService.selectById(xinxi.getId());
            YonghuEntity yonghuzhanghao = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", xinxiEntity.getYonghuzhanghao()));
            if (yonghuzhanghao.getYonghuchepai() == null || yonghuzhanghao.getYonghuchepai().equals("")) {
                return R.error("用户未上传车牌信息！");
            }
            if (!xinxi.getTingfangpaihao().equals(yonghuzhanghao.getYonghuchepai())){
                return R.error("车牌信息不匹配！");
            }
            if (xinxiEntity.getStatus() == 0) {


                if (xinxi.getTingfangpaihao().equals(yonghuzhanghao.getYonghuchepai())) {
                    CheweixinxiEntity cheweixinxiEntity = cheweixinxiService.selectOne(new EntityWrapper<CheweixinxiEntity>().eq("cheweihao", xinxiEntity.getCheweihao()));
                    if (cheweixinxiEntity.getZhuangtai().equals("未使用")) {

                        CheweixinxiEntity cheweixinxi = new CheweixinxiEntity();
                        cheweixinxi.setId(cheweixinxiEntity.getId());
                        cheweixinxi.setZhuangtai("使用中");

                        cheweixinxiService.updateById(cheweixinxi);

                        TingfangxinxiEntity tingfangxinxi = new TingfangxinxiEntity();
                        tingfangxinxi.setCheweihao(xinxiEntity.getCheweihao());
                        tingfangxinxi.setTupian("upload/cheweixinxi_tupian8.jpg");
                        tingfangxinxi.setJiage(xinxiEntity.getJiage());
                        tingfangxinxi.setTingfangshijian(new Date());
                        tingfangxinxi.setYonghuzhanghao(xinxiEntity.getYonghuzhanghao());
                        tingfangxinxi.setYonghuxingming(xinxiEntity.getYonghuxingm());
                        tingfangxinxi.setDianhua(yonghuzhanghao.getDianhua());
                        tingfangxinxi.setTingfangpaihao(xinxi.getTingfangpaihao());
                        tingfangxinxi.setTingcheshibietupian(xinxi.getTingcheshibietupian());
                        boolean insert = tingfangxinxiService.insert(tingfangxinxi);

                        xinxiService.deleteById(xinxi.getId());
                    } else {
                        return R.error("车位使用中！");
                    }
                }
            } else if (xinxiEntity.getStatus() == 1){
                CheweixinxiEntity cheweixinxiEntity = cheweixinxiService.selectOne(new EntityWrapper<CheweixinxiEntity>().eq("cheweihao", xinxiEntity.getCheweihao()));
                if (cheweixinxiEntity.getZhuangtai().equals("使用中")) {
                    TingfangxinxiEntity tingfangxinxiEntity = tingfangxinxiService.selectOne(new EntityWrapper<TingfangxinxiEntity>().eq("cheweihao", xinxiEntity.getCheweihao()));
                    YonghuEntity yonghuEntity = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", xinxiEntity.getYonghuzhanghao()));

                    LikaixinxiEntity likaixinxi = new LikaixinxiEntity();
                    likaixinxi.setAddtime(new Date());
                    likaixinxi.setCheweihao(tingfangxinxiEntity.getCheweihao());
                    likaixinxi.setTupian(tingfangxinxiEntity.getTupian());
                    likaixinxi.setJiage(tingfangxinxiEntity.getJiage());

                    Date date1 = tingfangxinxiEntity.getTingfangshijian();
                    Date date2 = new Date();
                    long from1 = date1.getTime();
                    long to1 = date2.getTime();
                    int days = (int) ((to1 - from1) / (1000 * 60 * 60));

                    likaixinxi.setTingfangshijian(tingfangxinxiEntity.getTingfangshijian().toString());
                    likaixinxi.setLikaishijian(new Date());
                    likaixinxi.setShizhang(days);
                    likaixinxi.setZonge((double) (days * tingfangxinxiEntity.getJiage()));
                    likaixinxi.setLikaibeizhu("离开备注");
                    likaixinxi.setYonghuzhanghao(tingfangxinxiEntity.getYonghuzhanghao());
                    likaixinxi.setYonghuxingming(tingfangxinxiEntity.getYonghuxingming());
                    likaixinxi.setDianhua(tingfangxinxiEntity.getDianhua());
                    likaixinxi.setTingfangpaihao(tingfangxinxiEntity.getTingfangpaihao());
                    likaixinxi.setCrossuserid(yonghuEntity.getId());
                    likaixinxi.setCrossrefid(cheweixinxiEntity.getId());
                    likaixinxi.setIspay("未支付");
                    likaixinxiService.insert(likaixinxi);
                    xinxiService.deleteById(xinxi.getId());
                    tingfangxinxiService.deleteById(tingfangxinxiEntity.getId());

                    CheweixinxiEntity cheweixinxi = new CheweixinxiEntity();
                    cheweixinxi.setId(cheweixinxiEntity.getId());
                    cheweixinxi.setZhuangtai("未使用");

                    cheweixinxiService.updateById(cheweixinxi);
                }
            }
        }
        // xinxiService.insert(xinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XinxiEntity xinxi, HttpServletRequest request){
        xinxiService.insert(xinxi);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XinxiEntity xinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xinxi);
        xinxiService.updateById(xinxi);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,XinxiEntity xinxi, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            xinxi.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<XinxiEntity> ew = new EntityWrapper<XinxiEntity>();
        int count = xinxiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xinxi), params), params));
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
