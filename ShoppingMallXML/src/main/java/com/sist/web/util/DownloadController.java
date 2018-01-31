/*package com.sist.web.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;

public class DownloadController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path=request.getParameter("p");
		String fname=request.getParameter("f");
		
		String urlPath=path+"/"+fname;
		String realPath = request.getServletContext().getRealPath(urlPath);
		System.out.println("urlPath:"+urlPath);
		System.out.println("realPath:"+realPath);
		
		//한글 첨부시 깨지는 현상을 방지
		String newfname= new String(fname.getBytes(), "ISO-8859-1");


		//응답헤더를 보내줌 내용의위치, 파일유형
		response.setHeader("Content-Disposition", "attachment;filename="+newfname);
		//파일저장
		FileInputStream fis = new FileInputStream(realPath);
		//서블릿스트림
		ServletOutputStream sout = response.getOutputStream();
		
		
		byte[] buf = new byte[1024];
		//한번에 읽어올때 1kb만큼 읽음
		int size=0;
		//더이상 읽어올데이터가 없으면 -1을 리턴
		while((size=fis.read(buf))!=-1){
			sout.write(buf);
		}
		fis.close();
		sout.close();
		return "void:";
	}
}
*/