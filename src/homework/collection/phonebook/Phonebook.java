package homework.collection.phonebook;

import java.util.ArrayList;
import java.util.List;

public class Phonebook {

    private final List<Note> notes = new ArrayList<>();

    public Note find(String name) {
        for (Note note : notes) {
            if (note.getName().equals(name)) {
                return note;
            }
        }

        return null;
    }

    public List<Note> findAll(String name) {
        List<Note> foundNotes = new ArrayList<>();
        for (Note note : notes) {
            if (note.getName().equals(name)) {
                foundNotes.add(note);
            }
        }

        if (foundNotes.isEmpty()) {
            return null;
        }

        return foundNotes;
    }

    public void addNote(Note note) {
        if (note != null) {
            notes.add(note);
        }
    }

    public void addNotes(List<Note> notes) {
        if (notes != null) {
            this.notes.addAll(notes);
        }
    }
}
