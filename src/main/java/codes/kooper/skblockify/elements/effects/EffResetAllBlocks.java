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
import codes.kooper.blockify.models.View;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Reset All Blocks in View")
@Description("Reset all blocks in a view to a block from its pattern. Call Asynchronously")
@Examples("reset blocks in view {_view}")
@Since("1.0.0")
public class EffResetAllBlocks extends Effect {
    private Expression<View> view;

    static {
        Skript.registerEffect(EffResetAllBlocks.class, "reset [all] blocks in view %view%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        View view = this.view.getSingle(event);
        if (view == null) {
            return;
        }
        view.resetViewBlocks();
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Reset blocks in view with expression view: " + view.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        view = (Expression<View>) expressions[0];
        return view != null;
    }

}
