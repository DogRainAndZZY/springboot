package com.zzy.controller;


import com.google.code.kaptcha.Producer;
import com.zzy.entity.Auction_user;
import com.zzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private Producer producer;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpSession session) {
		Auction_user user = new Auction_user(request.getParameter("user_name"),
				request.getParameter("user_password"));
		if (userService.login(user) != null
				&& request.getParameter("code").compareToIgnoreCase(
						(String) session.getAttribute("kaptcha")) == 0) {
			Auction_user right_user = userService.login(user);
			System.out.println(right_user);
			session.setAttribute("user", right_user);
			return "redirect:/getAll.do";
		}
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}

	@RequestMapping("/regist")
	public String regist(HttpServletRequest request, HttpSession session) {
		if (request.getParameter("code").compareToIgnoreCase(
				(String) session.getAttribute("kaptcha")) == 0) {
			Auction_user user = new Auction_user(
					request.getParameter("user_name"),
					request.getParameter("user_password"));
			userService.regist(user);
			session.setAttribute("user", user);
		}
		return "login";
	}

	@RequestMapping("/getKaptcha")
	public void getKaptcha(HttpSession session, HttpServletResponse response)
			throws IOException {

		String text = producer.createText();

		session.setAttribute("kaptcha", text);

		BufferedImage image = producer.createImage(text);

		ImageIO.write(image, "jpg", response.getOutputStream());

	}
}
