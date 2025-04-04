package gtech.platform;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_CAPS_LOCK;

public enum KeyCode {
    UNKNOWN(GLFW.GLFW_KEY_UNKNOWN),
    SPACE(GLFW.GLFW_KEY_SPACE),
    APOSTROPHE(GLFW.GLFW_KEY_APOSTROPHE),
    COMMA(GLFW.GLFW_KEY_COMMA),
    MINUS(GLFW.GLFW_KEY_MINUS),
    PERIOD(GLFW.GLFW_KEY_PERIOD),
    SLASH(GLFW.GLFW_KEY_SLASH),
    DIGIT_0(GLFW.GLFW_KEY_0),
    DIGIT_1(GLFW.GLFW_KEY_1),
    DIGIT_2(GLFW.GLFW_KEY_2),
    DIGIT_3(GLFW.GLFW_KEY_3),
    DIGIT_4(GLFW.GLFW_KEY_4),
    DIGIT_5(GLFW.GLFW_KEY_5),
    DIGIT_6(GLFW.GLFW_KEY_6),
    DIGIT_7(GLFW.GLFW_KEY_7),
    DIGIT_8(GLFW.GLFW_KEY_8),
    DIGIT_9(GLFW.GLFW_KEY_9),
    SEMICOLON(GLFW.GLFW_KEY_SEMICOLON),
    EQUAL(GLFW.GLFW_KEY_EQUAL),
    A(GLFW.GLFW_KEY_A),
    B(GLFW.GLFW_KEY_B),
    C(GLFW.GLFW_KEY_C),
    D(GLFW.GLFW_KEY_D),
    E(GLFW.GLFW_KEY_E),
    F(GLFW.GLFW_KEY_F),
    G(GLFW.GLFW_KEY_G),
    H(GLFW.GLFW_KEY_H),
    I(GLFW.GLFW_KEY_I),
    J(GLFW.GLFW_KEY_J),
    K(GLFW.GLFW_KEY_K),
    L(GLFW.GLFW_KEY_L),
    M(GLFW.GLFW_KEY_M),
    N(GLFW.GLFW_KEY_N),
    O(GLFW.GLFW_KEY_O),
    P(GLFW.GLFW_KEY_P),
    Q(GLFW.GLFW_KEY_Q),
    R(GLFW.GLFW_KEY_R),
    S(GLFW.GLFW_KEY_S),
    T(GLFW.GLFW_KEY_T),
    U(GLFW.GLFW_KEY_U),
    V(GLFW.GLFW_KEY_V),
    W(GLFW.GLFW_KEY_W),
    X(GLFW.GLFW_KEY_X),
    Y(GLFW.GLFW_KEY_Y),
    Z(GLFW.GLFW_KEY_Z),
    LEFT_BRACKET(GLFW.GLFW_KEY_LEFT_BRACKET),
    BACKSLASH(GLFW.GLFW_KEY_BACKSLASH),
    RIGHT_BRACKET(GLFW.GLFW_KEY_RIGHT_BRACKET),
    GRAVE_ACCENT(GLFW.GLFW_KEY_GRAVE_ACCENT),
    WORLD_1(GLFW.GLFW_KEY_WORLD_1),
    WORLD_2(GLFW.GLFW_KEY_WORLD_2),
    ESCAPE(GLFW.GLFW_KEY_ESCAPE),
    ENTER(GLFW.GLFW_KEY_ENTER),
    TAB(GLFW.GLFW_KEY_TAB),
    BACKSPACE(GLFW.GLFW_KEY_BACKSPACE),
    INSERT(GLFW.GLFW_KEY_INSERT),
    DELETE(GLFW.GLFW_KEY_DELETE),
    RIGHT(GLFW.GLFW_KEY_RIGHT),
    LEFT(GLFW.GLFW_KEY_LEFT),
    DOWN(GLFW.GLFW_KEY_DOWN),
    UP(GLFW.GLFW_KEY_UP),
    PAGE_UP(GLFW.GLFW_KEY_PAGE_UP),
    PAGE_DOWN(GLFW.GLFW_KEY_PAGE_DOWN),
    HOME(GLFW.GLFW_KEY_HOME),
    END(GLFW.GLFW_KEY_END),
    CAPS_LOCK(GLFW_KEY_CAPS_LOCK),
    SCROLL_LOCK(GLFW.GLFW_KEY_SCROLL_LOCK),
    NUM_LOCK(GLFW.GLFW_KEY_NUM_LOCK),
    PRINT_SCREEN(GLFW.GLFW_KEY_PRINT_SCREEN),
    PAUSE(GLFW.GLFW_KEY_PAUSE),
    F1(GLFW.GLFW_KEY_F1),
    F2(GLFW.GLFW_KEY_F2),
    F3(GLFW.GLFW_KEY_F3),
    F4(GLFW.GLFW_KEY_F4),
    F5(GLFW.GLFW_KEY_F5),
    F6(GLFW.GLFW_KEY_F6),
    F7(GLFW.GLFW_KEY_F7),
    F8(GLFW.GLFW_KEY_F8),
    F9(GLFW.GLFW_KEY_F9),
    F10(GLFW.GLFW_KEY_F10),
    F11(GLFW.GLFW_KEY_F11),
    F12(GLFW.GLFW_KEY_F12),
    F13(GLFW.GLFW_KEY_F13),
    F14(GLFW.GLFW_KEY_F14),
    F15(GLFW.GLFW_KEY_F15),
    F16(GLFW.GLFW_KEY_F16),
    F17(GLFW.GLFW_KEY_F17),
    F18(GLFW.GLFW_KEY_F18),
    F19(GLFW.GLFW_KEY_F19),
    F20(GLFW.GLFW_KEY_F20),
    F21(GLFW.GLFW_KEY_F21),
    F22(GLFW.GLFW_KEY_F22),
    F23(GLFW.GLFW_KEY_F23),
    F24(GLFW.GLFW_KEY_F24),
    F25(GLFW.GLFW_KEY_F25),
    KP_0(GLFW.GLFW_KEY_KP_0),
    KP_1(GLFW.GLFW_KEY_KP_1),
    KP_2(GLFW.GLFW_KEY_KP_2),
    KP_3(GLFW.GLFW_KEY_KP_3),
    KP_4(GLFW.GLFW_KEY_KP_4),
    KP_5(GLFW.GLFW_KEY_KP_5),
    KP_6(GLFW.GLFW_KEY_KP_6),
    KP_7(GLFW.GLFW_KEY_KP_7),
    KP_8(GLFW.GLFW_KEY_KP_8),
    KP_9(GLFW.GLFW_KEY_KP_9),
    KP_DECIMAL(GLFW.GLFW_KEY_KP_DECIMAL),
    KP_DIVIDE(GLFW.GLFW_KEY_KP_DIVIDE),
    KP_MULTIPLY(GLFW.GLFW_KEY_KP_MULTIPLY),
    KP_SUBTRACT(GLFW.GLFW_KEY_KP_SUBTRACT),
    KP_ADD(GLFW.GLFW_KEY_KP_ADD),
    KP_ENTER(GLFW.GLFW_KEY_KP_ENTER),
    KP_EQUAL(GLFW.GLFW_KEY_KP_EQUAL),
    LEFT_SHIFT(GLFW.GLFW_KEY_LEFT_SHIFT),
    LEFT_CONTROL(GLFW.GLFW_KEY_LEFT_CONTROL),
    LEFT_ALT(GLFW.GLFW_KEY_LEFT_ALT),
    LEFT_SUPER(GLFW.GLFW_KEY_LEFT_SUPER),
    RIGHT_SHIFT(GLFW.GLFW_KEY_RIGHT_SHIFT),
    RIGHT_CONTROL(GLFW.GLFW_KEY_RIGHT_CONTROL),
    RIGHT_ALT(GLFW.GLFW_KEY_RIGHT_ALT),
    RIGHT_SUPER(GLFW.GLFW_KEY_RIGHT_SUPER),
    MENU(GLFW.GLFW_KEY_MENU),

