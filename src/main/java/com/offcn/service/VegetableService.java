package com.offcn.service;

import java.util.List;

import com.offcn.pojo.QueryVo;
import com.offcn.pojo.Shangpin;

public interface VegetableService {

	public void saveVegetable();
	
	public List<Shangpin> getVegetable(QueryVo qv);
	
	public void run();
}
