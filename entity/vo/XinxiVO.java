package com.entity.vo;



import java.io.Serializable;


public class XinxiVO<T> implements Serializable {
  private static final long serialVersionUID = 1L;


  private long status;
  private String cheweihao;
  private long jiage;
  private String weizhi;
  private String zhuangtai;
  private String zhuyishixiang;

  private String yonghuzhanghao;
  private String chepaihao;
  private String yonghuxingm;

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

  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

  public String getCheweihao() {
    return cheweihao;
  }

  public void setCheweihao(String cheweihao) {
    this.cheweihao = cheweihao;
  }


  public long getJiage() {
    return jiage;
  }

  public void setJiage(long jiage) {
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
