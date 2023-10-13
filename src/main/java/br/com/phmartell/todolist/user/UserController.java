package br.com.phmartell.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Modificadores
 * public
 * private
 * protected
 * 
*/

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel UserModel){
        var usuario = this.userRepository.findByUsername(UserModel.getUsername());
        
        if(usuario != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já existe");
        }

        var userCreate = this.userRepository.save(UserModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreate);
    }
}
