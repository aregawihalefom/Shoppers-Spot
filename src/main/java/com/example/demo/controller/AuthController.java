package com.example.demo.controller;


import com.example.demo.config.Constants;
import com.example.demo.domain.ERole;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.Response;
import com.example.demo.dto.authDTOs.AuthenticationRequest;
import com.example.demo.dto.authDTOs.AuthenticationResponse;
import com.example.demo.dto.authDTOs.MessageResponse;
import com.example.demo.dto.authDTOs.RegisterUserRequest;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/validate")
    public boolean checkTokenValidity(@RequestBody String token) {
        return false;
    }

    @PostMapping("/signin")
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Response> createAuthneticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (BadCredentialsException e) {

            ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("user", authenticationRequest))
                            .message("Exception " + e.getMessage())
                            .status(HttpStatus.BAD_REQUEST)
                            .developerMessage("Api by Aregawi")
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        User user = userRepository.findByUsername(userDetails.getUsername()).get();

        AuthenticationResponse response = new AuthenticationResponse(jwt,
                userDetails.getUsername(),
                roles,
                user.getId()
        );

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("user", response))
                        .message("User Logged in with successfully")
                        .status(OK)
                        .developerMessage("Api by Aregawi")
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {

        if (userRepository.existsByUsername(registerUserRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(registerUserRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        // Create user and save it to the database
        User user = new User();
        user.setEmail(registerUserRequest.getEmail());
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(encoder.encode(registerUserRequest.getPassword()));
        user.setFirstName(registerUserRequest.getFirstName());
        user.setMiddleName(registerUserRequest.getMiddleName());
        user.setLastName(registerUserRequest.getLastName());
        user.setPoints(Constants.NEW_BUYER_BONUS);

        Set<String> strRoles = registerUserRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.BUYER.name())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {

                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "seller":
                        Role sellerRole = roleRepository.findByName(ERole.SELLER.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(sellerRole);

                        break;
                    default:
                        Role buyerRole = roleRepository.findByName(ERole.BUYER.name())
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(buyerRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(registerUserRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        Set<String> realRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        AuthenticationResponse response = new AuthenticationResponse(jwt,
                userDetails.getUsername(),
                realRoles,
                user.getId()
        );

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("user", response))
                        .message("User Successfully registered")
                        .status(OK)
                        .developerMessage("Api by Aregawi")
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
