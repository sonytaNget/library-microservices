package edu.mum.cs544.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs544.domain.Book;

public interface BookDao extends JpaRepository<Book, Integer> {

}
