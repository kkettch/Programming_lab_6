package common.Message;

import common.MusicBand.MusicBand;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private final static long serialVersionUID = 99765;
    private final String commandName;
    private final String argument;
    private final MusicBand musicBand;

    private final boolean subquery;
    public Request(String commandName, String argument, MusicBand musicBand, boolean subquery) {
        this.commandName = commandName;
        this.argument = argument;
        this.musicBand = musicBand;
        this.subquery = subquery;
    }
    public String getCommandName() {
        return this.commandName;
    }
    public String getArgument() {
        return argument;
    }
    public MusicBand getMusicBand() {
        return musicBand;
    }
    public boolean isSubquery() {
        return subquery;
    }

}
