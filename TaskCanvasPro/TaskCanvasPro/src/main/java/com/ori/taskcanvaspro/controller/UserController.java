package com.ori.taskcanvaspro.controller;

import com.ori.taskcanvaspro.dto.LoginDTO;
import com.ori.taskcanvaspro.dto.LoginResponseDTO;
import com.ori.taskcanvaspro.dto.UserCreateDTO;
import com.ori.taskcanvaspro.dto.UserResponseDTO;
import com.ori.taskcanvaspro.service.UserService;
import com.ori.taskcanvaspro.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateDTO createUser){
        UserResponseDTO user = userService.register(createUser);
        var uri = UriComponentsBuilder.fromPath("/api/v1/users/:id").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping("/login")
    @CrossOrigin(origins =  "*")
    public ResponseEntity<?> login(@RequestBody LoginDTO login){

        try {
            String token = null;
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmail(), login.getPassword()));
            if (authentication.isAuthenticated()) {
                token = jwtService.generateToken(login.getEmail());
            } else {
                throw new UsernameNotFoundException("invalid user request !");
            }
            UserResponseDTO user = userService.getByEmail(login.getEmail());
            LoginResponseDTO resp = new LoginResponseDTO(user, token);
            return ResponseEntity.ok(resp);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}") // http://localhost:8080/api/v1/users/1589 -> userId = 1589
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable long userId){
        UserResponseDTO user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }




}
