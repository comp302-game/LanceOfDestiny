package tr.edu.ku.AppInterface;

import tr.edu.ku.GameArea.Layout;

public interface ModeSwitchListener {
    void switchToRunningMode(Layout layout);
    void switchToEditingMode();
}
