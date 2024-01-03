package mynotes.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mynotes.api.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
	public List<Note> getNoteByAuthor(String author);
	public void deleteByNoteID(String noteID);
}
