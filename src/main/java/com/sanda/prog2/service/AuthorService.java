package com.sanda.prog2.service;

import com.sanda.prog2.model.Author;
import com.sanda.prog2.repository.AuthorRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    private AuthorRepository authorRepository;

    private boolean testBadRequest(Author author,boolean testId){
        if(testId && author.getIdAuthor() == null)
            return true;
        return author.getName() == null ||
                author.getFirstName() == null;
    }
    public List<Author> getAllAuthors(HttpServletResponse response){
        try {
            return this.authorRepository.getALlAuthors();
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Author getAuthorById(HttpServletResponse response,Integer idAuthor){
        try {
            Author author = this.authorRepository.getAuthorById(idAuthor);
            if (author == null){
                SendError.notFound(
                    response,
                    "There is no author with id = " + idAuthor
                );
            }
            return author;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Author deleteAuthor(HttpServletResponse response,Integer idAuthor){
        try {
            Author author = this.authorRepository.deleteAuthor(idAuthor);
            if (author == null){
                SendError.notFound(
                    response,
                    "Cannot delete author with id = " + idAuthor + " because it doesn't exist"
                );
            }
            return author;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Author updateAuthor(HttpServletResponse response,Author author){
        try {
            if(testBadRequest(author,true)){
                SendError.badRequest(
                    response,
                    "There is something missed of your request body"
                );
                return null;
            }

            Author oldAuthor= this.authorRepository.updateAuthor(author);
            if (oldAuthor == null){
                SendError.notFound(
                    response,
                    "Cannot update author with id = " + author.getIdAuthor() + " because it doesn't exist"
                );
            }
            return author;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Author updatePartialAuthor(HttpServletResponse response,Author author){
        try {
            if(
                author.getIdAuthor() == null ||
                ( author.getFirstName() == null && author.getName() == null)
            ){
                SendError.badRequest( response, "Verify you body request" );
                return null;
            }

            Author updateAuthor = this.authorRepository.updatePartialAuthor(author);
            if (updateAuthor == null){
                SendError.notFound(
                    response,
                    "Cannot update author with id = " + author.getIdAuthor() + " because it doesn't exist"
                );
            }
            return updateAuthor;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Author createAuthor(HttpServletResponse response,Author author){
        try {
            if(testBadRequest(author,false)){
                SendError.badRequest(
                        response,
                        "There is something missed of your request body"
                );
                return null;
            }
            return this.authorRepository.createAuthor(author);
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }
}