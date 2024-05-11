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
import codes.kooper.blockify.models.View;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

@Name("Lowest Block of View")
@Description("Get the lowest block of a view at a specific x and z coordinate. Returns null if no block is found.")
@Examples("set {_loc} to lowest block of {_view} at x-coord 5 and z-coord 5")
@Since("1.0.0")
public class ExprLowestBlock extends SimpleExpression<Location> {
    private Expression<View> view;
    private Expression<Integer> x, z;

    static {
        Skript.registerExpression(ExprLowestBlock.class, Location.class, ExpressionType.SIMPLE, "lowest block of %view% at [x|x-coord|x-coordinate] %integer% and [z|z-coord|z-coordinate] %integer%");
    }

    @Override
    protected Location @NotNull [] get(@NotNull Event event) {
        View view = this.view.getSingle(event);
        Integer x = this.x.getSingle(event);
        Integer z = this.z.getSingle(event);
        if (view == null || x == null || z == null) {
            return new Location[0];
        }
        return new Location[]{view.getLowestBlock(x, z) == null ? null : Objects.requireNonNull(view.getLowestBlock(x, z).toLocation(view.getStage().getWorld()))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "Lowest block of view with view expression " + view.toString(event, b) + " at x " + x.toString(event, b) + " and z " + z.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        view = (Expression<View>) expressions[0];
        x = (Expression<Integer>) expressions[1];
        z = (Expression<Integer>) expressions[2];
        return view != null;
    }
}
