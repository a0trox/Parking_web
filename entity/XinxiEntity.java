package com.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


@TableName("xinxi")
public class XinxiEntity<T> implements Serializable {
  private static final long serialVersionUID = 1L;


  public XinxiEntity() {

  }

  public XinxiEntity(T t) {
    try {
      BeanUtils.copyProperties(this, t);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  @TableId(type = IdType.AUTO)
  private Long id;
  private Integer status;
  private String cheweihao;
  private String yonghuzhanghao;
  private String chepaihao;
  private String yonghuxingm;
  private Integer jiage;
  private String weizhi;
  private String zhuangtai;
  private String zhuyishixiang;

  public String getYonghuzhanghao() {
    return yonghuzhanghao;
  }

  public void setYonghuzhanghao(String yonghuzhanghao) {
    this.yonghuzhanghao = yonghuzhanghao;
  }

  public String getChepaihao() {
    return chepaihao;
  }

  public void setChepaihao(String chepaihao) {
    this.chepaihao = chepaihao;
  }

  public String getYonghuxingm() {
    return yonghuxingm;
  }

  public void setYonghuxingm(String yonghuxingm) {
    this.yonghuxingm = yonghuxingm;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getCheweihao() {
    return cheweihao;
  }

  public void setCheweihao(String cheweihao) {
    this.cheweihao = cheweihao;
  }


  public Integer getJiage() {
    return jiage;
  }

  public void setJiage(Integer jiage) {
    this.jiage = jiage;
  }


  public String getWeizhi() {
    return weizhi;
  }

  public void setWeizhi(String weizhi) {
    this.weizhi = weizhi;
  }


  public String getZhuangtai() {
    return zhuangtai;
  }

  public void setZhuangtai(String zhuangtai) {
    this.zhuangtai = zhuangtai;
  }


  public String getZhuyishixiang() {
    return zhuyishixiang;
  }

  public void setZhuyishixiang(String zhuyishixiang) {
    this.zhuyishixiang = zhuyishixiang;
  }

}
