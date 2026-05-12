package com.smartmaintain.identityservice.controllers;

import com.smartmaintain.identityservice.dto.LoginRequest;
import com.smartmaintain.identityservice.dto.LoginResponse;
import com.smartmaintain.identityservice.entities.*;
import com.smartmaintain.identityservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtEncoder jwtEncoder;

    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );

            String role = auth.getAuthorities().iterator().next().getAuthority();

            JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(Instant.now())
                    .subject(auth.getName())
                    .claim("role", role)
                    .expiresAt(Instant.now().plusSeconds(36000))
                    .build();

            String token = jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

            return ResponseEntity.ok(new LoginResponse(token, role));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Les identifications sont erronées");
        }
    }



    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Utilisateur> listUsers() {
        return accountService.getAllUsers();
    }
    @GetMapping("/users/role/{role}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Utilisateur> getUsersByRole(@PathVariable String role) {
        return accountService.getUsersByRole(role);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utilisateur editUser(@PathVariable UUID id, @RequestBody Utilisateur userDetails) {
        return accountService.updateUser(id, userDetails);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable UUID id) {
        accountService.deleteUser(id);
    }



    @PostMapping("/admin")
    public Admin addAdmin(@RequestBody Admin admin) {
        return accountService.saveAdmin(admin);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/client")
    public Client addClient(@RequestBody Client client) {
        return accountService.saveClient(client);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/manager")
    public Manager addManager(@RequestBody Manager manager) {
        return accountService.saveManager(manager);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ingenieur")
    public Ingenieur addIngenieur(@RequestBody Ingenieur ingenieur) {
        return accountService.saveIngenieur(ingenieur);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/operateur")
    public Operateur addOperateur(@RequestBody Operateur operateur) {
        return accountService.saveOperateur(operateur);
    }
}