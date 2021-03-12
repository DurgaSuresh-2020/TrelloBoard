package starter;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class TrelloEndpoints {

    static EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
    public static final String BOARD = variables.getProperty("trello.base.url")+"/boards";
    public static final String LIST = variables.getProperty("trello.base.url")+"/lists";
    public static final String CARD = variables.getProperty("trello.base.url")+"/cards";
    public static final String KEY = variables.getProperty("trello.key");
    public static final String TOKEN = variables.getProperty("trello.token");
}
