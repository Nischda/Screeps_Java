package org.parakoopa.screeps.api;
import org.fnord.screeps.api.MyMain;
import static jsweet.util.Globals.$export;
/**
 * Main entrypoint
 */
public class Main {

    /**
     * This is the main game loop.
     * You have to hook into this method by calling your own loop method
     * eg. new MyGreatImplementation().loop();
     */
    public void loop() {

        MyMain.loop();
    }

    /**
     * Export main class
     * Don't change this!
     * @param args ...
     */
    public static void main(String[] args) {
        $export("main", new Main());
    }

}