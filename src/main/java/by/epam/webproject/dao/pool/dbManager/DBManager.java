package by.epam.webproject.dao.pool.dbManager;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DBManager {

    /**
     * Represents name of JDBC Driver of database used
     */
    public static final String DRIVER = "db.driver";

    public static final String URL = "db.url";

    public static final String USER = "db.user";

    public static final String PASSWORD = "db.password";

    /**
     * Represents maximum amount of connection which are created and used in web app
     */
    public static final String POOL_SIZE = "db.poolSize";

    private static final String RESOURCE_NAME = "database";

    private static final ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_NAME);


    /**
     * Returns database property value with specified key
     * @param key of the property, recommended to use constants.
     * @return value of property with the specified key
     */
    public static String getValue(String key) {

        String result = null;

        if (key != null) {
            try {
                result = bundle.getString(key);
            } catch (MissingResourceException e) {
                System.out.println("Missing exc"); // may be better to throw an exception , because return null can confuse user.
            }
        }
        return result;
    }
}
