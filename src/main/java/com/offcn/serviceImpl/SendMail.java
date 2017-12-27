package com.offcn.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.offcn.mappers.VegetableMapper;
import com.offcn.pojo.QueryVo;
import com.offcn.pojo.Shangpin;

public class SendMail {
	
	 private JavaMailSenderImpl javaMailSenderImpl;
	 @Autowired
	 private VegetableMapper vegetableMapper;
	
	 
	public void setJavaMailSenderImpl(JavaMailSenderImpl javaMailSenderImpl) {
		this.javaMailSenderImpl = javaMailSenderImpl;
	}
	
	
	
	
/**
 * 
* @Title: getPdf
* @Description: 制作成pdf
* @param @param veList
* @param @param veList1 参数
* @return void 返回类型
* @throws
 */
	public void getPdf(List<Shangpin> veList,List<Shangpin> veList1){
		
		PdfWriter pw=null;
		
		try {
			pw =new PdfWriter(new File("d://189.pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PdfDocument pd =new PdfDocument(pw);
		
		Document doc=new Document(pd);
		
		Paragraph pag=new Paragraph();
		PdfFont font=null;
		try {
			font=PdfFontFactory.createFont("STSongStd-Light","UniGB-UCS2-H",false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pag.add("xxx月xxx分析报告");
		pag.setFont(font);
		pag.setTextAlignment(TextAlignment.CENTER);
		
		
		float[]fo=new float[veList.size()+1];
		
		for (int i = 0; i < fo.length; i++) {
			fo[i]=100/veList.size()+1;
		}
		
		Table table=new Table(fo);
		table.setWidthPercent(100);
		for (int i = 0; i < veList.size(); i++) {
             Cell cell=new Cell(1,1);
             if(i!=0){
			Paragraph pa =new Paragraph();
			Date d=veList.get(i).getPublic_date();
			SimpleDateFormat sdf =new SimpleDateFormat("dd");
			String sd =sdf.format(d);
			pa.add(sd);
			cell.add(pa);
             }
             table.addCell(cell);
		}
		for (int i = 0; i < veList.size(); i++) {
            Cell cell=new Cell(1,1);
            if(i==0){
            	Paragraph pa =new Paragraph();
    			Date d=veList.get(i).getPublic_date();
    			SimpleDateFormat sdf =new SimpleDateFormat("yyyy");
    			String sd =sdf.format(d);
    			pa.add(sd);
    			cell.add(pa);	
            }
            if(i!=0){
			Paragraph pa =new Paragraph();
			pa.add(String.valueOf(veList.get(i).getPrice_ave()));
			cell.add(pa);
            }
            table.addCell(cell);
		}
		for (int i = 0; i < veList.size(); i++) {
            Cell cell=new Cell(1,1);
            if(i==0){
            	Paragraph pa =new Paragraph();
    			Date d=veList.get(i).getPublic_date();
    			SimpleDateFormat sdf =new SimpleDateFormat("yyyy");
    			String sd =sdf.format(d);
    			pa.add(sd);
    			cell.add(pa);	
            }
            if(i!=0){
			Paragraph pa =new Paragraph();
			pa.add(String.valueOf(veList1.get(i).getPrice_ave()));
			cell.add(pa);
            }
            table.addCell(cell);
		}
		doc.add(table);
		
		
			
	}
	
/**
 * 
* @Title: 定时发送邮件
* @Description: 定时发送邮件
* @param  参数
* @return void 返回类型
* @throws
 */
	public void  send(QueryVo qv){
		
		List<Shangpin> s1=vegetableMapper.selectVegetable(qv);
		
		List<Shangpin> s2=vegetableMapper.selectVegetable(qv);
		
		getPdf(s1,s2);
		
	MimeMessage mm =javaMailSenderImpl.createMimeMessage();
	MimeMessageHelper helper=null;
	try {
		helper = new MimeMessageHelper(mm, true);
		helper.setFrom("13672092996@163.com");//发件人
		helper.setTo("13672092996@163.com");//收件人
		helper.setSubject("来呀");//主题
		helper.setText("真的");//内容
		
		ClassPathResource image = new ClassPathResource("/kl.jpg");//附件
		
		helper.addAttachment("kl.jpg", image);//添加附件
		// 发送邮件
		javaMailSenderImpl.send(mm);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
