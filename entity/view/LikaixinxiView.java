package com.entity.view;

import com.entity.LikaixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 离开信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-05-03 22:30:06
 */
@TableName("likaixinxi")
public class LikaixinxiView  extends LikaixinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public LikaixinxiView(){
	}
 
 	public LikaixinxiView(LikaixinxiEntity likaixinxiEntity){
 	try {
			BeanUtils.copyProperties(this, likaixinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
