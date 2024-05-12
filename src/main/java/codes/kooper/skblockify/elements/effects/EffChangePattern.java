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
import codes.kooper.blockify.models.Pattern;
import codes.kooper.blockify.models.View;
import codes.kooper.skblockify.utils.Utils;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Change Pattern")
@Description("Change the pattern of a view. The pattern is a string that represents the blocks in the view. The string should be in the format of 'material:percent,material:percent,...'. The total percent should be 1.")
@Examples("change pattern of view {_view} to \"stone:0.5,dirt:0.5\"")
@Since("1.0.0")
public class EffChangePattern extends Effect {
    private Expression<View> view;
    private Expression<String> pattern;

    static {
        Skript.registerEffect(EffChangePattern.class, "change pattern of [view] %view% to %string%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        View view = this.view.getSingle(event);
        String pattern = this.pattern.getSingle(event);
        if (view != null && pattern != null) {
            view.changePattern(new Pattern(Utils.parseMaterialValues(pattern)));
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Change pattern of view with view expression: " + view.toString(event, debug) + " and pattern expression: " + pattern.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        view = (Expression<View>) expressions[0];
        pattern = (Expression<String>) expressions[1];
        return (view != null && pattern != null);
    }

}
