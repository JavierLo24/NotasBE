package com.practice.notasbe.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.notasbe.entities.UserE;
import com.practice.notasbe.repositories.UserRepository;
import com.practice.notasbe.shared.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserRepository userRepository;

    public AuthenticationFilter(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        UserE user = null;
        try{
            user = new ObjectMapper().readValue(request.getInputStream(), UserE.class);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        return getAuthenticationManager().authenticate(auth);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException{

        UserDTO user = new ModelMapper()
                .map(userRepository.findUserByEmail(((User) authResult.getPrincipal()).getUsername()).get(), UserDTO.class);

        Map<String, Object> httpResponse = new HashMap<>();

        httpResponse.put("message", "Successful authentication done");
        httpResponse.put("user", user);

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);


    }


}
