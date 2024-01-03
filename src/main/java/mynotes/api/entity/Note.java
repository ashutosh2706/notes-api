package mynotes.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes")
public class Note {
	
	@Id
	@GeneratedValue
	private Long id;
	private String fileName, subject, author, uploadDate, noteID;
	
	public Note() {
		super();
	}

	public Note(Long id, String fileName, String subject, String author, String uploadDate, String noteID) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.subject = subject;
		this.author = author;
		this.uploadDate = uploadDate;
		this.noteID = noteID;
	}

	public String getNoteID() {
		return noteID;
	}

	public void setNoteID(String noteID) {
		this.noteID = noteID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	
}
