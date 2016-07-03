package fr.xephi.authme.settings;

import fr.xephi.authme.AuthMe;
import fr.xephi.authme.settings.domain.Property;
import fr.xephi.authme.settings.properties.PluginSettings;
import fr.xephi.authme.settings.properties.RestrictionSettings;
import fr.xephi.authme.settings.properties.SecuritySettings;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * Old settings manager. See {@link NewSetting} for the new manager.
 */
@Deprecated
public final class Settings {

    public static List<String> getUnrestrictedName;
    public static boolean isPermissionCheckEnabled;
    public static boolean isTeleportToSpawnEnabled;
    public static boolean isAllowRestrictedIp;
    public static boolean isSaveQuitLocationEnabled;
    public static boolean protectInventoryBeforeLogInEnabled;
    public static boolean isStopEnabled;
    public static boolean reloadSupport;
    public static boolean noTeleport;
    public static String getUnloggedinGroup;
    public static String unRegisteredGroup;
    public static String getRegisteredGroup;
    public static String defaultWorld;
    public static int getNonActivatedGroup;
    private static FileConfiguration configFile;

    /**
     * Constructor for Settings.
     *
     * @param pl AuthMe
     */
    public Settings(AuthMe pl) {
        configFile = pl.getConfig();
        loadVariables();
    }

    private static void loadVariables() {
        isPermissionCheckEnabled = load(PluginSettings.ENABLE_PERMISSION_CHECK);
        isTeleportToSpawnEnabled = load(RestrictionSettings.TELEPORT_UNAUTHED_TO_SPAWN);
        isAllowRestrictedIp = load(RestrictionSettings.ENABLE_RESTRICTED_USERS);
        isSaveQuitLocationEnabled = load(RestrictionSettings.SAVE_QUIT_LOCATION);
        isRemoveSpeedEnabled = load(RestrictionSettings.REMOVE_SPEED);
        getUnloggedinGroup = load(SecuritySettings.UNLOGGEDIN_GROUP);
        getNonActivatedGroup = configFile.getInt("ExternalBoardOptions.nonActivedUserGroup", -1);
        unRegisteredGroup = configFile.getString("GroupOptions.UnregisteredPlayerGroup", "");
        getUnrestrictedName = load(RestrictionSettings.UNRESTRICTED_NAMES);
        getRegisteredGroup = configFile.getString("GroupOptions.RegisteredPlayerGroup", "");
        isStopEnabled = configFile.getBoolean("Security.SQLProblem.stopServer", true);
        reloadSupport = configFile.getBoolean("Security.ReloadCommand.useReloadCommandSupport", true);
        defaultWorld = configFile.getString("Purge.defaultWorld", "world");
        noTeleport = load(RestrictionSettings.NO_TELEPORT);
    }

    /**
     * Load the value via the new Property setup for temporary support within this old settings manager.
     *
     * @param property The property to load
     * @param <T> The property type
     * @return The config value of the property
     */
    private static <T> T load(Property<T> property) {
        return property.getFromFile(configFile);
    }
}
