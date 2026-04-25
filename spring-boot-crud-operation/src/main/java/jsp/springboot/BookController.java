package jsp.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	
	//Insert a Record
	@PostMapping
	public ResponseStructure<Book> saveBook(@RequestBody Book book) {
		ResponseStructure<Book> res=new ResponseStructure<Book>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Book Record saved");
		res.setData(bookRepository.save(book));
		return res;
		
	}
	
	//Insert Multiple Record
	@PostMapping("/all")
	public ResponseStructure<List<Book>> saveAllBooks(@RequestBody List<Book> book) {
		ResponseStructure<List<Book>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Book Records saved");
		res.setData(bookRepository.saveAll(book));
		return res;
	}
	
	//Fetch All Record
	@GetMapping
	public ResponseStructure<List<Book>> getAllBook() {
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("All Record Found");
		res.setData(bookRepository.findAll());
		return res;
	}
	
	//Fetch By Id
	@GetMapping("/{id}")
	public ResponseStructure<Book> getBookById(@PathVariable Integer id) {
		ResponseStructure<Book> res=new ResponseStructure();
		
		Optional<Book>opt= bookRepository.findById(id);
		if(opt.isPresent()) {
			res.setData(opt.get());
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage("Book Record Found");
			return res;
		}
		else {
			res.setStatusCode(HttpStatus.NOT_FOUND.value());
			res.setMessage("Book Record Not Found");
			return res;
		}
	}
	
	@PutMapping
	public ResponseStructure<Book> updateBook(@RequestBody Book book) {
		
		ResponseStructure<Book> res=new ResponseStructure<Book>();
		//case-I
		if(book.getId()==null) {
			res.setData(book);
			res.setStatusCode(HttpStatus.NOT_FOUND.value());
			res.setMessage("Id must be passed to Update");
			return res;
		}
		Optional<Book> opt=bookRepository.findById(book.getId());
		
		//case-II
		if(opt.isPresent()) {
			res.setData(bookRepository.save(book));
			res.setMessage("Updated");
			res.setStatusCode(HttpStatus.OK.value());
			return res;
		}
		//case-III
		else {
			res.setStatusCode(HttpStatus.NOT_FOUND.value());
			res.setMessage("Id does not exist in Database");
			return res;
		}
	}
	@DeleteMapping("/{id}") 
	public ResponseStructure<Optional<Book>> deleteBook(@PathVariable Integer id) {
		ResponseStructure<Optional<Book>> res=new ResponseStructure<Optional<Book>>();
		Optional<Book> opt=bookRepository.findById(id);
		if(opt.isPresent()) {
			res.setData(bookRepository.findById(id));
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Record Deleted");
			bookRepository.delete(res.getData().get());
			return res;
		}
		else {
			res.setStatusCode(HttpStatus.NOT_FOUND.value());
			res.setMessage("Record Not Found As Id is Invalid");
			return res;
		}
	}
}
