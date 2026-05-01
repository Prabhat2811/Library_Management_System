package jsp.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springboot.Repository.BookRepository;
import jsp.springboot.dto.ResponseStructure;
import jsp.springboot.entity.Book;
import jsp.springboot.exception.IdNotFoundException;
import jsp.springboot.exception.NoRecordAvailableException;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	//T save(T ref)
	//Save a Record. 
	public ResponseStructure<Book> saveBook(Book book){
		ResponseStructure<Book> res=new ResponseStructure<Book>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Book Record Saved");
		res.setData(bookRepository.save(book));
		return res;
	}
	
	//Save all Record.
	public ResponseStructure<List<Book>> saveAllBook(List<Book> books){
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("All Book Record Saved");
		res.setData(books);
		return res;
	}
	
	//Fetch All Record.
	public ResponseStructure<List<Book>> getAllBooks(){
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		List<Book> books=bookRepository.findAll();
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage("All Book Record Found");
			res.setData(books);
			return res;
		}
		else
			throw new NoRecordAvailableException("No Book Record Available");
	}
	
	//Fetch Record using Id
	public ResponseStructure<Book> getBookById(Integer id){
		ResponseStructure<Book> res=new ResponseStructure<Book>();
		Optional<Book> opt=bookRepository.findById(id);
		if(opt.isPresent()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage("Book Record Found With Id "+id);
			res.setData(opt.get());
			return res;
		}
		else
			throw new IdNotFoundException("Book Record with Id "+id+" doesn't Exist.");
	}
	
	//Update a Record.
	public ResponseStructure<Book> updateBook(Book book){
		//case-I
		if(book.getId()==null) {
			throw new IdNotFoundException("Id Should Be present to Update a Existing Record.");
		}
		
		ResponseStructure<Book> res=new ResponseStructure<Book>();
		Optional<Book> opt=bookRepository.findById(book.getId());
		//case-II
		if(opt.isPresent()) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Updated");
			res.setData(bookRepository.save(book));
			return res;
		}
		//case-III
		else
			throw new NoRecordAvailableException("Book Record With id "+book.getId()+" Does not Exist");
	}
	
	//Delete a Record
	public ResponseStructure<String> deleteBook(Integer id){
		ResponseStructure<String>res=new ResponseStructure<String>();
		Optional<Book> opt=bookRepository.findById(id);
		if(opt.isPresent()) {
			bookRepository.deleteById(id);
			res.setStatusCode(HttpStatus.OK.value());
			res.setData("Deleted");
			res.setMessage("Book Record Deleted");
			return res;
		}
		else
			throw new NoRecordAvailableException("Book Record With id "+id+" Does not Exist");
	}
	
	//Fetch Record By Author
	public ResponseStructure<List<Book>> getBookByAuthor(String author){
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		List<Book> books=bookRepository.findByAuthor(author);
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage(books.size()+" Book Record Found written by "+author);
			res.setData(books);
			return res;
		}
		else
			throw new NoRecordAvailableException("No Book Record Available written by "+author);
	}
	
	//Fetch Record By Author and Title
	public ResponseStructure<Book> getBookByAuthorAndTitle(String author, String title){
		ResponseStructure<Book> res=new ResponseStructure<Book>();
		Optional<Book> opt=bookRepository.findByAuthorAndTitle(author, title);
		if(opt.isPresent()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage("Book Record found written by "+author+" and the Title : "+title);
			res.setData(opt.get());
			return res;
		}
		else
			throw new NoRecordAvailableException("No Book is Available written by "+author+" and the Title : "+title);
	}
	
	//Fetch by Price Less Than
	public ResponseStructure<List<Book>> getBookByPriceLessThan(Double price){
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		List<Book> books=bookRepository.findByPriceLessThan(price);
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage(books.size()+" Book Record Found whose Price is less than "+price+"rs");
			res.setData(books);
			return res;
		}
		else
			throw new NoRecordAvailableException("No Book Available whose Price is less than "+price+"rs");
	}
	
	//Fetch Book Between Price Range
	public ResponseStructure<List<Book>> getBookBetweenPrice(Double initial, Double end){
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		List<Book> books=bookRepository.findByPriceBetween(initial, end);
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage(books.size()+" Book Record Found whose price is in Between "+initial+"rs and "+end+"rs");
			res.setData(books);
			return res;
		}
		else
			throw new NoRecordAvailableException("No Book Record Avialable whose price is in between "+initial+"rs and "+end+"rs");
	}
	
	//Fetch Books which are Available
	public ResponseStructure<List<Book>> getBookByAvailability(){
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		List<Book> books=bookRepository.getBookByAvailability();
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage(books.size()+" Book Records Available");
			res.setData(books);
			return res;
		}
		else
			throw new NoRecordAvailableException("No Book is Available");
	}
	
	//Fetch Book by Published Year
	public ResponseStructure<List<Book>> getBookByPublishedYear(Integer year){
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		List<Book> books=bookRepository.getBookByPublishedYear(year);
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage(books.size()+" Books are Available Published in "+year);
			res.setData(books);
			return res;
		}
		else
			throw new NoRecordAvailableException("No Book record Available Published in "+year);
	}
	
	//Fetch Book by Genre
	public ResponseStructure<List<Book>> getBookByGenre(String genre){
		ResponseStructure<List<Book>> res=new ResponseStructure<List<Book>>();
		List<Book> books=bookRepository.getBookByGenre(genre);
		if(!books.isEmpty()) {
			res.setStatusCode(HttpStatus.FOUND.value());
			res.setMessage(books.size()+" Books Found whose Genre is "+genre);
			res.setData(books);
			return res;
		}
		else
			throw new NoRecordAvailableException("No Record Available whose Genre is "+genre);
	}
}
