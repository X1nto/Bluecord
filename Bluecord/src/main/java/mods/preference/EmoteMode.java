package mods.preference;

public class EmoteMode {
    private static final int BIG_EMOTE_SIZE = 64;
    private static final int NORMAL_EMOTE_SIZE = 48;
    private final String mode;

    public EmoteMode(String str) {
        this.mode = str == null ? "" : str;
    }

    public int getEmoteSizePx() {
        return this.mode.equalsIgnoreCase("Nitro Spoof (Bigger Emotes)") ? 64 : 48;
    }

    public boolean hideLockedEmotes() {
        return this.mode.equalsIgnoreCase("Hide Locked Emotes");
    }

    public boolean isNewNitroSpoof() {
        return this.mode.startsWith("Nitro Spoof");
    }

    public boolean isNitroSpoofEnabled() {
        return this.mode.contains("Nitro Spoof");
    }

    public boolean isOldNitroSpoof() {
        return this.mode.equalsIgnoreCase("Old Nitro Spoof");
    }
}
