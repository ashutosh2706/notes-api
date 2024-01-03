package mynotes.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mynotes.api.entity.Note;
import mynotes.api.entity.User;
import mynotes.api.service.NoteServiceImpl;
import mynotes.api.service.UserServiceImpl;

@SpringBootApplication
@RestController
@EntityScan("mynotes.api.entity")
@RequestMapping("api")
public class Application {
	
	@Autowired
	private NoteServiceImpl noteService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/")
	public String welcomePage() throws IOException {
		return "API is started and running...";
	}
	
	@GetMapping("/all") 
	public List<Note> viewAllNotes() {
		return this.noteService.getAllNotes();
	}
	
	
	@GetMapping("/all/{user}")
	public ResponseEntity<?> viewAllNotesByUser(@PathVariable String user) {
		return this.userService.getUserByUserName(user) == null ? 
				ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) : 
					ResponseEntity.status(HttpStatus.OK).body(this.noteService.getNotesByAuthor(user));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestParam String username, @RequestParam String password) {
		if(this.userService.getUserByUserName(username) == null) {
			User user = new User();
			user.setUserName(username);
			user.setPassword(password);
			return this.userService.saveUser(user) == null ? 
					ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Register Failed") :
						ResponseEntity.status(HttpStatus.OK).body("Registered");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Register Failed");
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
		User retUser = this.userService.getUserByUserName(username);
		if(retUser == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");

		return retUser.getPassword().equals(password) ? 
				ResponseEntity.status(HttpStatus.OK).body("Login Successful") : 
					ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Password");
	}
	
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadNote(@RequestParam("file") MultipartFile file, @RequestParam("filename") String fileName, 
			@RequestParam("subject") String subject, @RequestParam("user") String user) {
		
		
		
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}
	
	
	@PostMapping("/delete/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable String noteID) {
		
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

