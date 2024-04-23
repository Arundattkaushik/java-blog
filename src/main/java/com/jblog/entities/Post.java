package com.jblog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "post_title", length = 150)
	@NotBlank
	private String title;
	
	@Column(name = "post_content", length = 1500)
	private String content;
	
	@Column(name = "post_image")
	private String image;
	
	@Column(name = "post_added_date")
	private Date addeDate;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	@JsonBackReference
	private Set<Comment> comments = new HashSet<Comment>();
	
	@ManyToOne
	private User user;
	
	private Date postUpdateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getAddeDate() {
		return addeDate;
	}

	public void setAddeDate(Date addeDate) {
		this.addeDate = addeDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPostUpdateDate() {
		return postUpdateDate;
	}

	public void setPostUpdateDate(Date postUpdateDate) {
		this.postUpdateDate = postUpdateDate;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
