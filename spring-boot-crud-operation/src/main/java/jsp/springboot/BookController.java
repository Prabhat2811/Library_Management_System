package jsp.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	
	//Insert a Record
	//T save(T ref)
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
		ResponseStructure<Book> res=new ResponseStructure<Book>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Book Record saved");
		res.setData(bookRepository.save(book));
		return new ResponseEntity<ResponseStructure<Book>>(res,HttpStatus.CREATED);
		
	}
	
	//Insert Multiple Record
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Book>>> saveAllBooks(@RequestBody List<Book> book) {
		ResponseStructure<List<Book>> res=new ResponseStructure<>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Book Records saved");
		res.setData(bookRepository.saveAll(book));
		return new ResponseEntity<ResponseStructure<List<Book>>>(res, HttpStatus.CREATED);
	}
	
	//Fetch All Record
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBook() {
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		List<Book> books=bookRepository.findAll();
		if(!books.isEmpty()) {
		res.setStatusCode(HttpStatus.FOUND.value());
		res.setMessage("All Record Found");
		res.setData(books);
		return new ResponseEntity<ResponseStructure<List<Book>>>(res, HttpStatus.FOUND);
		}
		else {
			res.setStatusCode(HttpStatus.NOT_FOUND.value());
			res.setMessage("No Record Found");
			return new ResponseEntity<ResponseStructure<List<Book>>>(res, HttpStatus.NOT_FOUND);
		}
	}
	
	//Fetch By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Integer id) {
		ResponseStructure<Book> res=new ResponseStructure<Book>();
		
		Optional<Book>opt= bookRepository.findById(id);
		if(opt.isPresent()) {
			res.setData(opt.get());
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage("Book Record Found");
			return new ResponseEntity<ResponseStructure<Book>>(res, HttpStatus.FOUND);
		}
		else {
			res.setStatusCode(HttpStatus.NOT_FOUND.value());
			res.setMessage("Book Record Not Found");
			return new ResponseEntity<ResponseStructure<Book>>(res, HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Update Book Record
	@PutMapping
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		
		ResponseStructure<Book> res=new ResponseStructure<Book>();
		//case-I
		if(book.getId()==null) {
			res.setData(book);
			res.setStatusCode(HttpStatus.NOT_FOUND.value());
			res.setMessage("Id must be passed to Update");
			return new ResponseEntity<ResponseStructure<Book>>(res, HttpStatus.NOT_FOUND);
		}
		Optional<Book> opt=bookRepository.findById(book.getId());
		
		//case-II
		if(opt.isPresent()) {
			res.setData(bookRepository.save(book));
			res.setMessage("Updated");
			res.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Book>>(res, HttpStatus.OK);
		}
		//case-III
		else {
			res.setStatusCode(HttpStatus.NOT_FOUND.value());
			res.setMessage("Id does not exist in Database");
			return new ResponseEntity<ResponseStructure<Book>>(res, HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
		ResponseStructure<String> res=new ResponseStructure<String>();
		Optional<Book> opt=bookRepository.findById(id);
		if(opt.isPresent()) {
			res.setData("Deleted");
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Book Record Deleted");
			bookRepository.delete(opt.get());
			return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.OK);
		}
		else {
			res.setStatusCode(HttpStatus.NOT_FOUND.value());
			res.setMessage("Record Not Found As Id is Invalid");
			res.setData("Failure");
			return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
		}
	}
}
