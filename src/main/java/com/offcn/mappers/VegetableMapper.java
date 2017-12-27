package com.offcn.mappers;

import java.util.List;

import com.offcn.pojo.QueryVo;
import com.offcn.pojo.Shangpin;

public interface VegetableMapper {
	
	public void saveVegetable(Shangpin shangpin);
	
	public List<Shangpin> selectVegetable(QueryVo qv);
	
	
	

}
