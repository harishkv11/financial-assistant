package com.harishverma.financial.assistant.service;

import com.harishverma.financial.assistant.dto.AuthRequest;
import com.harishverma.financial.assistant.dto.AuthResponse;
import com.harishverma.financial.assistant.dto.RegisterRequest;
import com.harishverma.financial.assistant.entity.User;
import com.harishverma.financial.assistant.repository.UserRepository;
import com.harishverma.financial.assistant.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;

    public void register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = jwtUtil.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
