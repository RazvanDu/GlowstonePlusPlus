package net.glowstone.net.message.play.scoreboard;

import com.flowpowered.networking.Message;
import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.NameTagVisibility;

import java.util.List;

@Data
public final class ScoreboardTeamMessage implements Message {

    public final String teamName;
    public final Action action;

    // CREATE and METADATA only
    public final String displayName;
    public final String prefix;
    public final String suffix;
    public final int flags;
    public final NameTagVisibility nametagVisibility;
    public final ChatColor color;

    // CREATE, ADD_, and REMOVE_PLAYERS only
    public final List<String> entries;

    public enum Action {
        CREATE,
        REMOVE,
        UPDATE,
        ADD_PLAYERS,
        REMOVE_PLAYERS
    }

    public ScoreboardTeamMessage(String teamName, Action action, String displayName, String prefix, String suffix, boolean friendlyFire, boolean seeInvisible, NameTagVisibility nametagVisibility, ChatColor color, List<String> entries) {
        this.teamName = teamName;
        this.action = action;
        this.displayName = displayName;
        this.prefix = prefix;
        this.suffix = suffix;
        this.flags = (friendlyFire ? 1 : 0) | (seeInvisible ? 2 : 0);
        this.nametagVisibility = nametagVisibility;
        this.color = color;
        this.entries = entries;
    }

    public static ScoreboardTeamMessage create(String teamName, String displayName, String prefix, String suffix, boolean friendlyFire, boolean seeInvisible, NameTagVisibility nametagVisibility, ChatColor color, List<String> players) {
        return new ScoreboardTeamMessage(teamName, Action.CREATE, displayName, prefix, suffix, friendlyFire, seeInvisible, nametagVisibility, color, players);
    }

    public static ScoreboardTeamMessage remove(String teamName) {
        return new ScoreboardTeamMessage(teamName, Action.REMOVE, null, null, null, false, false, null, ChatColor.RESET, null);
    }

    public static ScoreboardTeamMessage update(String teamName, String displayName, String prefix, String suffix, boolean friendlyFire, boolean seeInvisible, NameTagVisibility nametagVisibility, ChatColor color) {
        return new ScoreboardTeamMessage(teamName, Action.UPDATE, displayName, prefix, suffix, friendlyFire, seeInvisible, nametagVisibility, color, null);
    }

    public static ScoreboardTeamMessage addPlayers(String teamName, List<String> entries) {
        return new ScoreboardTeamMessage(teamName, Action.ADD_PLAYERS, null, null, null, false, false, null, ChatColor.RESET, entries);
    }

    public static ScoreboardTeamMessage removePlayers(String teamName, List<String> entries) {
        return new ScoreboardTeamMessage(teamName, Action.REMOVE_PLAYERS, null, null, null, false, false, null, ChatColor.RESET, entries);
    }
}
