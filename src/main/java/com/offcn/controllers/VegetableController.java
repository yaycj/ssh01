package com.offcn.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.offcn.pojo.QueryVo;
import com.offcn.pojo.Shangpin;
import com.offcn.service.VegetableService;

@Controller
public class VegetableController {

	@Autowired
	private VegetableService vegetableService;
	
	@RequestMapping(value="zhuaqu.action")
	public String saveZhuaQu(){
		
		vegetableService.saveVegetable();
		return "main.jsp";	
	}
	
	@RequestMapping(value="ajaxhuoqu.action")
	@ResponseBody
	public List<List<Shangpin>> getVegetable(@RequestBody QueryVo qv){
		
		/*QueryVo qv =new QueryVo();*/
		Calendar c =Calendar.getInstance();
		
		c.set(2016, 11, 1);
		
		Date dfe=c.getTime();
			
		c.set(2016, 11, 30);
		
		Date dfe1=c.getTime();
			
		qv.setData1(new java.sql.Date(dfe.getTime()));
		qv.setData2(new java.sql.Date(dfe1.getTime()));	
	
		System.out.println(qv.getData1());
		System.out.println(qv.getData2());
		
		List<Shangpin> liv=vegetableService.getVegetable(qv);
		
        c.set(2017, 11, 1);
		
		Date dfe2=c.getTime();
			
		c.set(2017, 11, 30);
		
		Date dfe3=c.getTime();
			
		qv.setData1(new java.sql.Date(dfe2.getTime()));
		qv.setData2(new java.sql.Date(dfe3.getTime()));
			
		List<Shangpin> liv2=vegetableService.getVegetable(qv);
		
		List<List<Shangpin>> lops =new ArrayList<List<Shangpin>>();
		
		lops.add(liv);
		lops.add(liv2);
		
		return lops;	
	}
	
	
	
	
}
