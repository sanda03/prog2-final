package com.sanda.prog2.service;

import com.sanda.prog2.model.Category;
import com.sanda.prog2.repository.CategoryRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository serviceRepository;

    private boolean testBadRequest(Category service,boolean testId){
        if(testId && service.getIdCategory() == null)
            return true;
        return service.getName() == null;
    }
    public List<Category> getAllCategory(HttpServletResponse response){
        try {
            return this.serviceRepository.getALlCategory();
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Category getCategoryById(HttpServletResponse response,Integer idCategory){
        try {
            Category service = this.serviceRepository.getCategoryById(idCategory);
            if (service == null){
                SendError.notFound(
                        response,
                        "There is no service with id = " + idCategory
                );
            }
            return service;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Category deleteCategory(HttpServletResponse response,Integer idCategory){
        try {
            Category service = this.serviceRepository.deleteCategory(idCategory);
            if (service == null){
                SendError.notFound(
                        response,
                        "Cannot delete service with id = " + idCategory + " because it doesn't exist"
                );
            }
            return service;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Category updateCategory(HttpServletResponse response,Category service){
        try {
            if(testBadRequest(service,true)){
                SendError.badRequest(
                        response,
                        "There is something missed of your request body"
                );
                return null;
            }

            Category oldCategory= this.serviceRepository.updateCategory(service);
            if (oldCategory == null){
                SendError.notFound(
                        response,
                        "Cannot update service with id = " + service.getIdCategory() + " because it doesn't exist"
                );
            }
            return service;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Category createCategory(HttpServletResponse response,Category service){
        try {
            if(testBadRequest(service,false)){
                SendError.badRequest(
                        response,
                        "There is something missed of your request body"
                );
                return null;
            }
            return this.serviceRepository.createCategory(service);
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }
}
