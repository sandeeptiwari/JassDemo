package co.in.techiesandeep;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class Driver {

    public static void main(String...args) {
        System.setProperty("java.security.auth.login.config", "jaastut.config");
        LoginContext ctx = null;
        try {
             ctx = new LoginContext("JaasTutorial", new ZaCallbackHandler());
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(0);
        }

        while (true) {
            try {
                ctx.login();
                System.exit(0);
            } catch (LoginException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
