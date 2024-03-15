package somePackage;

import somePackage.Journal;

import java.util.ArrayList;
import java.util.List;

public class JournalCatalogue {
    List<Journal> journalList = new ArrayList<>();
    private static JournalCatalogue journalCatalogue;

    private JournalCatalogue() {
        for (int i = 0; i < 20; i++) {
            journalList.add(new Journal("Name" + i, "Genre " + (Math.floorMod(i, 5)), 10));
        }
    }

    public static JournalCatalogue getJournalCatalogue() {
        if (journalCatalogue == null) {
            journalCatalogue = new JournalCatalogue();
        }

        return journalCatalogue;
    }
    public void addJournal(Journal journal){
        journalList.add(journal);
    }

    public List<Journal> getJournalList(){
        return this.journalList;
    }

    public Journal findJournalByName(String name) {
        return this.getJournalList().stream().filter(e -> e.getName().equals(name) ).findFirst().get();
    }

}
