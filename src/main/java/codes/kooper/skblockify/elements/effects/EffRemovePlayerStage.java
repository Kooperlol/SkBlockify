package codes.kooper.skblockify.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.Stage;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Remove Player")
@Description("Removes player(s) from a stage")
@Examples("on quit: remove player from stage {_lobby}")
@Since("1.0.0")
public class EffRemovePlayerStage extends Effect {
    private Expression<Stage> stage;
    private Expression<Player> player;

    static {
        Skript.registerEffect(EffRemovePlayerStage.class, "remove %player% from audience of stage %stage%", "remove %player% from stage %stage%'s audience", "remove %player% from stage %stage%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Stage stage = this.stage.getSingle(event);
        Player[] player = this.player.getAll(event);
        if (stage == null) {
            return;
        }
        for (Player p : player) {
            stage.getAudience().removePlayer(p);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Remove player from stage with expression: " + stage.toString(event, debug) + " and player: " + player.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        stage = (Expression<Stage>) expressions[1];
        return (player != null && stage != null);
    }

}
