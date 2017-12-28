package com.offcn.serviceImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offcn.mappers.VegetableMapper;
import com.offcn.pojo.QueryVo;
import com.offcn.pojo.Shangpin;
import com.offcn.service.VegetableService;

@Service
public class VegetableServiceImpl implements VegetableService{

	@Autowired
	private VegetableMapper vegetableMapper;

	public void saveVegetable() {
		List<Shangpin> velist=new ArrayList<Shangpin>();
		String plo=zhuaqu();
		
		velist=getVegetable(plo);
		for (Shangpin vegetable2 : velist) {
			vegetableMapper.saveVegetable(vegetable2);
		}
	}
	
	/**
	 * 
	* @Title: 
	* @Description: 抓取的内容转换为字符串
	* @param @return 参数
	* @return String 返回类型
	* @throws
	 */
	public String zhuaqu(){
		
	CloseableHttpClient httpClient=HttpClients.createDefault();
	
	Date d =new Date();
	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
	
	Calendar c =Calendar.getInstance();
	c.setTime(d);
	c.set(Calendar.DATE, c.get(Calendar.DATE)-1);
	
	
	
	String starttime=sdf.format(c.getTime());
	
	  String endtime= sdf.format(d);
	
	String oplo="http://www.xinfadi.com.cn/marketanalysis/1/list/1.shtml?prodname=&begintime="+starttime+"&endtime="+endtime;
	
	HttpGet htteGet=new HttpGet(oplo);
	
	
		CloseableHttpResponse response =null;
		
		try {
			response=httpClient.execute(htteGet);
			HttpEntity entity=response.getEntity();
			
			String entityString= EntityUtils.toString(entity, "utf-8");
			return entityString;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	* @Title: getVegetable
	* @Description: 用jsoup将字符串转换为节点操作封装为对象
	* @param @param entityString
	* @param @return vagetable集合
	* @return List<Shangpin> 返回类型
	* @throws
	 */
	public List<Shangpin> getVegetable(String entityString){
		
		Document document = Jsoup.parse(entityString); 
		
		Elements dom = document.select("table[class=hq_table]").select("table").select("tr");
		List<Shangpin> veList=new ArrayList<Shangpin>();
		
		for (int i = 1; i < dom.size(); i++) {
			Elements lpo = dom.get(i).select("td");
			Shangpin shangpin =new Shangpin();
			shangpin.setName(lpo.get(0).html());
			shangpin.setPrice_low(Double.valueOf(lpo.get(1).html()));
			shangpin.setPrice_ave(Double.valueOf(lpo.get(2).html()));
			shangpin.setPrice_hight(Double.valueOf(lpo.get(3).html()));
			shangpin.setSpec(lpo.get(4).html());
			shangpin.setUnit(lpo.get(5).html());
		
			veList.add(shangpin);
		}	
		return veList;
	}
	
	public List<Shangpin> getVegetable(QueryVo qv){
		
		List<Shangpin> veList= vegetableMapper.selectVegetable(qv);
		
		return veList;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(111);
	}
	
}
