package com.vkrepsky.cursomc.resources;

import com.vkrepsky.cursomc.dto.EmailDTO;
import com.vkrepsky.cursomc.security.JWTUtil;
import com.vkrepsky.cursomc.security.UserSS;
import com.vkrepsky.cursomc.services.AuthService;
import com.vkrepsky.cursomc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService service;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS user = UserService.getCurrentAuthenticatedUser();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody EmailDTO objDto){
        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
