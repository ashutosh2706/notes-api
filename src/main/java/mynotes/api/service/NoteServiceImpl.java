package mynotes.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mynotes.api.entity.Note;
import mynotes.api.repository.NoteRepository;

@Component
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public Note saveNote(Note note) {
		return noteRepository.save(note);
	}
	
	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}
	
	public List<Note> getNotesByAuthor(String author) {
		return noteRepository.getNoteByAuthor(author);
	}
	
	@Transactional
	public void deleteNote(String noteID) {
		noteRepository.deleteByNoteID(noteID);
	}
	
}
