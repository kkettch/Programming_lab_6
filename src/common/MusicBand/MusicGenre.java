package common.MusicBand;

import java.io.Serializable;

/**
 * Шаблон класса enum MusicGenre. Содержит перечисление возможных музыкальных жанров
 * Содержит метод для получения списка музыкальных жанров
 * @author maria
 */
public enum MusicGenre implements Serializable {
    PSYCHEDELIC_ROCK,
    POP,
    MATH_ROCK,
    BRIT_POP;
    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (MusicGenre musicGenre : values()) {
            nameList.append(musicGenre.name()).append(" \n");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}