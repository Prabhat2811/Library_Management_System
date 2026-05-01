package jsp.springboot.controller;

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

import jsp.springboot.Repository.BookRepository;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Book;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;
import jsp.springboot.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	
	//Insert a Record
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
		return new ResponseEntity<ResponseStructure<Book>>(bookService.saveBook(book),HttpStatus.CREATED);
		
	}
	
	//Insert Multiple Record
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Book>>> saveAllBooks(@RequestBody List<Book> book) {
		return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.saveAllBook(book), HttpStatus.CREATED);
	}
	
	//Fetch All Record
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBook() {
		return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.getAllBooks(), HttpStatus.FOUND);
	}
	
	//Fetch By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Integer id) {
			return new ResponseEntity<ResponseStructure<Book>>(bookService.getBookById(id), HttpStatus.FOUND);
	}
	
	
	//Update Book Record
	@PutMapping
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {		
			return new ResponseEntity<ResponseStructure<Book>>(bookService.updateBook(book), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
			return new ResponseEntity<ResponseStructure<String>>(bookService.deleteBook(id), HttpStatus.OK);
	}
	
	
	//Fetch By Author
	@GetMapping("/author/{author}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthor(@PathVariable String author){
			return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.getBookByAuthor(author), HttpStatus.FOUND);
	}
	
	
	//Fetch By Author and Title
	@GetMapping("/{author}/{title}")
	public ResponseEntity<ResponseStructure<Book>> getBookByAuthorAndTitle(@PathVariable String author, @PathVariable String title){
			return new ResponseEntity<ResponseStructure<Book>>(bookService.getBookByAuthorAndTitle(author, title), HttpStatus.FOUND);
	}
	
	
	//Fetch By Price Less Than
	@GetMapping("/price/{price}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPriceLessThan(@PathVariable Double price){
			return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.getBookByPriceLessThan(price), HttpStatus.FOUND);
	}
	
	//Fetch Book Between Price Range
	@GetMapping("/range/{startRange}/{endRange}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookBetweenPrice(@PathVariable Double startRange, @PathVariable Double endRange){
			return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.getBookBetweenPrice(startRange, endRange), HttpStatus.FOUND);
	}
	
	//Fetch Books Which are Available
	@GetMapping("/available")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookAvailability(){
			return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.getBookByAvailability(),HttpStatus.FOUND);
	}
	
	//Fetch Book by Published Year
	@GetMapping("/year/{year}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPublishedYear(@PathVariable Integer year){
			return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.getBookByPublishedYear(year), HttpStatus.FOUND);
	}
	
	//Fetch Book by Genre
	@GetMapping("/genre/{genre}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGenre(@PathVariable String genre){
			return new ResponseEntity<ResponseStructure<List<Book>>>(bookService.getBookByGenre(genre), HttpStatus.FOUND);
	}
}
