package codes.kooper.skblockify.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.models.View;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

@Name("View In Stage")
@Description("Get a view in a stage")
@Examples("set {_view} to view \"view1\" in stage \"stage1\"")
@Since("1.0.0")
public class ExprViewInStage extends SimpleExpression<View> {
    private Expression<Stage> stage;
    private Expression<String> name;

    static {
        Skript.registerExpression(ExprViewInStage.class, View.class, ExpressionType.SIMPLE, "view %string% in stage %stage%");
    }

    @Override
    protected View @NotNull [] get(@NotNull Event event) {
        return new View[]{Objects.requireNonNull(stage.getSingle(event)).getView(name.getSingle(event))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends View> getReturnType() {
        return View.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "View in stage with name: " + name.toString(event, debug) + " and stage: " + stage.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) expressions[0];
        stage = (Expression<Stage>) expressions[1];
        return (name != null && stage != null);
    }
}