    LAST(GLFW.GLFW_KEY_LAST);

    private final int glfwKey;

    KeyCode(int glfwKey) {
        this.glfwKey = glfwKey;
    }

    public int getGlfwKey() {
        return glfwKey;
    }

    public static KeyCode fromGlfwKey(int glfwKey) {
        return switch (glfwKey) {
            case GLFW.GLFW_KEY_SPACE -> SPACE;
            case GLFW.GLFW_KEY_APOSTROPHE -> APOSTROPHE;
            case GLFW.GLFW_KEY_COMMA -> COMMA;
            case GLFW.GLFW_KEY_MINUS -> MINUS;
            case GLFW.GLFW_KEY_PERIOD -> PERIOD;
            case GLFW.GLFW_KEY_SLASH -> SLASH;
            case GLFW.GLFW_KEY_0 -> DIGIT_0;
            case GLFW.GLFW_KEY_1 -> DIGIT_1;
            case GLFW.GLFW_KEY_2 -> DIGIT_2;
            case GLFW.GLFW_KEY_3 -> DIGIT_3;
            case GLFW.GLFW_KEY_4 -> DIGIT_4;
            case GLFW.GLFW_KEY_5 -> DIGIT_5;
            case GLFW.GLFW_KEY_6 -> DIGIT_6;
            case GLFW.GLFW_KEY_7 -> DIGIT_7;
            case GLFW.GLFW_KEY_8 -> DIGIT_8;
            case GLFW.GLFW_KEY_9 -> DIGIT_9;
            case GLFW.GLFW_KEY_SEMICOLON -> SEMICOLON;
            case GLFW.GLFW_KEY_EQUAL -> EQUAL;
            case GLFW.GLFW_KEY_A -> A;
            case GLFW.GLFW_KEY_B -> B;
            case GLFW.GLFW_KEY_C -> C;
            case GLFW.GLFW_KEY_D -> D;
            case GLFW.GLFW_KEY_E -> E;
            case GLFW.GLFW_KEY_F -> F;
            case GLFW.GLFW_KEY_G -> G;
            case GLFW.GLFW_KEY_H -> H;
            case GLFW.GLFW_KEY_I -> I;
            case GLFW.GLFW_KEY_J -> J;
            case GLFW.GLFW_KEY_K -> K;
            case GLFW.GLFW_KEY_L -> L;
            case GLFW.GLFW_KEY_M -> M;
            case GLFW.GLFW_KEY_N -> N;
            case GLFW.GLFW_KEY_O -> O;
            case GLFW.GLFW_KEY_P -> P;
            case GLFW.GLFW_KEY_Q -> Q;
            case GLFW.GLFW_KEY_R -> R;
            case GLFW.GLFW_KEY_S -> S;
            case GLFW.GLFW_KEY_T -> T;
            case GLFW.GLFW_KEY_U -> U;
            case GLFW.GLFW_KEY_V -> V;
            case GLFW.GLFW_KEY_W -> W;
            case GLFW.GLFW_KEY_X -> X;
            case GLFW.GLFW_KEY_Y -> Y;
            case GLFW.GLFW_KEY_Z -> Z;
            case GLFW.GLFW_KEY_LEFT_BRACKET -> LEFT_BRACKET;
            case GLFW.GLFW_KEY_BACKSLASH -> BACKSLASH;
            case GLFW.GLFW_KEY_RIGHT_BRACKET -> RIGHT_BRACKET;
            case GLFW.GLFW_KEY_GRAVE_ACCENT -> GRAVE_ACCENT;
            case GLFW.GLFW_KEY_WORLD_1 -> WORLD_1;
            case GLFW.GLFW_KEY_WORLD_2 -> WORLD_2;
            case GLFW.GLFW_KEY_ESCAPE -> ESCAPE;
            case GLFW.GLFW_KEY_ENTER -> ENTER;
            case GLFW.GLFW_KEY_TAB -> TAB;
            case GLFW.GLFW_KEY_BACKSPACE -> BACKSPACE;
            case GLFW.GLFW_KEY_INSERT -> INSERT;
            case GLFW.GLFW_KEY_DELETE -> DELETE;
            case GLFW.GLFW_KEY_RIGHT -> RIGHT;
            case GLFW.GLFW_KEY_LEFT -> LEFT;
            case GLFW.GLFW_KEY_DOWN -> DOWN;
            case GLFW.GLFW_KEY_UP -> UP;
            case GLFW.GLFW_KEY_PAGE_UP -> PAGE_UP;
            case GLFW.GLFW_KEY_PAGE_DOWN -> PAGE_DOWN;
            case GLFW.GLFW_KEY_HOME -> HOME;
            case GLFW.GLFW_KEY_END -> END;
            case GLFW_KEY_CAPS_LOCK -> CAPS_LOCK;
            case GLFW.GLFW_KEY_SCROLL_LOCK -> SCROLL_LOCK;
            case GLFW.GLFW_KEY_NUM_LOCK -> NUM_LOCK;
            case GLFW.GLFW_KEY_PRINT_SCREEN -> PRINT_SCREEN;
            case GLFW.GLFW_KEY_PAUSE -> PAUSE;
            case GLFW.GLFW_KEY_F1 -> F1;
            case GLFW.GLFW_KEY_F2 -> F2;
            case GLFW.GLFW_KEY_F3 -> F3;
            case GLFW.GLFW_KEY_F4 -> F4;
            case GLFW.GLFW_KEY_F5 -> F5;
            case GLFW.GLFW_KEY_F6 -> F6;
            case GLFW.GLFW_KEY_F7 -> F7;
            case GLFW.GLFW_KEY_F8 -> F8;
            case GLFW.GLFW_KEY_F9 -> F9;
            case GLFW.GLFW_KEY_F10 -> F10;
            case GLFW.GLFW_KEY_F11 -> F11;
            case GLFW.GLFW_KEY_F12 -> F12;
            case GLFW.GLFW_KEY_F13 -> F13;
            case GLFW.GLFW_KEY_F14 -> F14;
            case GLFW.GLFW_KEY_F15 -> F15;
            case GLFW.GLFW_KEY_F16 -> F16;
            case GLFW.GLFW_KEY_F17 -> F17;
            case GLFW.GLFW_KEY_F18 -> F18;
            case GLFW.GLFW_KEY_F19 -> F19;
            case GLFW.GLFW_KEY_F20 -> F20;
            case GLFW.GLFW_KEY_F21 -> F21;
            case GLFW.GLFW_KEY_F22 -> F22;
            case GLFW.GLFW_KEY_F23 -> F23;
            case GLFW.GLFW_KEY_F24 -> F24;
            case GLFW.GLFW_KEY_F25 -> F25;
            case GLFW.GLFW_KEY_KP_0 -> KP_0;
            case GLFW.GLFW_KEY_KP_1 -> KP_1;
            case GLFW.GLFW_KEY_KP_2 -> KP_2;
            case GLFW.GLFW_KEY_KP_3 -> KP_3;
            case GLFW.GLFW_KEY_KP_4 -> KP_4;
            case GLFW.GLFW_KEY_KP_5 -> KP_5;
            case GLFW.GLFW_KEY_KP_6 -> KP_6;
            case GLFW.GLFW_KEY_KP_7 -> KP_7;
            case GLFW.GLFW_KEY_KP_8 -> KP_8;
            case GLFW.GLFW_KEY_KP_9 -> KP_9;
            case GLFW.GLFW_KEY_KP_DECIMAL -> KP_DECIMAL;
            case GLFW.GLFW_KEY_KP_DIVIDE -> KP_DIVIDE;
            case GLFW.GLFW_KEY_KP_MULTIPLY -> KP_MULTIPLY;
            case GLFW.GLFW_KEY_KP_SUBTRACT -> KP_SUBTRACT;
            case GLFW.GLFW_KEY_KP_ADD -> KP_ADD;
            case GLFW.GLFW_KEY_KP_ENTER -> KP_ENTER;
            case GLFW.GLFW_KEY_KP_EQUAL -> KP_EQUAL;
            case GLFW.GLFW_KEY_LEFT_SHIFT -> LEFT_SHIFT;
            case GLFW.GLFW_KEY_LEFT_CONTROL -> LEFT_CONTROL;
            case GLFW.GLFW_KEY_LEFT_ALT -> LEFT_ALT;
            case GLFW.GLFW_KEY_LEFT_SUPER -> LEFT_SUPER;
            case GLFW.GLFW_KEY_RIGHT_SHIFT -> RIGHT_SHIFT;
            case GLFW.GLFW_KEY_RIGHT_CONTROL -> RIGHT_CONTROL;
            case GLFW.GLFW_KEY_RIGHT_ALT -> RIGHT_ALT;
            case GLFW.GLFW_KEY_RIGHT_SUPER -> RIGHT_SUPER;
            case GLFW.GLFW_KEY_MENU -> MENU;
            default -> UNKNOWN;
        };

    }
}
