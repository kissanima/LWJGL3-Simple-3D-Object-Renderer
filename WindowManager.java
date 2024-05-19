import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class WindowManager {
    public static final float FOV = (float) Math.toRadians(60);
    public static final float Z_NEAR = 0.01f;
    public static final float Z_FAR = 1000f;

    private final String title;

    //window
    private int width, height;
    private long window;

    private boolean resize, vSync;

    // private final Matrix4f projectionMatrix;
	private InputHandler inputHandler;

    public WindowManager(String title, int width, int height, boolean vsync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vsync;
        // projectionMatrix = new Matrix4f();

        init();
        loop();

        // Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
    }

    public void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// window config support
		boolean maximised = false;
		if(width == 0 || height == 0) {
			width = 100;
			height = 100;
			glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
			maximised = true;
		}

		// Create the window
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");
		
		// Initialize UserInputHandler
		inputHandler = new InputHandler(window);

		// // Setup a key callback. It will be called every time a key is pressed, repeated or released.
		// glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
		// 	if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
		// 		glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		// });

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
	}

    private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();
        
        
		// Set the clear color
		glClearColor(0.1f, 0.1f, 0.1f, 1.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			// Apply transformations based on user input
            glLoadIdentity();
            glTranslatef(inputHandler.getTranslationX(), inputHandler.getTranslationY(), inputHandler.getTranslationZ());
            glRotatef(inputHandler.getRotationX(), 1.0f, 0.0f, 0.0f);
            glRotatef(inputHandler.getRotationY(), 0.0f, 1.0f, 0.0f);


			//start render
			Shapes.renderCube(0.0f, 0.0f, 0.0f, 0.5f, new float[]{1.0f, 0.0f, 0.0f});

			glfwSwapBuffers(window); // swap the color buffers
			
			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();

			
		}
	}
}
