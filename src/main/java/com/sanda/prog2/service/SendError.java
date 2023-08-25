package com.sanda.prog2.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SendError {
    public static void internalServerError(HttpServletResponse response){
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void notFound(HttpServletResponse response,String message){
        try {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,message);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void badRequest(HttpServletResponse response,String message){
        try {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,message);
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

}

