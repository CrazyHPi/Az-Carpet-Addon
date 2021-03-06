package azcarpetaddon.mixins;

import azcarpetaddon.AzCarpetAddonSettings;
import azcarpetaddon.helpers.ThrowableSuppression;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(World.class)
public class World_updateSuppressionCrashFixMixin {
    @Inject(method = "updateNeighbor", at = @At(value="INVOKE", target = "Lnet/minecraft/util/crash/CrashReport;create(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"),locals =  LocalCapture.CAPTURE_FAILHARD)
            public void checkUpdateSuppression(BlockPos sourcePos, Block sourceBlock, BlockPos neighborPos, CallbackInfo ci, BlockState state,Throwable throwable){
                if(AzCarpetAddonSettings.updateSuppressionCrashFix && (throwable instanceof ThrowableSuppression || throwable instanceof StackOverflowError)){
                    throw new ThrowableSuppression("Update suppression");
                }
            }


}
