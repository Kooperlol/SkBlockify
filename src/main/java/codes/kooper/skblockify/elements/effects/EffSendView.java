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

@Name("Send View")
@Description("Send a view to player(s) in the stage's audience. Call asynchronously.")
@Examples({"send view {_view} to {_player}", "send view {_view} to all players in {_stage}'s audience"})
@Since("1.0.0")
public class EffSendView extends Effect {
    private Expression<View> view;
    private Expression<Player> player;

    static {
        Skript.registerEffect(EffSendView.class, "send view %view% to %players%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        View view = this.view.getSingle(event);
        Player[] player = this.player.getAll(event);
        if (view == null) {
            return;
        }
        for (Player p : player) {
            if (!view.getStage().getAudience().getPlayers().contains(p.getUniqueId())) continue;
            Blockify.getInstance().getBlockChangeManager().sendView(p, view);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Send view with view expression: " + view.toString(event, debug) + " and player expression: " + player.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        view = (Expression<View>) expressions[0];
        player = (Expression<Player>) expressions[1];
        return (view != null && player != null);
    }
}
