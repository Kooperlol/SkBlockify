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
import codes.kooper.blockify.Blockify;
import codes.kooper.blockify.models.Stage;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Send Views")
@Description("Sends all views to player(s) in the stage's audience.")
@Examples({"send all views to {_player} of {_stage}"})
@Since("1.0.0")
public class EffSendViews extends Effect {
    private Expression<Stage> stage;
    private Expression<Player> player;

    static {
        Skript.registerEffect(EffSendViews.class, "send all views to %players% (of|in) %stage%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Stage stage = this.stage.getSingle(event);
        Player[] player = this.player.getAll(event);
        if (stage == null) {
            return;
        }
        for (Player p : player) {
            if (!stage.getAudience().getPlayers().contains(p)) continue;
            Blockify.getInstance().getBlockChangeManager().sendViews(stage, p);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Send view with stage expression: " + stage.toString(event, debug) + " and player expression: " + player.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[1];
        player = (Expression<Player>) expressions[0];
        return (stage != null && player != null);
    }
}
