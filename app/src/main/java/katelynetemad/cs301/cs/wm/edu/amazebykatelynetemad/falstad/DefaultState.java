/**
 * 
 */
package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.MazeConfiguration;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.Order;

/**
 * This is a default implementation of the State interface
 * with methods that do nothing but providing runtime exceptions
 * such that subclasses of this class can selectively override
 * those methods that are truly needed.
 *
 * @author Peter Kemper
 *
 */
public class DefaultState implements State {

    @Override
    public void start(MazePanel panel) {
        throw new RuntimeException("DefaultState:using unimplemented method");    
    }

    @Override
    public void setFileName(String filename) {
        throw new RuntimeException("DefaultState:using unimplemented method");   
    }

    @Override
    public void setSkillLevel(int skillLevel) {
        throw new RuntimeException("DefaultState:using unimplemented method");  
    }
    @Override
    public void setPerfect(boolean isPerfect) {
        throw new RuntimeException("DefaultState:using unimplemented method"); 
    }

    @Override
    public void setMazeConfiguration(MazeConfiguration config) {
        throw new RuntimeException("DefaultState:using unimplemented method");   
    }

    @Override
    public void setPathLength(int pathLength) {
        throw new RuntimeException("DefaultState:using unimplemented method");   
    }

    @Override
    public boolean keyDown(Constants.UserInput key, int value) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }

    @Override
    public void setBuilder(Order.Builder dfs) {
        throw new RuntimeException("DefaultState:using unimplemented method");
    }
}
