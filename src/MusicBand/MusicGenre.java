package MusicBand;

public enum MusicGenre {
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

