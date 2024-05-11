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

@Name("Delete View")
@Description("Deletes a view")
@Examples("delete view {_view}")
@Since("1.0.0")
public class EffDeleteView extends Effect {
    private Expression<View> view;

    static {
        Skript.registerEffect(EffDeleteView.class, "(delete|remove) [blockify] view %view%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        View viewSingle = view.getSingle(event);
        if (viewSingle == null) return;
        viewSingle.getStage().removeView(viewSingle);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "Delete view " + view.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        view = (Expression<View>) expressions[0];
        return (view != null);
    }
}
