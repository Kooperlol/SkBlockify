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

@Name("Views In Stage")
@Description("Get views in a stage")
@Examples("set {_views::*} to views in stage {_stage}")
@Since("1.0.3")
public class ExprViewsInStage extends SimpleExpression<View> {
    private Expression<Stage> stage;

    static {
        Skript.registerExpression(ExprViewsInStage.class, View.class, ExpressionType.SIMPLE, "[the] views in stage %stage%");
    }

    @Override
    protected View @NotNull [] get(@NotNull Event event) {
        return Objects.requireNonNull(stage.getSingle(event)).getViews().toArray(new View[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends View> getReturnType() {
        return View.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Views in stage with stage: " + stage.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[0];
        return stage != null;
    }
}
