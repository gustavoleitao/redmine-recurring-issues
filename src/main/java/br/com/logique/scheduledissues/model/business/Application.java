package br.com.logique.scheduledissues.model.business;

import org.apache.commons.lang3.LocaleUtils;

import java.time.Clock;
import java.util.Locale;

/**
 * Created by Gustavo on 23/04/2016.
 */
public class Application {

    private static String LOCALE = System.getProperty("app.locale", "pt_BR");

    private static final Application instance = new Application();

    private Application() {
    }

    public static Application getInstance() {
        return instance;
    }

    public Clock getClock() {
        return Clock.systemDefaultZone();
    }

    public Locale getLocale(){
        Locale locale = LocaleUtils.toLocale(LOCALE);
        return locale;
    }

}
