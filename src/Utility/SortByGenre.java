package Utility;

import MusicBand.MusicBand;

import java.util.Comparator;

public class SortByGenre implements Comparator<MusicBand> {

    @Override
    public int compare(MusicBand mb1, MusicBand mb2) {
        if (mb1.getGenre().ordinal() == mb2.getGenre().ordinal()) {
            return 0;
        } else if (mb1.getGenre().ordinal() > mb2.getGenre().ordinal()) {
            return -1;
        } else {
            return 1;
        }

    }
}
