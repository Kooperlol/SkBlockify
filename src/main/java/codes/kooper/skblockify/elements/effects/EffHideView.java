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
import codes.kooper.blockify.models.View;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Hide View")
@Description("Hides a view from player(s) in the stage's audience.")
@Examples({"hide view {_view} from {_player}", "hide view {_view} from all players in {_stage}'s audience"})
@Since("1.0.0")
public class EffHideView extends Effect {
    private Expression<View> view;
    private Expression<Player> player;

    static {
        Skript.registerEffect(EffHideView.class, "hide view %view% from %players%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        View view = this.view.getSingle(event);
        Player[] player = this.player.getAll(event);
        if (view == null) {
            return;
        }
        for (Player p : player) {
            if (!view.getStage().getAudience().getPlayers().contains(p)) continue;
            Blockify.getInstance().getBlockChangeManager().hideView(p, view);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Hide view with view expression: " + view.toString(event, debug) + " and player expression: " + player.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        view = (Expression<View>) expressions[0];
        player = (Expression<Player>) expressions[1];
        return (view != null && player != null);
    }
}
