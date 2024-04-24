package com.account.editor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
	@Autowired
	AccountRepository repository;

	@GetMapping("/")
	public String Index(Model model) {
		Iterable<Account> list = repository.findAll();
		model.addAttribute("list", list);
		return "index";

	}

	@GetMapping("/delete")
	public String Delete(@RequestParam(name = "pno") Integer pno, Model model) {

		repository.deleteById(pno);

		Iterable<Account> list = repository.findAll();
		model.addAttribute("list", list);
		return "index";

	}

	@GetMapping("/update")

	public String Update(@RequestParam(name = "pno") Integer pno, @RequestParam(name = "pname") String pname,
			@RequestParam(name = "zandaka") Integer zandaka,
			@RequestParam(name = "batchupdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date batchupdate, Model model) {
		Account list = new Account();
		list.setZandaka(zandaka);
		list.setBatchupdate(batchupdate);
		list.setPname(pname);
		list.setPno(pno);
		model.addAttribute("list", list);
		return "UpdatePage";

	}

	@GetMapping("/updatedata")
	public String updatedata(@RequestParam(name = "pno") Integer pno, @RequestParam(name = "pname") String pname,
			@RequestParam(name = "zandaka") Integer zandaka,
			Model model) {
		

		Date now = new Date();
		Account entity = repository.findById(pno).orElse(null);
		if (entity != null) {
			entity.setPname(pname);
			entity.setZandaka(zandaka);
			entity.setBatchupdate(now);

			repository.save(entity);
		}

		Iterable<Account> list = repository.findAll();
		model.addAttribute("list", list);

		return "index";

	}

	@GetMapping("/insertpage")
	public String insertpage(Model model) {

		Account list = new Account();
		list.setZandaka(null);
		list.setBatchupdate(null);
		list.setPname(null);
		list.setPno(null);
		model.addAttribute("list", list);

		return "insertpage";

	}
	@GetMapping("/insertdata")
	public String insertdata(@RequestParam(name = "pno") Integer pno, @RequestParam(name = "pname") String pname,
			@RequestParam(name = "zandaka") Integer zandaka,
			Model model) {
		Date now = new Date();
		Account newlist = new Account();
		newlist.setZandaka(zandaka);
		newlist.setBatchupdate(now);
		newlist.setPname(pname);
		newlist.setPno(pno);
		repository.save(newlist);
		
		Iterable<Account> list = repository.findAll();
		model.addAttribute("list", list);
		return "index";
		
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(name = "pno") String pno,@RequestParam("select_num") int selectNum, Model model) {
		 if (selectNum ==2) {
			 
		Optional<Account> searchResult = repository.findById(Integer.parseInt(pno));
		searchResult.ifPresent(account -> model.addAttribute("list", account));
		 }	
		 else if(selectNum == 1) {
			 String pname = pno;
			 List<Account> searchResult = repository.findByPnameContaining(pname);
					 model.addAttribute("list", searchResult);
		 }
		    
		return "index";

	}

	private Account findById(String pno) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
