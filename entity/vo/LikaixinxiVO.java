package com.entity.vo;

import com.entity.LikaixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 离开信息
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
public class LikaixinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 价格/时
	 */
	
	private Integer jiage;
		
	/**
	 * 停放时间
	 */
	
	private String tingfangshijian;
		
	/**
	 * 离开时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date likaishijian;
		
	/**
	 * 时长
	 */
	
	private Integer shizhang;
		
	/**
	 * 总额
	 */
	
	private Double zonge;
		
	/**
	 * 离开备注
	 */
	
	private String likaibeizhu;
		
	/**
	 * 用户账号
	 */
	
	private String yonghuzhanghao;
		
	/**
	 * 用户姓名
	 */
	
	private String yonghuxingming;
		
	/**
	 * 电话
	 */
	
	private String dianhua;
		
	/**
	 * 车牌号
	 */
	
	private String tingfangpaihao;
		
	/**
	 * 跨表用户id
	 */
	
	private Long crossuserid;
		
	/**
	 * 跨表主键id
	 */
	
	private Long crossrefid;
		
	/**
	 * 是否支付
	 */
	
	private String ispay;
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：价格/时
	 */
	 
	public void setJiage(Integer jiage) {
		this.jiage = jiage;
	}
	
	/**
	 * 获取：价格/时
	 */
	public Integer getJiage() {
		return jiage;
	}
				
	
	/**
	 * 设置：停放时间
	 */
	 
	public void setTingfangshijian(String tingfangshijian) {
		this.tingfangshijian = tingfangshijian;
	}
	
	/**
	 * 获取：停放时间
	 */
	public String getTingfangshijian() {
		return tingfangshijian;
	}
				
	
	/**
	 * 设置：离开时间
	 */
	 
	public void setLikaishijian(Date likaishijian) {
		this.likaishijian = likaishijian;
	}
	
	/**
	 * 获取：离开时间
	 */
	public Date getLikaishijian() {
		return likaishijian;
	}
				
	
	/**
	 * 设置：时长
	 */
	 
	public void setShizhang(Integer shizhang) {
		this.shizhang = shizhang;
	}
	
	/**
	 * 获取：时长
	 */
	public Integer getShizhang() {
		return shizhang;
	}
				
	
	/**
	 * 设置：总额
	 */
	 
	public void setZonge(Double zonge) {
		this.zonge = zonge;
	}
	
	/**
	 * 获取：总额
	 */
	public Double getZonge() {
		return zonge;
	}
				
	
	/**
	 * 设置：离开备注
	 */
	 
	public void setLikaibeizhu(String likaibeizhu) {
		this.likaibeizhu = likaibeizhu;
	}
	
	/**
	 * 获取：离开备注
	 */
	public String getLikaibeizhu() {
		return likaibeizhu;
	}
				
	
	/**
	 * 设置：用户账号
	 */
	 
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
				
	
	/**
	 * 设置：用户姓名
	 */
	 
	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}
	
	/**
	 * 获取：用户姓名
	 */
	public String getYonghuxingming() {
		return yonghuxingming;
	}
				
	
	/**
	 * 设置：电话
	 */
	 
	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}
	
	/**
	 * 获取：电话
	 */
	public String getDianhua() {
		return dianhua;
	}
				
	
	/**
	 * 设置：车牌号
	 */
	 
	public void setTingfangpaihao(String tingfangpaihao) {
		this.tingfangpaihao = tingfangpaihao;
	}
	
	/**
	 * 获取：车牌号
	 */
	public String getTingfangpaihao() {
		return tingfangpaihao;
	}
				
	
	/**
	 * 设置：跨表用户id
	 */
	 
	public void setCrossuserid(Long crossuserid) {
		this.crossuserid = crossuserid;
	}
	
	/**
	 * 获取：跨表用户id
	 */
	public Long getCrossuserid() {
		return crossuserid;
	}
				
	
	/**
	 * 设置：跨表主键id
	 */
	 
	public void setCrossrefid(Long crossrefid) {
		this.crossrefid = crossrefid;
	}
	
	/**
	 * 获取：跨表主键id
	 */
	public Long getCrossrefid() {
		return crossrefid;
	}
				
	
	/**
	 * 设置：是否支付
	 */
	 
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	
	/**
	 * 获取：是否支付
	 */
	public String getIspay() {
		return ispay;
	}
			
}
