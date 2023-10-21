package com.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.todolist.User.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component//toda classe que eu quero que o spring gerencie
public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servLetPath = request.getServletPath();

        if (servLetPath.startsWith("/tasks/")) {
            // pegar a autenticação (usuário e senha)
            var authorization = request.getHeader("Authorization");


            var userPasswordEncoded = authorization.substring("Basic".length()).trim();

            byte[] userPasswordDecoded = Base64.getDecoder().decode(userPasswordEncoded);

            var userPasswordDecodeString = new String(userPasswordDecoded);


            //["leomsa", "oInvernoEstaChegando"]
            String[] credencials = userPasswordDecodeString.split(":");
            String username = credencials[0];
            String password = credencials[1];


            // validar usuário
            var user = this.userRepository.findByUserName(username);
            if (user == null) {
                response.sendError(401);
            } else {
                //Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    //segue viagem
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }

            }
        }
        else{
            filterChain.doFilter(request, response);
        }
    }
}
