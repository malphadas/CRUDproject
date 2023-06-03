package matheus.io.projeto2.Controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import matheus.io.projeto2.model.Usuario;
import matheus.io.projeto2.repo.UserRepository;

/* funcionalidades CRUD
 * create
 * read
 * update
 * delete
 */

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository UserRepo;
	
	@GetMapping
	public List<Usuario> list(){
		return UserRepo.findAll();		
	}
	
	@PostMapping
	public Usuario addUser(@RequestBody Usuario myUser) {
		return UserRepo.save(myUser);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Usuario> changeInfo(@PathVariable("id")Long userId,@RequestBody Usuario updatedUsuario) {
		Optional<Usuario> optionalUser = UserRepo.findById(userId);
	    if (optionalUser.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    Usuario existingUsuario = optionalUser.get();
	    
	    if (updatedUsuario.getName() != null) {
	        existingUsuario.setName(updatedUsuario.getName());
	    }
	    if (!Objects.equals(updatedUsuario.getAge(), null)) {
	        existingUsuario.setAge(updatedUsuario.getAge());
	    }
	    if (updatedUsuario.getFavColor() != null) {
	        existingUsuario.setFavColor(updatedUsuario.getFavColor());
	    }

	    UserRepo.save(existingUsuario);

	    return ResponseEntity.ok(existingUsuario);	
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deleteUser(@PathVariable("id") Long id){
	    Optional<Usuario> optionalUser = UserRepo.findById(id);
	    
		if (optionalUser.isPresent()) {
	        UserRepo.delete(optionalUser.get());
	        return ResponseEntity.ok().build(); // Successful delete, return 200 OK
	    } else {
	        return ResponseEntity.notFound().build(); // User with the specified ID not found, return 404 Not Found
	    }
		
	}
	
	
	
}