package mynotes.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mynotes.api.entity.Note;

@Service
public interface NoteService {
	public Note saveNote(Note note);
	public List<Note> getAllNotes();
	public List<Note> getNotesByAuthor(String author);
	public void deleteNote(String noteID);
}
