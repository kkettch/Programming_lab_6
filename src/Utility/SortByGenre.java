package Utility;
import MusicBand.MusicBand;
import java.util.Comparator;
public class SortByGenre implements Comparator<MusicBand> {
    @Override
    public int compare(MusicBand mb1, MusicBand mb2) {
        return Integer.compare(mb2.getGenre().ordinal(), mb1.getGenre().ordinal());
    }
}