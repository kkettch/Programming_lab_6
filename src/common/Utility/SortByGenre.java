package common.Utility;
import common.MusicBand.MusicBand;
import java.util.Comparator;

/**
 * Класс реализует сортировка элементов common.MusicBand по полю genre с помощью класса Comparator и метода compare()
 * @author maria
 */
public class SortByGenre implements Comparator<MusicBand> {
    @Override
    public int compare(MusicBand mb1, MusicBand mb2) {
        return mb2.getGenre().ordinal() - mb1.getGenre().ordinal();
    }
}