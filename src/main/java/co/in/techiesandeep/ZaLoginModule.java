package co.in.techiesandeep;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class ZaLoginModule implements LoginModule {

    public static final String TEST_USERNAME = "zauser";
    public static final String TEST_PASSWORD = "password";
    private boolean authenticationSuccess = false;
    private CallbackHandler callbackHandler = null;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("UserName:");
        callbacks[1] = new PasswordCallback("Password:", false);
        try {
            callbackHandler.handle(callbacks);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        String name = ((NameCallback)callbacks[0]).getName();
        String password = new String(((PasswordCallback)callbacks[1]).getPassword());

        if (TEST_USERNAME.equals(name) && TEST_PASSWORD.equals(password)) {
            System.out.println("authentication success...");
            authenticationSuccess = true;
        } else {
            authenticationSuccess = false;
            throw new FailedLoginException("authentication failure... ");
        }

        return authenticationSuccess;
    }

    @Override
    public boolean commit() throws LoginException {
        return authenticationSuccess;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
