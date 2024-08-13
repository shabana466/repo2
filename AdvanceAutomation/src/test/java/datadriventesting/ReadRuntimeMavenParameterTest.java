package datadriventesting;

import org.testng.annotations.Test;

public class ReadRuntimeMavenParameterTest {
@Test
public void runtimeparameterTest() {
	String url = System.getProperty("url");
	String BROWSER = System.getProperty("browser");
	String USERNAME = System.getProperty("username");
	String PASSWORD = System.getProperty("password");
	System.out.println("Env Data==>URL ==>"+url);
	System.out.println("Env Data==>URL ====>"+url);
	System.out.println("Browser Data==URL ======>"+BROWSER);
	System.out.println("Env Data==>URL ====>"+USERNAME);
	System.out.println("Env Data==>URL ====>"+PASSWORD);
}

}
