package jsp.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends  JpaRepository<Book, Integer>{
	
	//Fetch Book record by Naming 
	
	//fetch book by author
	List<Book> findByAuthor(String author);
	
	//fetch book by author and title
	Optional<Book> findByAuthorAndTitle(String author, String title);
	
	//fetch book by price less than
	List<Book> findByPriceLessThan(double price);
	
	//fetch book by price between a range
	List<Book> findByPriceBetween(double startRange, double endRange);
	
	
	
	
	
	//fetch Book record by using @Query
	
	@Query("Select b from Book b where b.availability=true")
	List<Book> getBookByAvailability();
	
	
	@Query("Select b from Book b where b.publishedYear=?1")
	List<Book> getBookByPublishedYear(Integer year);
	
	
	@Query("Select b from Book b where b.genre=:genre")
	List<Book> getBookByGenre(String genre);
}
