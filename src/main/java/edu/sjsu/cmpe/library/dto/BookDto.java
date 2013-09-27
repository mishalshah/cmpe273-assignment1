package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Review;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto {
    private Book book;
    private Author author;
    private Review review;
    
    private static Map<String,Book> hmap =  new HashMap<String,Book>();

    
    
    /**
     * @param book
     */
    public BookDto(Book book, Author author, Review review) {
	super();
	this.book = book;
	this.author = author;
	this.review = review;
    }
    
    
    public BookDto(Book book, Author author, Review review, String str) {
    	
    	
    	this.book = book;
    	this.author = author;
    	this.review = review;
    	
        }

    /**
     * @return the book
     */
    public Book getBook() {

	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(Book book) {
	this.book = book;
    }
    
    //Get hardcoded book
    public Book hardBook()
    {
    	return book;
    }
    //get the book from HashMap
    public Book getBookByIsbn()
    {
    	String isbn1 = Long.toString(book.getIsbn());
    	System.out.println("hmap " + hmap.size());
    	return hmap.get(isbn1);
    }

    //Add book in Hash Hash Map
    public void addBook()	{
    	String isbn1 = Long.toString(this.getBook().getIsbn());
    	hmap.put(isbn1,book);
    	System.out.println(hmap.size());
    }
    
    //Delete book from Hash Map
    public void removeBook()	{
    	String isbn1 = Long.toString(book.getIsbn());
       	hmap.remove(isbn1);
    }
    
    //Update book in Hash Map
    
    public void updateBook()
    {
    	book.getStatus();
    	String isbn1 = Long.toString(book.getIsbn());
    	hmap.put(isbn1,book);
        	
    }
//*** Review ****/
    
    // Add review in HashMap
    public void addReview()
    {
    	String isbn1 = Long.toString(book.getIsbn());
//      	int id1 = review.getId();
       	Book mybook = hmap.get(isbn1);
       	Review review1 = book.getReview();
       	System.out.println("review id "+review1.getId());
       
       	List<Review> reviews = new ArrayList<Review>();
  		reviews = mybook.getReviews();
  		
  		if(reviews != null) {
  		try{	
  			reviews.add(review1);
       		mybook.setReviews(reviews);
       		}
  		catch(Exception e){
       		System.out.println("this is the spot");
       	}
  		}
  		else
  		{
  			List<Review> reviews1 = new ArrayList<Review>();
  			reviews1.add(review1);
  			mybook.setReviews(reviews1);
  		}
       		try{
//       		reviews.add(review1);
       		System.out.println("hi"+ review1.getId());
//       		mybook.setReviews(reviews);
//       		} catch(Exception e){System.out.println("here is that spot");}
       		hmap.put(isbn1, mybook);
       		}catch(Exception e)
       		{
       			System.out.println("error3");
       		}
       		
    }
    
    
    // View particular review for particular book
    public Review particularReview()
    {
    	String isbn1 = Long.toString(book.getIsbn());
//    	int id1=Review.id;
    	int id1 = review.getId();
    	Book mybook = hmap.get(isbn1);
    	List<Review> mylist = new ArrayList<Review>();
    			mylist = mybook.getReviews();
    	    	if(mylist != null)
    	    	{	
    	    		return mylist.get(id1);
    	    	}
    	    	else
    	    	{
    	    		return null;

    	    	}

    			
    }

  
    
    // view all the review of particular book
    public List<Review> allreviews()
    {
    	String isbn1 = Long.toString(book.getIsbn());
    	Book mybook = hmap.get(isbn1);
    	System.out.println(isbn1+"included in hashmap");

    	if(mybook.getReviews() != null)
    	{	
    		System.out.println("giving all the reviews");
    		return mybook.getReviews();
    	}
    	else
    	{
    		System.out.println("returning null values");
    		return Collections.EMPTY_LIST;

    	}

    }

    
    //Author name by id
    public Author authorName()
    {
    	String isbn1 = Long.toString(book.getIsbn());
    	int id1 = Author.id;
    	//   	int id1 = author.getId();
    	System.out.println("Author id"+id1);
    	Book mybook = hmap.get(isbn1);
    	List<Author> mylist = new ArrayList<Author>();
    			mylist = mybook.getAuthors();

    	    	if(mylist != null)
    	    	{	
    	    		return mylist.get(id1);
    	    	}
    	    	else
    	    	{
    	    		return null;

    	    	}

    			
    }
    
    public List<Author> allAuthors()
    {
    	String isbn1 = Long.toString(book.getIsbn());
    	Book mybook = hmap.get(isbn1);
    	if(mybook.getAuthors() != null)
    	{	
    		return mybook.getAuthors();
    	}
    	else
    	{
    		return Collections.EMPTY_LIST;

    	}
    }
}
