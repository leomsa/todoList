package com.todolist.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component//toda classe que eu quero que o spring gerencie
public class FilterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // pegar a autenticação (usuário e senha)
        var authorization = request.getHeader("Authorization");
        System.out.println("Authorization");

        var userPasswordEncoded = authorization.substring("Basic".length()).trim();

        byte[] userPasswordDecoded = Base64.getDecoder().decode(userPasswordEncoded);

        var userPasswordDecodeString = new String(userPasswordDecoded);


        //["leomsa", "oInvernoEstaChegando"]
        String[] credencials = userPasswordDecodeString.split(":");
                String username = credencials[0];
                String password = credencials[1];

        System.out.println(username);
        System.out.println(password);
        // validar usuário


        //Validar senha

        //segue viagem
        filterChain.doFilter(request, response);

    }
}
