package edu.sjsu.cmpe.library.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
	
    private long isbn;
    private String title;
    private String pubDate;
    private String language;
    private int pages;
    public enum Status 
    		{available, checked_out, in_queue, lost};
    private Status status;
    private List<Author> authors;
    private List<Review> reviews;
    public Review review;
    public Author author;
	
    public Status getStatus() {
		return status;
	}
    
	@JsonProperty("publication-date")
	public String getPubDate() {
		return pubDate;
	}

	@JsonProperty("publication-date")
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public void setStatus(Status status) {
//		Status.valueOf(status)
		this.status = status;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
//	public Date getPubDate() {
//		return pubDate;
//	}
//	public void setPubDate(String pubDate) throws ParseException {
//		this.pubDate = new SimpleDateFormat("MM/DD/YYYY").parse(pubDate);
//		//this.pubDate = pubDate;
//	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@JsonProperty("num-pages")
	public int getPages() {
		return pages;
	}

   @JsonProperty("num-pages")		
   public void setPages(int pages) {
		this.pages = pages;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	} 
	
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
}
