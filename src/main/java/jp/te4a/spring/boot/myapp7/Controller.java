package jp.te4a.spring.boot.myapp7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {
	final String bookListUrl = "books/list";
	@Autowired
	BookService bookService;

	@RequestMapping(bookListUrl)
	public String index(Model model) {
		model.addAttribute("msg", "this is setting message");
		return bookListUrl;
	}

	@RequestMapping(value = bookListUrl, method = RequestMethod.POST)
	public ModelAndView postForm(
			 @RequestParam("id")        String id
			,@RequestParam("title")     String title
			,@RequestParam("writter")   String writter
			,@RequestParam("publisher") String publisher
			,@RequestParam("price")     String price
			) {
		ModelAndView mv = new ModelAndView(bookListUrl);
		bookService.save(new BookBean(Integer.valueOf(id), title, writter, publisher, Integer.valueOf(price)));
		mv.addObject("books", bookService.findAll());
		return mv;
	}
}
