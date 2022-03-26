package tv.quaint.tickables;

import net.minecraft.server.network.ServerPlayerEntity;
import tv.quaint.utils.MainUtils;

public class SecondTicker {
    public int countdown;
    public int reset;

    public SecondTicker(int reset) {
        this.countdown = 0;
        this.reset = reset;
    }

    public void tick() {
        if (countdown <= 0) {
            countdown = reset;
            for (ServerPlayerEntity player : MainUtils.getOnlinePlayers()) {
                MainUtils.sync(player);
            }
        }

        countdown --;
    }
}
