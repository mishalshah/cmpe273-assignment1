package edu.sjsu.cmpe.library.api.resources;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
//import javax.ws.rs.UPDATE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;


@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class BookResource {

    public BookResource() {
	// do nothing
    }

    //view book
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
    	
		Book book = new Book();
		book.setIsbn(isbn.get());
		book.setTitle("Programming Amazon EC2");
		book.setPubDate("02/11/2011");
		book.setLanguage("eng");
		
		int x = 185;
		book.setPages(x);
		book.setStatus(Book.Status.available);
		
		Author author1 = new Author();
		author1.setId(5);
		author1.setName("Sithu Aung");
		Author author2 = new Author();
		author2.setId(6);
		author2.setName("Sithu Aung");

		
//		String author1 = "Sithu Aung";
//		String author2 = "Mishal Shah";
		List<Author> authorlist = new ArrayList<Author>();
		authorlist.add(author1);
		authorlist.add(author2);
		book.setAuthors(authorlist); 

    	BookDto bookResponse = new BookDto(book,null,null);
    	bookResponse.addBook();
    	bookResponse.addLink(new LinkDto("create-book", "/books/" + book.getIsbn(),"GET"));
    	bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
    	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
    	bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn()+"/reviews", "POST"));
    	bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + book.getIsbn()+"/reviews", "GET"));
    	return bookResponse;
    }
    
    //create book
    @POST
    @Path("")
    @Timed(name= "create-book")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public BookDto setBook(Book mybook) {

    		Random r = new Random();
    		int rand = r.nextInt(1000);
	    	mybook.setIsbn(rand);
	    		
	    	BookDto bookResponse = new BookDto(mybook,null,null);
	    	bookResponse.addBook();
	    	bookResponse.addLink(new LinkDto("create-book", "/books/" + mybook.getIsbn(),"GET"));
	    	bookResponse.addLink(new LinkDto("update-book","/books/" + mybook.getIsbn(), "PUT"));
	    	bookResponse.addLink(new LinkDto("delete-book","/books/" + mybook.getIsbn(), "DELETE"));
	    	bookResponse.addLink(new LinkDto("create-review","/books/" + mybook.getIsbn()+"/reviews", "POST"));
	    	bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + mybook.getIsbn()+"/reviews", "GET"));
	    	return bookResponse;
        }
    
    //Delete book api
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    
    public BookDto removeBookByIsbn(@PathParam("isbn") LongParam isbn) {

	Book book = new Book();
	book.setIsbn(isbn.get());
	BookDto bookResponse = new BookDto(book,null,null);
	bookResponse.removeBook();
	bookResponse.addLink(new LinkDto("create-book", "/books/" ,"POST"));
	
	return bookResponse;
    }
    
    //Update book API
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public BookDto updateBookByIsbn(@PathParam("isbn") long isbn, @QueryParam("status") Book.Status status) {
	
    Book book = new Book();
    book.setIsbn(isbn);
	book.setStatus(status);
	
	BookDto bookResponse = new BookDto(book,null,null);
	bookResponse.addLink(new LinkDto("create-book", "/books/" + book.getIsbn(),"GET"));
	bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
	bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn()+"/reviews", "POST"));
	bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + book.getIsbn()+"/reviews", "GET"));

	return bookResponse;
    }
    
    //create Review API
    @POST
    @Path("/{isbn}/reviews")
    @Timed(name = "create-review")
    public BookDto setReviewById(@PathParam("isbn") LongParam isbn, Review review) {
        	Book book = new Book();
        	book.setIsbn(isbn.get());

        	Random r = new Random();
    		int id = r.nextInt(50);
//        	int id = Review.id;
        	review.setId(Review.id);
//        	List<Review> reviewList = new ArrayList<Review>();
//        	reviewList.add(review);
        	
//        	review.setId(Review.id);
        	book.setReview(review);
        	
        	BookDto bookResponse = new BookDto(book,null,review);
        	bookResponse.addReview();
        	bookResponse.addLink(new LinkDto("view-review", "/books/" + book.getIsbn()+"/authors/"+review.getId(),"GET"));
        	return bookResponse;
    }
    
    //View review API
    @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn,@PathParam("id") int id) {
	Book book = new Book();
	book.setIsbn(isbn.get());
	
	Review review = new Review();
	Review.id=id;
//	review.setId(id);
	System.out.println(isbn.get()+"getting isbn");
	BookDto bookResponse = new BookDto(book,null,review);
	bookResponse.particularReview();
	bookResponse.addLink(new LinkDto("view-review", "/books/" + book.getIsbn()+"/authors/"+review.getId(),"GET"));
	return bookResponse;
    }
    

    //View all reviews
    @GET
    @Path("/{isbn}/reviews")
    @Timed(name = "review-book")
    public BookDto getAllReviews(@PathParam("isbn") LongParam isbn) {
	Book book = new Book();
	book.setIsbn(isbn.get());

	BookDto bookResponse = new BookDto(book,null,null);
	bookResponse.allreviews();
	return bookResponse;
    }

    // View book particular Author
    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-book")
    public BookDto getAuthorByIsbn(@PathParam("isbn") LongParam isbn,@PathParam("id") int id) {
	Book book = new Book();
	book.setIsbn(isbn.get());
	
	
	Author author = new Author();
	
//	Author.id = id;
//	author.setId();
	author.setId(id);
	
	BookDto bookResponse = new BookDto(book,author,null);
	bookResponse.authorName();
	bookResponse.addLink(new LinkDto("view-author", "/books/" + book.getIsbn()+"/authors/"+author.getId(),"GET"));
	return bookResponse;
    }
    
    //view all authors
    @GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-all-authors")
    public BookDto get(@PathParam("isbn") LongParam isbn) {

	Book book = new Book();
	book.setIsbn(isbn.get());
	
	BookDto bookResponse = new BookDto(book,null,null);
	bookResponse.allAuthors();
	
	return bookResponse;
    	
   	
    }
}


