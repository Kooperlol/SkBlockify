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

@Name("Reset Mining Speed")
@Description("Reset the mining speed of a player for a stage.")
@Examples({"reset the mining speed of all players for stage {_stage}", "reset the mining speed of player for stage {_stage}"})
@Since("1.0.0")
public class EffResetMiningSpeed extends Effect {
    private Expression<Player> player;
    private Expression<Stage> stage;

    static {
        Skript.registerEffect(EffResetMiningSpeed.class, "reset [the] mining speed of %players% for stage %stage%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Player[] player = this.player.getAll(event);
        Stage stage = this.stage.getSingle(event);
        if (stage == null) {
            return;
        }
        for (Player p : player) {
            stage.getAudience().resetMiningSpeed(p);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Reset mining speed with player expression: " + player.toString(event, debug) + " and stage expression: " + stage.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        stage = (Expression<Stage>) expressions[1];
        return (player != null && stage != null);
    }

}
