package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad;

import generation.Distance;
import gui.Robot.Direction;
import gui.Robot.Turn;

/**
 * The WallFollower implements the RobotDriver interface. Its responsibility is to find the exit
 * to the maze by using its leftSensor to follow the left wall until it reaches the exit.
 * Attributes: robot, width, height, pathLength, and distance
 * Methods: getters/setters, drive2Exit
 * @author Katelyn Etemad
 *
 */
public class WallFollower implements RobotDriver{
	private Robot robot;
	private int width, height, pathLength;
	private Distance distance;

	@Override
	public void setRobot(Robot r) {
		this.robot = r;
	}

	@Override
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	/**
	 * This method guides the robot to the exit by following the left wall.
	 * While the robot is not at the exit, we move through the maze by using
	 * the distanceToObstacle method of the robot. 
	 */
	@Override
	public boolean drive2Exit() throws Exception {
		//while the robot is not at the exit, we continue to move
		while(!robot.isAtExit()) {
			//If the robot has a wall to its left, we keep moving, but if there is not
			//a wall to the left, the robot turns left and moves in the new direction
			if(robot.distanceToObstacle(Robot.Direction.LEFT) != 0) {
				robot.rotate(Turn.LEFT);
				if(robot.distanceToObstacle(Robot.Direction.LEFT) != 0) {
					robot.move(1, false);
					if(robot.distanceToObstacle(Robot.Direction.LEFT) != 0) {
						robot.rotate(Turn.LEFT);
						robot.move(1, false);
					}
				}
			}
			else if(robot.distanceToObstacle(Robot.Direction.FORWARD) != 0) {
				robot.move(1, false);
			}
			else {
				robot.rotate(Turn.RIGHT);
			}
		}
		
		//if statements to move the robot out of the maze once it has actually reached the exit position
		if(robot.canSeeExit(Robot.Direction.LEFT)) {
			robot.rotate(Turn.LEFT);
		}
		else if(robot.canSeeExit(Robot.Direction.RIGHT)) {
			robot.rotate(Turn.RIGHT);
		}
		robot.move(1, false);
		
		return true;
	}

	@Override
	public float getEnergyConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPathLength() {
		return pathLength;
	}
	
}