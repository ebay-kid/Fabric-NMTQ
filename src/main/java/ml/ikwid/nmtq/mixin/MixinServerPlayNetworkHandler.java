package ml.ikwid.nmtq.mixin;

import ml.ikwid.nmtq.NMTQ;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler {
    @Redirect(method = "onPlayerMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;lengthSquared()D", ordinal = 0))
    public double preLengthSquared(Vec3d instance) {
        return 1000000000;
    }

    @Redirect(method = "onVehicleMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;lengthSquared()D", ordinal = 0))
    public double preVehicleLengthSquared(Vec3d instance) {
        return 1000000000;
    }

    @ModifyConstant(method = "onPlayerMove", constant = @Constant(doubleValue = 0.0625))
    private double modifyPlayerMoveDoubleConst(double original) {
        return 1000000000;
    }

    @ModifyConstant(method = "onVehicleMove", constant = @Constant(doubleValue = 0.0625, ordinal = 1))
    private double modifyVehicleMoveDoubleConst(double original) {
        return 1000000000;
    }
}