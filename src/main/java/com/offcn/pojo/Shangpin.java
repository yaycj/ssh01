package com.offcn.pojo;

import java.sql.Date;

public class Shangpin {
	
	private Integer vegetableId;//产品Id
	private String name;//产品名称
	private Double price_hight;//价格最大值
	private Double price_ave;//价格平均值
	private Double price_low;//价格最小值
	private String spec;//产品规格
	private String unit;//价格单位
	private Date public_date;//统计日期
	private String catalog_name;//产品类型
	public Integer getVegetableId() {
		return vegetableId;
	}
	public void setVegetableId(Integer vegetableId) {
		this.vegetableId = vegetableId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice_hight() {
		return price_hight;
	}
	public void setPrice_hight(Double price_hight) {
		this.price_hight = price_hight;
	}
	public Double getPrice_ave() {
		return price_ave;
	}
	public void setPrice_ave(Double price_ave) {
		this.price_ave = price_ave;
	}
	public Double getPrice_low() {
		return price_low;
	}
	public void setPrice_low(Double price_low) {
		this.price_low = price_low;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Date getPublic_date() {
		return public_date;
	}
	public void setPublic_date(Date public_date) {
		this.public_date = public_date;
	}
	public String getCatalog_name() {
		return catalog_name;
	}
	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}
	
	
	
	
}
