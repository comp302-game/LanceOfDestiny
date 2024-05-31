package tr.edu.ku.AppInterface;

import tr.edu.ku.GameArea.Grid;

public interface ModeSwitchListener {
    void switchToRunningMode(Grid grid);
    void switchToMultiRunningMode(Grid grid);
    void switchToEditingMode();
}
