package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 停放信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
@TableName("tingfangxinxi")
public class TingfangxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public TingfangxinxiEntity() {
		
	}
	
	public TingfangxinxiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 车位号
	 */
					
	private String cheweihao;
	
	/**
	 * 图片
	 */
					
	private String tupian;
	
	/**
	 * 价格/时
	 */
					
	private Integer jiage;
	/**
	 * 价格/时
	 */

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 停放时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date tingfangshijian;
	
	/**
	 * 停放备注
	 */
					
	private String tingfangbeizhu;
	
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
	 * 识别图片
	 */
					
	private String tingcheshibietupian;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：车位号
	 */
	public void setCheweihao(String cheweihao) {
		this.cheweihao = cheweihao;
	}
	/**
	 * 获取：车位号
	 */
	public String getCheweihao() {
		return cheweihao;
	}
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
	public void setTingfangshijian(Date tingfangshijian) {
		this.tingfangshijian = tingfangshijian;
	}
	/**
	 * 获取：停放时间
	 */
	public Date getTingfangshijian() {
		return tingfangshijian;
	}
	/**
	 * 设置：停放备注
	 */
	public void setTingfangbeizhu(String tingfangbeizhu) {
		this.tingfangbeizhu = tingfangbeizhu;
	}
	/**
	 * 获取：停放备注
	 */
	public String getTingfangbeizhu() {
		return tingfangbeizhu;
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
	 * 设置：识别图片
	 */
	public void setTingcheshibietupian(String tingcheshibietupian) {
		this.tingcheshibietupian = tingcheshibietupian;
	}
	/**
	 * 获取：识别图片
	 */
	public String getTingcheshibietupian() {
		return tingcheshibietupian;
	}

}
