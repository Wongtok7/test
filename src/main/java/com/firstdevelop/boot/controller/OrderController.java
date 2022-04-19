package com.firstdevelop.boot.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.firstdevelop.boot.entity.Order;
import com.firstdevelop.boot.entity.Product;
import com.firstdevelop.boot.entity.UserEntity;
import com.firstdevelop.boot.form.EmailAdressForm;
import com.firstdevelop.boot.mapper.OrderMapper;
import com.firstdevelop.boot.service.OrderService;
import com.firstdevelop.boot.service.ProductService;

/**
 * 
 * @author study
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    //メール送信用、連絡先リスト処理用メソッド
	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;
	//DBに保存処理用メソッド
	@Autowired
	private OrderMapper orderMapper;
	
	// javaのメール送信機能インターフェース
	@Autowired
	private JavaMailSender mailSender;


	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/cart")
	public String cart(Model model, HttpSession session) {
		// as
		UserEntity user = (UserEntity) session.getAttribute("user");
		if (user != null) {
			List<Product> product_list = productService.searchAll();
			model.addAttribute("product_list", product_list);
			return "/mall/cart";
		} else {
			return "login";
		}
	}

	/**
	 * 
	 * @param productID オーダーしたの産品ID
	 * @param quantity  オーダーしたの産品数量
	 * @param session
	 * @return
	 */
	@PostMapping("/add")
	public String add(@RequestParam("productID") int productID, @RequestParam("quantity") int quantity,
			HttpSession session) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		System.out.println(productID + quantity + user.getId());
		orderMapper.insert(quantity, productID, user.getId());
		return "redirect:/order/searchAll";

	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 */

	@RequestMapping("/searchAll")
	public String searchAll(Model model, HttpSession session) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		if (user != null) {
			List<Order> order_list = orderMapper.searchByUserId(user.getId());
			System.out.println(order_list);
			model.addAttribute("order_list", order_list);
			return "/mall/list";
		}
		return "login";
	}

	/**
	 * メール送信画面を開く
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/openEmail")
	public String openEmail(Model model) {
		return "email/sendEmail";
	}

	@RequestMapping("/sendM")
	public String sendMail(@RequestParam("file_address_list") MultipartFile file_address_list,
			@RequestParam("file_email_text") MultipartFile file_email_text,
			@RequestParam("file_upload") MultipartFile file_upload,
			@RequestParam("title_email_text") String title_email_text, Model model) {
		InputStream textData = null;
		String emailText = null;
		try {
			String fileTextName = file_email_text.getOriginalFilename();
			textData = file_email_text.getInputStream();
			emailText = orderService.sendEmailText(fileTextName, textData);
		} catch (Exception e) {
			e.printStackTrace();
		}
        //メールアドレス処理
		InputStream addressData = null;
		List<EmailAdressForm> addressList = null;
		try {
			String fileAddressName = file_address_list.getOriginalFilename();
			addressData = file_address_list.getInputStream();
			addressList = orderService.sendEmailAddressList(fileAddressName, addressData);
		} catch (Exception e) {
			e.printStackTrace();
		}
        //エラー対応処理
		List<EmailAdressForm> errorAddressList = new ArrayList<>();
		boolean isError = false;
		String error_Message = null;
		String result_Message = null;
        //メール送信処理、アドレスリストをループして、一つずつ送信する。
		for (EmailAdressForm address : addressList) {
			// メール文本にある会社名（1行目）
			String title = address.getPerson();
            //メール文本にある連絡人（二行目）
			String perName = address.getPerson();
            //メールの基本設定
			MimeMessage message = mailSender.createMimeMessage();
			String fileName = file_upload.getOriginalFilename();
			MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(message, true);
				// 送信先
				helper.setTo(address.getEmailAdress());
                //件名
				helper.setSubject(title_email_text);
                //本文
				helper.setText(String.format(emailText, title, perName), true);
                //添付ファイル
				helper.addAttachment(fileName, file_upload);
				mailSender.send(message);
			} catch (Exception e) {
				// 送信失敗の場合、失敗のアドレス情報を格納
				e.printStackTrace();
				isError = true;
				errorAddressList.add(address);
			}
		}
        //リスポンス設定
		if (isError) {
			error_Message = "メール送信処理件数：" + addressList.size() + "。";
			result_Message = "送信成功：" + (addressList.size() - errorAddressList.size()) + "件。\n" + "送信失敗："
					+ errorAddressList.size() + "。";
			model.addAttribute("message", error_Message);
			model.addAttribute("result_Message", result_Message);
			model.addAttribute("errorAddressList", errorAddressList);
		} else {
			result_Message = addressList.size() + "件メール送信しました。";
			model.addAttribute("result_Message", result_Message);
		}
		return "email/email";
	}
}
