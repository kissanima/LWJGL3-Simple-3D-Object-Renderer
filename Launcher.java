

public class Launcher {
   
	private static WindowManager windowManager;

	public static void main(String[] args) {
		windowManager = new WindowManager("Simple 3D Object Renderer", 1600, 900, false);
	}

	public static WindowManager GetWindow() {
		return windowManager;
	}
}