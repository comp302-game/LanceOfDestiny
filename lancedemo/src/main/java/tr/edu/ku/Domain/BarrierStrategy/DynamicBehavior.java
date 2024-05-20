package tr.edu.ku.Domain.BarrierStrategy;

import tr.edu.ku.Domain.Barrier;

//STRATEGY PATTERN - MOVEMENT BEHAVIOR FOR DIFFERENT TYPES OF BARRIERS
public interface DynamicBehavior {
    public void move(Barrier barrier);
    public double getSpeed();
    public void setSpeed(double d);
} 