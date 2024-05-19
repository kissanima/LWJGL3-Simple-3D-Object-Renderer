import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.opengl.GL11;

public class Shapes {
    
    public static void renderCube(float x, float y, float z, float size, float[] color) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, z);
        GL11.glColor3f(color[0], color[1], color[2]);
        GL11.glBegin(GL11.GL_QUADS);
        // Define vertices for each face of the cube
        // Front face
        GL11.glVertex3f(-size, -size, size);
        GL11.glVertex3f(size, -size, size);
        GL11.glVertex3f(size, size, size);
        GL11.glVertex3f(-size, size, size);
        // Other faces here...
        GL11.glEnd();
        GL11.glPopMatrix();
    }
}
