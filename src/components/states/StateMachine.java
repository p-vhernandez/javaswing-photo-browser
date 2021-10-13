package components.states;

import components.drawing.Drawing;
import components.photo.PhotoComponent;
import fr.lri.swingstates.sm.JStateMachine;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StateMachine extends JStateMachine {

    private final PhotoComponent controller;

    public StateMachine(PhotoComponent controller) {
        this.controller = controller;
    }

    public State viewing = new State("viewing") {
        final Transition doubleClick = new Click(BUTTON1, "=> annotating") {
            @Override
            public boolean guard() {
                return getMouseEvent().getClickCount() == 2;
            }

            @Override
            public void action() {
                controller.toggleAnnotations();
            }
        };
    };

    public State annotating = new State("annotating") {
        final Transition doubleClick = new Click(BUTTON1, "=> viewing") {
            @Override
            public boolean guard() {
                return getMouseEvent().getClickCount() == 2;
            }

            @Override
            public void action() {
                controller.toggleAnnotations();
            }
        };

        final Transition drawingClick = new Click(BUTTON1, "=> annotating") {
            @Override
            public boolean guard() {
                return getMouseEvent().getClickCount() == 1
                        && controller.getDrawingAt(controller.getMousePosition()) != null;
            }

            @Override
            public void action() {
                Drawing drawing = controller.getDrawingAt(controller.getMousePosition());
                controller.deselectAllDrawings();
                controller.selectDrawing(drawing);
            }
        };

        final Transition singleClick = new Click(BUTTON1, "=> annotating") {
            @Override
            public boolean guard() {
                return getMouseEvent().getClickCount() == 1
                        && controller.getDrawingAt(controller.getMousePosition()) == null;
            }

            @Override
            public void action() {
                controller.deselectAllDrawings();
                controller.startTyping(controller.getMousePosition());
            }
        };

        final Transition drag = new Drag(BUTTON1, "=> drag") {
            @Override
            public boolean guard() {
                return controller.hasDrawingsSelected();
            }
        };

        final Transition draw = new Drag(BUTTON1, "=> annotating") {
            @Override
            public void action() {
                controller.draw(controller.getMousePosition());
            }
        };

        final Transition stopDrawing = new Release() {
            @Override
            public void action() {
                controller.stopDrawing();
            }
        };

        final Transition shiftPressed = new KeyPress(KeyEvent.VK_SHIFT, "=> annotatingShiftDown") {};

        final Transition deleteCharacter = new KeyPress() {
            @Override
            public boolean guard() {
                return getChar() == '\u0008' && controller.isFocusable();
            }

            @Override
            public void action() {
                controller.deleteCharacter();
            }
        };

        final Transition addCharacter = new KeyPress() {
            @Override
            public boolean guard() {
                // FIXME: search for better solutions ?
                return (Character.isAlphabetic(getChar()) || Character.isDigit(getChar())
                        || getChar() == ' ' || getChar() == '\n') && controller.isFocusable();
            }

            @Override
            public void action() {
                controller.addCharacter(getChar());
            }
        };
    };

    public State annotatingShiftDown = new State("annotatingShiftDown") {
        final Transition doubleClick = new Click(BUTTON1, "=> viewing") {
            @Override
            public boolean guard() {
                return getMouseEvent().getClickCount() == 2;
            }

            @Override
            public void action() {
                controller.toggleAnnotations();
            }
        };

        final Transition drawingClick = new Click(BUTTON1, "=> annotatingShiftDown") {
            @Override
            public boolean guard() {
                return controller.getDrawingAt(controller.getMousePosition()) != null;
            }

            @Override
            public void action() {
                Drawing drawing = controller.getDrawingAt(controller.getMousePosition());
                controller.selectDrawing(drawing);
            }
        };

        final Transition drag = new Drag(BUTTON1, "=> dragShiftDown") {
            @Override
            public boolean guard() {
                return controller.hasDrawingsSelected();
            }
        };

        final Transition shiftReleased = new KeyRelease(KeyEvent.VK_SHIFT, "=> annotating") {};
    };

    public State drag = new State("drag") {
        Point lastMousePosition;

        @Override
        public void enter() {
            lastMousePosition = controller.getMousePosition();
            controller.startDraggingShapes();
        }

        final Transition drag = new Drag("=> drag") {
            @Override
            public void action() {
                Point currentMousePosition = controller.getMousePosition();
                controller.dragDrawings(currentMousePosition, lastMousePosition);
                lastMousePosition = currentMousePosition;
            }
        };

        final Transition stopDragging = new Release(BUTTON1, "=> annotating") {
            @Override
            public void action() {
                controller.startDraggingShapes();
            }
        };

        final Transition dragShiftDown = new KeyPress(KeyEvent.VK_SHIFT, "=> dragShiftDown") {};
    };

    public State dragShiftDown = new State() {
        Point lastMousePosition;

        @Override
        public void enter() {
            lastMousePosition = controller.getMousePosition();
            controller.startDraggingShapes();
        }

        final Transition drag = new Drag("=> dragShiftDown") {
            @Override
            public void action() {
                Point currentMousePosition = controller.getMousePosition();
                controller.dragDrawings(currentMousePosition, lastMousePosition);
                lastMousePosition = currentMousePosition;
            }
        };

        final Transition stopDragging = new Release(BUTTON1, "=> annotatingShiftDown") {
            @Override
            public void action() {
                controller.startDraggingShapes();
            }
        };

        final Transition shiftRelease = new KeyRelease(KeyEvent.VK_SHIFT, "=> annotating") {
            @Override
            public void action() {
                controller.stopDraggingShapes();
            }
        };
    };

}
