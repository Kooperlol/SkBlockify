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

@Name("Hide Views")
@Description("Hides all views to player(s) in the stage's audience. Call asynchronously.")
@Examples({"hide all views from {_player} of {_stage}"})
@Since("1.0.0")
public class EffHideViews extends Effect {
    private Expression<Stage> stage;
    private Expression<Player> player;

    static {
        Skript.registerEffect(EffHideViews.class, "hide all views from %players% (of|in) %stage%");
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
            Blockify.getInstance().getBlockChangeManager().hideViews(stage, p);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Hide views with stage expression: " + stage.toString(event, debug) + " and player expression: " + player.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[1];
        player = (Expression<Player>) expressions[0];
        return (stage != null && player != null);
    }
}
