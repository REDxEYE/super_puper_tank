package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mygdx.game.main.Main;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class GameStart {
    private static final Path CONFIG_PATH = Path.of("./config.json");

    public static Lwjgl3ApplicationConfiguration config;
    public static int viewportWidth = 1280;
    public static int viewportHeight = 720;

    public static void main(String[] arg) {
        config = new Lwjgl3ApplicationConfiguration();
        loadConfig();
        config.setWindowedMode(viewportWidth, viewportHeight - 80);
        config.useVsync(true);
//		config.title = "Title";
//		config.useGL20 = true;
//		config.height = 640;
        config.setForegroundFPS(60);
        config.setTitle("Game");
        config.setWindowIcon("image/player/tower_player_1.png");
        new Lwjgl3Application(new Main(viewportWidth, viewportHeight - 80, 60), config);
    }

    private static void loadConfig() {
        Gson gson = new Gson();
        if (Files.exists(CONFIG_PATH)) {
            try (FileReader reader = new FileReader(CONFIG_PATH.toFile())) {
                JsonObject config = gson.fromJson(reader, JsonObject.class);
                viewportWidth = config.get("viewportWidth").getAsInt();
                viewportHeight = config.get("viewportHeight").getAsInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            saveConfig();
        }

    }

    // Create method to save config
    public static void saveConfig() {
        Gson gson = new Gson();
        JsonObject config = new JsonObject();
        config.addProperty("viewportWidth", viewportWidth);
        config.addProperty("viewportHeight", viewportHeight);
        try (FileWriter writer = new FileWriter(CONFIG_PATH.toFile())) {
            gson.toJson(config, writer);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
