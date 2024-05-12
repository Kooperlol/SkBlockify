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
import codes.kooper.blockify.types.BlockifyPosition;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Blocks At Location In View")
@Description("Get the block at a location in a view.")
@Examples("set {_block} to the block at player's location in view {_view}")
@Since("1.0.0")
public class ExprBlocksAtLocInView extends SimpleExpression<BlockData> {
    private SimpleExpression<View> view;
    private SimpleExpression<Location> location;

    static {
        Skript.registerExpression(ExprBlocksAtLocInView.class, BlockData.class, ExpressionType.SIMPLE, "[get] [the] block at %location% in view %view%");
    }

    @Override
    protected BlockData @NotNull [] get(@NotNull Event event) {
        View view = this.view.getSingle(event);
        if (view == null) return new BlockData[0];
        Location location = this.location.getSingle(event);
        if (location == null) return new BlockData[0];
        return new BlockData[]{view.getBlock(BlockifyPosition.fromLocation(location))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends BlockData> getReturnType() {
        return BlockData.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "Blocks in view with view expression " + view.toString(event, b) + " at location " + location.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        location = (SimpleExpression<Location>) expressions[0];
        view = (SimpleExpression<View>) expressions[1];
        return (location != null && view != null);
    }

}
