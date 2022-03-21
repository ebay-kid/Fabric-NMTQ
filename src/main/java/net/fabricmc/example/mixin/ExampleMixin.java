package net.fabricmc.example.mixin;

import net.fabricmc.example.ExampleMod;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;
import java.util.Set;

@Mixin(ServerPlayNetworkHandler.class)
public class ExampleMixin {
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