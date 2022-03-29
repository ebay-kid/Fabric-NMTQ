package ml.ikwid.nmtq.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler {
    @Redirect(
            method = "onPlayerMove",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/Vec3d;lengthSquared()D",
                    ordinal = 0
            )
    )
    public double preLengthSquared(Vec3d instance) {
        return 1000000000;
    }

    @Redirect(
            method = "onVehicleMove",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/Vec3d;lengthSquared()D",
                    ordinal = 0
            )
    )
    public double preVehicleLengthSquared(Vec3d instance) {
        return 1000000000;
    }
}