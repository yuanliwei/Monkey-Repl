
package android.util;

public class StateSet {

    public static final int VIEW_STATE_WINDOW_FOCUSED = 1;
    public static final int VIEW_STATE_SELECTED = 1 << 1;
    public static final int VIEW_STATE_FOCUSED = 1 << 2;
    public static final int VIEW_STATE_ENABLED = 1 << 3;
    public static final int VIEW_STATE_PRESSED = 1 << 4;
    public static final int VIEW_STATE_ACTIVATED = 1 << 5;
    public static final int VIEW_STATE_ACCELERATED = 1 << 6;
    public static final int VIEW_STATE_HOVERED = 1 << 7;
    public static final int VIEW_STATE_DRAG_CAN_ACCEPT = 1 << 8;
    public static final int VIEW_STATE_DRAG_HOVERED = 1 << 9;

    public static int[] get(int mask) {
        throw new RuntimeException("Stub!");
    }

    public StateSet() {
        throw new RuntimeException("Stub!");
    }

    public static final int[] WILD_CARD = new int[0];

    public static final int[] NOTHING = new int[] { 0 };

    public static boolean isWildCard(int[] stateSetOrSpec) {
        throw new RuntimeException("Stub!");
    }

    public static boolean stateSetMatches(int[] stateSpec, int[] stateSet) {
        throw new RuntimeException("Stub!");
    }

    public static boolean stateSetMatches(int[] stateSpec, int state) {
        throw new RuntimeException("Stub!");
    }

    public static boolean containsAttribute(int[][] stateSpecs, int attr) {
        throw new RuntimeException("Stub!");
    }

    public static int[] trimStateSet(int[] states, int newSize) {
        throw new RuntimeException("Stub!");
    }

    public static String dump(int[] states) {
        throw new RuntimeException("Stub!");
    }
}
