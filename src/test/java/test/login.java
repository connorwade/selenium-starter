package test;

import base.TestBase;
import org.testng.annotations.Test;

public class login extends TestBase {

    @Test public void Login() {
        homePage().login();
    }
}
