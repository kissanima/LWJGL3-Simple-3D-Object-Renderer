# Simple 3D Object Renderer with LWJGL and JAWT

This project is a simple 3D object renderer using LWJGL (Lightweight Java Game Library) with a Swing-based GUI using JAWT (Java AWT). It supports rendering basic shapes such as cubes, spheres, and pyramids.

## Features

- Render 3D shapes: Cube, Sphere, Pyramid
- Basic lighting for realistic shading
- Interactive control panel for switching shapes
- Keyboard controls for moving and rotating objects

## Prerequisites

- Java Development Kit (JDK) 11 or higher
-LWJGL3
- Visual Studio Code
  - Extensions:
    - Java Extension Pack

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/your-username/simple-3d-object-renderer.git
cd simple-3d-object-renderer
```

## Open the Project in Visual Studio Code
Open Visual Studio Code.
Click on File -> Open Folder... and select the simple-3d-object-renderer folder.

## Import Libraries
Ensure that all the required libraries from the lib folder are added to the project


## Running the Application
In Visual Studio Code, press F5


## Project Structure
- Launcher.java: Entry point for the application. Sets up the GUI and initializes the WindowManager.
- WindowManager.java: Manages the OpenGL context, handles input, and renders shapes.
- Shapes.java: Contains methods for rendering different 3D shapes.
- InputHandler.java: Handles user input for interacting with the 3D scene.


## Keyboard Controls
- Move Object:
- W: Move up
- S: Move down
- A: Move left
- D: Move right

## Rotate Object:
- Arrow Up: Rotate upwards
- Arrow Down: Rotate downwards
- Arrow Left: Rotate left
- Arrow Right: Rotate right