package pe.sg.springsecurityjwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accesdAdmin() {
        return "Hola, has accedido con el rol Admin";
    }
    
    @GetMapping("/accessUser")
    @PreAuthorize("hasRole('USER')")
    public String accesdUser() {
        return "Hola, has accedido con el rol User";
    }

    @GetMapping("/accessInvited")
    @PreAuthorize("hasRole('INVITED')")
    public String accesdInvited() {
        return "Hola, has accedido con el rol Invited";
    }

}
