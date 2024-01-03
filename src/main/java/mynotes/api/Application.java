package mynotes.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import mynotes.api.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import mynotes.api.entity.Note;
import mynotes.api.entity.User;
import mynotes.api.service.NoteServiceImpl;
import mynotes.api.service.UserServiceImpl;

@SpringBootApplication
@RestController
@EntityScan("mynotes.api.entity")
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:3000")
public class Application {
	
	@Autowired
	private NoteServiceImpl noteService;
	
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private DownloadService downloadService;

	public static final String UPLOAD_DIR = "uploads";

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

	@GetMapping("/download/{noteId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String noteId) {
		Resource resource = this.downloadService.loadFileAsResource(this.noteService.getNoteByNoteId(noteId).getFileName());
		
		// update the download_cnt
		this.noteService.updateDownloadCount(noteId);
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.header("Access-Control-Expose-Headers", "Content-Disposition")	// required to expose the content-disposition header
				.body(resource);
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

		if(retUser == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
		}

		return retUser.getPassword().equals(password) ?
				ResponseEntity.status(HttpStatus.OK).body("Login Successful") :
					ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Password");
	}
	
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadNote(@RequestParam("file") MultipartFile file,
			@RequestParam("subject") String subject, @RequestParam("user") String user) {

		Note note = new Note();
		note.setAuthor(user);
		note.setFileName(file.getOriginalFilename());
		note.setSubject(subject);
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		note.setUploadDate(currentDate.format(formatter));
		note.setNoteID(UUID.randomUUID().toString());

		if (!file.isEmpty()) {
			try {
				String folderPath = UPLOAD_DIR + File.separator;
				File folder = new File(folderPath);
				if (!folder.exists()) folder.mkdirs();

				String filePath = folderPath + file.getOriginalFilename();
				OutputStream outputStream = new FileOutputStream(filePath);
				outputStream.write(file.getBytes());
				outputStream.close();
				long sz = file.getSize();
				double fileSizeInMegabytes = (double) sz / (1024 * 1024);
				String fileSize = String.format("%.2f MB", fileSizeInMegabytes);
				note.setSize(fileSize);
				this.noteService.saveNote(note);
				System.out.println("Note saved");

				return ResponseEntity.status(HttpStatus.OK).body("OK");

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred in backend api");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty file received");
		}
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteNote(@RequestParam("noteId") String noteID) {
		
		String filePath = UPLOAD_DIR + "/" + this.noteService.getNoteByNoteId(noteID).getFileName();
		
		try {
			Path path = Paths.get(filePath);
			Files.delete(path);
			this.noteService.deleteNote(noteID);
			return ResponseEntity.status(HttpStatus.OK).body("OK");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred at backend api");
		}
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

