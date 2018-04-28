package com.market.maicheng.controllers.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImageController {
	
	@RequestMapping(value = "/verificationcode", method = RequestMethod.GET)  
	public ModelAndView loginPage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int width = 100;// 验证码图片宽度
		int height = 30;// 验证码图片高度
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = image.getGraphics();
		Random random = new Random();// 创建一个随机类
		g.setColor(getRandColor(200, 250));// 背景颜色要偏淡
		g.fillRect(0, 0, width, height);// 画背景
		g.setColor(getRandColor(0, 255));// 边框颜色
		g.drawRect(0, 0, width - 1, height - 1);// 画边框
		g.setColor(getRandColor(160, 200));// 随机产生5条干扰线，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 8; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			g.drawLine(x, y, x1, y1);
		}
		g.setColor(getRandColor(160, 200));// 随机产生100点，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawLine(x, y, x, y);
		}
		Font font = new Font("Times New Roman", Font.ITALIC, 26); // 创建字体，字体的大小应该根据图片的高度来定。
		g.setFont(font);// 设置字体
		int length = 4; // 设置默认生成4个验证码
		String s = "1234567890"; // 设置备选验证码:"0-9"
		String sRand = "";
		
		// 用随机产生的颜色将验证码绘制到图像中。
		// 生成随机颜色(因为是做前景，所以偏深)
		// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		for (int i = 0; i < length; i++) {
			String ch = String.valueOf(s.charAt(random.nextInt(s.length())));
			sRand += ch;
			g.drawString(ch, 22 * i + 12, (random.nextInt(5) - 2) * i + 20);
		}
		// 将生成的字符串存储在session中					
		//HttpSession session = request.getSession();
		//session.setAttribute("checkCode", sRand);
		
		//保存cookie
		Cookie cookie = new Cookie("checkCode",sRand);
		response.addCookie(cookie);
		
		g.dispose();// 图像生效
		// 禁止图像缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 创建二进制的输出流
		ServletOutputStream sos = response.getOutputStream();
		// 将图像输出到Servlet输出流中。
		ImageIO.write(image, "jpeg", sos);
		sos.flush();
		sos.close();					
		return null;
	}
	
	private Color getRandColor(int lower, int upper) {
		Random random = new Random();
		if (upper > 255)
			upper = 255;
		if (upper < 1)
			upper = 1;
		if (lower < 1)
			lower = 1;
		if (lower > 255)
			lower = 255;
		int r = lower + random.nextInt(upper - lower);
		int g = lower + random.nextInt(upper - lower);
		int b = lower + random.nextInt(upper - lower);
		return new Color(r, g, b);
	}
}
