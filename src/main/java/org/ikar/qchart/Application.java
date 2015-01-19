package org.ikar.qchart;

public class Application {

	public static void main(String[] args) {
		try {
			EmbeddedWebServer server = new EmbeddedWebServer(getPort(args));
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static int getPort(String[] args) {
		return 8080;
	}
}
