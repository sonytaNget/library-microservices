package edu.mum.cs544.service;

import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.mum.cs544.dao.BookDao;
import edu.mum.cs544.domain.Book;
import edu.mum.cs544.exception.NotFoundException;

@Service
@Transactional
public class BookService implements IBookService {
    @Resource
    private BookDao bookDao;

    @Override
    public List<Book> getAll() {
        return bookDao.findAll();
    }

    @Override
    public void add(Book book) {
        bookDao.save(book);
    }

    @Override
    public Book get(int id) throws NotFoundException {
        Book book = bookDao.findById(id).orElse(null);
        if (book == null) {
            throw new NotFoundException("Book with Id " + id + " is not found.");
        }
        return book;
    }

    @Override
    public void update(Book book) {
        Book b = bookDao.findById(book.getId()).orElse(null);
        if (b == null) {
            throw new NotFoundException("Book with Id " + book.getId() + " doesn't exist");
        }
        bookDao.save(book);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        try {
            bookDao.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Book with Id " + id + " doesn't exist");
        }
    }
}
