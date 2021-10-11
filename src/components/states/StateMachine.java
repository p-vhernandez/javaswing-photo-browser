package components.states;

import components.photo.PhotoComponent;
import fr.lri.swingstates.sm.JStateMachine;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Click;
import fr.lri.swingstates.sm.transitions.Drag;
import fr.lri.swingstates.sm.transitions.KeyPress;
import fr.lri.swingstates.sm.transitions.Release;

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

        final Transition singleClick = new Click(BUTTON1, "=> typing") {
            @Override
            public boolean guard() {
                return getMouseEvent().getClickCount() == 1
                        && controller.annotationsAllowed();
            }

            @Override
            public void action() {
                controller.startTyping(controller.getMousePosition());
            }
        };

        final Transition draw = new Drag(BUTTON1, "=> drawing") {
            @Override
            public void action() {
                controller.draw(controller.getMousePosition());
            }
        };
    };

    public State drawing = new State("drawing") {
        final Transition draw = new Drag() {
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

        final Transition singleClick = new Click(BUTTON1, "=> typing") {
            @Override
            public boolean guard() {
                return getMouseEvent().getClickCount() == 1;
            }

            @Override
            public void action() {
                controller.startTyping(controller.getMousePosition());
            }
        };
    };

    public State typing = new State("typing") {
        final Transition deleteCharacter = new KeyPress() {
            @Override
            public boolean guard() {
                return getChar() == '\u0008';
            }

            @Override
            public void action() {
                controller.deleteCharacter();
            }
        };

        final Transition addCharacter = new KeyPress() {
            @Override
            public void action() {
                controller.addCharacter(getChar());
            }
        };

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

        final Transition singleClick = new Click(BUTTON1, "=> typing") {
            @Override
            public boolean guard() {
                return getMouseEvent().getClickCount() == 1;
            }

            @Override
            public void action() {
                controller.startTyping(controller.getMousePosition());
            }
        };

        final Transition draw = new Drag(BUTTON1, "=> drawing") {
            @Override
            public void action() {
                controller.draw(controller.getMousePosition());
            }
        };
    };

}
