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
		
		//�ѱ� ÷�ν� ������ ������ ����
		String newfname= new String(fname.getBytes(), "ISO-8859-1");


		//��������� ������ ��������ġ, ��������
		response.setHeader("Content-Disposition", "attachment;filename="+newfname);
		//��������
		FileInputStream fis = new FileInputStream(realPath);
		//������Ʈ��
		ServletOutputStream sout = response.getOutputStream();
		
		
		byte[] buf = new byte[1024];
		//�ѹ��� �о�ö� 1kb��ŭ ����
		int size=0;
		//���̻� �о�õ����Ͱ� ������ -1�� ����
		while((size=fis.read(buf))!=-1){
			sout.write(buf);
		}
		fis.close();
		sout.close();
		return "void:";
	}
}
*/