package com.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Date;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

import com.entity.CheweixinxiEntity;
import com.entity.XinxiEntity;
import com.entity.view.CheweixinxiView;
import com.service.CheweixinxiService;
import com.service.XinxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.YonghuEntity;
import com.entity.view.YonghuView;

import com.service.YonghuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 用户
 * 后端接口
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
@RestController
@RequestMapping("/yonghu")
public class YonghuController {
    @Autowired
    private YonghuService yonghuService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private XinxiService xinxiService;
    @Autowired
    private CheweixinxiService cheweixinxiService;

	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		String token = tokenService.generateToken(u.getId(), username,"yonghu",  "用户" );
		return R.ok().put("token", token);
	}

	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody YonghuEntity yonghu){
    	//ValidatorUtils.validateEntity(yonghu);
    	YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		yonghu.setId(uId);
        yonghuService.insert(yonghu);
        return R.ok();
    }

	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        YonghuEntity u = yonghuService.selectById(id);
        return R.ok().put("data", u);
    }


    @RequestMapping("/tingfangxinxibaocuo")
    public R tingFang(@RequestBody CheweixinxiEntity cheweixinxi, HttpServletRequest request){
        Long id1 = (Long)request.getSession().getAttribute("userId");
        YonghuEntity u = yonghuService.selectById(id1);

        XinxiEntity xinxiEntity = new XinxiEntity();
        xinxiEntity.setCheweihao(cheweixinxi.getCheweihao());
        xinxiEntity.setJiage(cheweixinxi.getJiage());
        xinxiEntity.setStatus(cheweixinxi.getStatus());
        xinxiEntity.setWeizhi(cheweixinxi.getWeizhi());
        xinxiEntity.setZhuyishixiang(cheweixinxi.getZhuyishixiang());
        xinxiEntity.setZhuangtai(cheweixinxi.getZhuangtai());
        xinxiEntity.setYonghuzhanghao(u.getYonghuzhanghao());
        xinxiEntity.setChepaihao(u.getYonghuchepai());
        xinxiEntity.setYonghuxingm(u.getYonghuxingming());

        Wrapper<XinxiEntity> wrapper = new EntityWrapper<>();
        if (xinxiEntity.getCheweihao() != null && !Objects.equals(xinxiEntity.getCheweihao(), "")){
            wrapper.eq("cheweihao",xinxiEntity.getCheweihao());
        }
        if (xinxiEntity.getJiage() != null && !Objects.equals(xinxiEntity.getJiage(), "")){
            wrapper.eq("jiage",xinxiEntity.getJiage());
        }
        if (xinxiEntity.getStatus() != null && !Objects.equals(xinxiEntity.getStatus(), "")){
            wrapper.eq("status",xinxiEntity.getStatus());
        }
        if (xinxiEntity.getYonghuzhanghao() != null && !Objects.equals(xinxiEntity.getYonghuzhanghao(), "")){
            wrapper.eq("yonghuzhanghao",xinxiEntity.getYonghuzhanghao());
        }
        if (xinxiEntity.getChepaihao() != null && !Objects.equals(xinxiEntity.getChepaihao(), "")){
            wrapper.eq("chepaihao",xinxiEntity.getChepaihao());
        }
        if (xinxiEntity.getYonghuxingm() != null && !Objects.equals(xinxiEntity.getYonghuxingm(), "")){
            wrapper.eq("yonghuxingm",xinxiEntity.getYonghuxingm());
        }

        CheweixinxiEntity cheweixinxiEntity = new CheweixinxiEntity();
        cheweixinxiEntity.setCheweihao(xinxiEntity.getCheweihao());

        EntityWrapper< CheweixinxiEntity> ew = new EntityWrapper< CheweixinxiEntity>();
        ew.allEq(MPUtil.allEQMapPre( cheweixinxiEntity, "cheweixinxi"));

        String cheweixinxiIsYesNo =  cheweixinxiService.selectView(ew).getZhuangtai();
        int xinxiLength = xinxiService.selectCount(wrapper);

        if (xinxiLength >= 1){
            return R.error("订单已生成，请勿重复提交！");
        }
        if (cheweixinxi.getStatus() == 0) {
            if (cheweixinxiIsYesNo.equals("使用中")) {
                return R.error("该车位正在使用中！");
            }
        }

        return xinxiService.insert(xinxiEntity)? R.ok() : R.error();
    }


    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        yonghuService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YonghuEntity yonghu,
		HttpServletRequest request){
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();

		PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YonghuEntity yonghu, 
		HttpServletRequest request){
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();

		PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YonghuEntity yonghu){
       	EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yonghu, "yonghu")); 
        return R.ok().put("data", yonghuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YonghuEntity yonghu){
        EntityWrapper< YonghuEntity> ew = new EntityWrapper< YonghuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yonghu, "yonghu")); 
		YonghuView yonghuView =  yonghuService.selectView(ew);
		return R.ok("查询用户成功").put("data", yonghuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YonghuEntity yonghu = yonghuService.selectById(id);
        return R.ok().put("data", yonghu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YonghuEntity yonghu = yonghuService.selectById(id);
        return R.ok().put("data", yonghu);
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
    	yonghu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		yonghu.setId(new Date().getTime());
        yonghuService.insert(yonghu);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
    	yonghu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("yonghuzhanghao", yonghu.getYonghuzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		yonghu.setId(new Date().getTime());
        yonghuService.insert(yonghu);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yonghu);
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().ne("id", yonghu.getId()).eq("yonghuzhanghao", yonghu.getYonghuzhanghao()))>0) {
            return R.error("用户账号已存在");
        }
        yonghuService.updateById(yonghu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yonghuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
