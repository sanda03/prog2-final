package com.sanda.prog2.service;

import com.sanda.prog2.model.Book;
import com.sanda.prog2.repository.BookRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    private boolean testBadRequest(Book book,boolean testId){
        if(testId && book.getIdBook() == null)
            return true;
        return book.getTitle() == null ||
                book.getDescription() == null ||
                book.getIdAuthor() == null ||
                book.getIdCategory() == null;
    }
    public List<Book> getAllBooks(HttpServletResponse response){
        try {
            return this.bookRepository.getALlBooks();
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Book getBookById(HttpServletResponse response,Integer idBook){
        try {
            Book book = this.bookRepository.getBookById(idBook);
            if (book == null){
                SendError.notFound(
                        response,
                        "There is no book with id = " + idBook
                );
            }
            return book;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Book deleteBook(HttpServletResponse response,Integer idBook){
        try {
            Book book = this.bookRepository.deleteBook(idBook);
            if (book == null){
                SendError.notFound(
                        response,
                        "Cannot delete book with id = " + idBook + " because it doesn't exist"
                );
            }
            return book;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Book updateBook(HttpServletResponse response,Book book){
        try {
            if(testBadRequest(book,true)){
                SendError.badRequest(
                        response,
                        "There is something missed of your request body"
                );
                return null;
            }

            Book oldBook= this.bookRepository.updateBook(book);
            if (oldBook == null){
                SendError.notFound(
                        response,
                        "Cannot update book with id = " + book.getIdBook() + " because it doesn't exist"
                );
            }
            return book;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Book updatePartialBook(HttpServletResponse response,Book book){
        try {
            if(
                    book.getIdBook() == null ||
                    ( book.getTitle() == null && book.getDescription() == null)
            ){
                SendError.badRequest( response, "Verify you body request" );
                return null;
            }

            Book updateBook = this.bookRepository.updatePartialBook(book);
            if (updateBook == null){
                SendError.notFound(
                        response,
                        "Cannot update book with id = " + book.getIdBook() + " because it doesn't exist"
                );
            }
            return updateBook;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Book createBook(HttpServletResponse response,Book book){
        try {
            if(testBadRequest(book,false)){
                SendError.badRequest(
                        response,
                        "There is something missed of your request body"
                );
                return null;
            }
            return this.bookRepository.createBook(book);
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }
}
