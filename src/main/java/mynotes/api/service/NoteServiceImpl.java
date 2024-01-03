package mynotes.api.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Note getNoteByNoteId(String noteID) {
		return noteRepository.getNoteByNoteID(noteID);
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
	
	public Note updateDownloadCount(String noteId) {
		Note note = noteRepository.getNoteByNoteID(noteId);
		note.setDownload_count(note.getDownload_count()+1);
		return noteRepository.save(note);
	}
	
}
