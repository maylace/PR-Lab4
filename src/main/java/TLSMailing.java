import jdk.nashorn.internal.runtime.Context;
import org.json.simple.JSONObject;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * Created by Dorin Luca on 27.05.2017.
 */
public class TLSMailing {
    private JSONConfig jsonConfig = new JSONConfig("config.json");
    private JSONObject dataObject = jsonConfig.getObject();

    private String userName = (String) dataObject.get("stmp.username");
    private String password = (String) dataObject.get("stmp.password");
    Properties properties = new Properties();

    public TLSMailing(String userName, String password) {
        this.userName = userName;
        this.password = password;
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    public Session getSession() {
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });
        return session;
    }
}
