package edu.mum.cs544.service;

import java.util.List;
import edu.mum.cs544.domain.Book;
import edu.mum.cs544.exception.NotFoundException;

public interface IBookService {
    public List<Book> getAll();

    public void add(Book book);

    public Book get(int id) throws NotFoundException;

    public void update(Book book);

    public void delete(int id);
}
