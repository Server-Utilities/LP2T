package tv.quaint;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tv.quaint.tickables.SecondTicker;

public class LuckPermsToTeams implements DedicatedServerModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("LP2T");
	public static MinecraftServer SERVER;
	public static SecondTicker secondTicker;

	@Override
	public void onInitializeServer() {
		secondTicker = new SecondTicker(20);

		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			SERVER = server;
		});

		ServerTickEvents.START_SERVER_TICK.register(server -> {
			secondTicker.tick();
		});

		LOGGER.info("Initialized!");
	}
}
