package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad;

import gui.Constants.UserInput;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.CardinalDirection;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.Cells;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.MazeConfiguration;

/**
 * This class implements the Robot interface.
 * 
 * The BasicRobot class stores information about the robot inside
 * of a maze. Its responsibility is to act as a player would, only
 * having access to the Controller.
 * 
 * Attributes: direction, position, sensors, controller, maze configurations, and cells.
 * Methods: rotate, move, getters/setters, distanceToObstacle, exit/room sensors
 * 
 * @author Katelyn Etemad
 *
 */

public class BasicRobot implements Robot {
	
	private CardinalDirection currentDirection;
	private int[] currentPosition;
	private boolean frontSensor, leftSensor, rightSensor, backwardSensor, roomSensor;
	private Controller controller;
	private MazeConfiguration mazeConfig;
	private Cells cell;
	
	

	public BasicRobot() {
		frontSensor = true;
		leftSensor = true;
		rightSensor = true;
		backwardSensor = true;
		roomSensor = true;
	}
	
	/**
	 * This method rotates the robot given a turn parameter.
	 */
	@Override
	public void rotate(Turn turn) {
		//Use switch-case statements given the parameter turn to determine in which
		//direction to turn the robot, we use the Controller method keyDown to do so.
		switch(turn) {
			case RIGHT:
				controller.keyDown(UserInput.Right, 0);
				break;
			case LEFT:
				controller.keyDown(UserInput.Left, 0);
				break;
			case AROUND:
				controller.keyDown(UserInput.Right, 0);
				controller.keyDown(UserInput.Right, 0);
				break;
			}
	}

	/**
	 * Moves the robot forward a given number of steps by the parameter distance.
	 * We use the Controller class method keyDown to do so.
	 */
	@Override
	public void move(int distance, boolean manual) {
		//Using a while-loop, move the robot forward
		while(distance != 0) {
			controller.keyDown(UserInput.Up, 0);
			distance--;
		}
	}

	@Override
	public int[] getCurrentPosition() throws Exception {
		return controller.getCurrentPosition();
	}

	@Override
	public void setMaze(Controller controller) {
		this.controller = controller;		
	}

	/**
	 * Using the information from the maze configuration, look at the current position
	 * and determine if it matches the exit
	 */
	@Override
	public boolean isAtExit() {
		mazeConfig = controller.getMazeConfiguration();
		currentPosition = controller.getCurrentPosition();
		cell = mazeConfig.getMazecells();
		return cell.isExitPosition(currentPosition[0], currentPosition[1]);
	}

	/**
	 * This method senses if the robot can see the exit (there is nothing in front of it
	 * so the distanceToObject should return infinity).
	 */
	@Override
	public boolean canSeeExit(Direction direction) throws UnsupportedOperationException {
		if(!hasDistanceSensor(direction)) {
			throw new UnsupportedOperationException();
		}
		
		if(this.distanceToObstacle(direction) == Integer.MAX_VALUE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInsideRoom() throws UnsupportedOperationException {
		if(!hasRoomSensor()) {
			throw new UnsupportedOperationException();
		}
		mazeConfig = controller.getMazeConfiguration();
		currentPosition = controller.getCurrentPosition();
		cell = mazeConfig.getMazecells();
		return cell.isInRoom(currentPosition[0], currentPosition[1]);
	}

	@Override
	public boolean hasRoomSensor() {
		return this.roomSensor;
	}

	@Override
	public CardinalDirection getCurrentDirection() {
		return controller.getCurrentDirection();
	}

	@Override
	public float getBatteryLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBatteryLevel(float level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOdometerReading() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetOdometer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getEnergyForFullRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getEnergyForStepForward() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStopped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int distanceToObstacle(Direction direction) throws UnsupportedOperationException {
		if(!hasDistanceSensor(direction)) {
			throw new UnsupportedOperationException();
		}
		//get all of the information for the maze configuration, direction, position, and cell
		mazeConfig = controller.getMazeConfiguration();
		currentDirection = controller.getCurrentDirection();
		currentPosition = controller.getCurrentPosition();
		cell = mazeConfig.getMazecells();
		
		//using the direction parameter, create temporary variables for the direction and change them
		//according to which direction we want to look in
		int[] dir = currentDirection.getDirection();
		//if the direction is forward we just leave the temp variables as they are
		int dx = dir[0];
		int dy = dir[1];
		int temp = 0;
		if(direction == Direction.LEFT) {
			temp = dx;
			dx = -dy;
			dy = temp;
		}
		else if(direction == Direction.RIGHT) {
			temp = dx;
			dx = dy;
			dy = -temp;
		}
		else if(direction == Direction.BACKWARD) {
			dx = -dx;
			dy = -dy;
		}
		
		
		//using a while loop, while there is no wall in front of the robot, increment the correct position
		//variable depending on the direction, and at the end increment the distance variable to return at the end
		int distance = 0;
		int tempx = currentPosition[0];
		int tempy = currentPosition[1];
		currentDirection = currentDirection.getDirection(dx, dy);
		
		while(!cell.hasWall(tempx, tempy, currentDirection)) {
	
			//if statements to determine how we modify the temp position variables depending on
			//the cardinal direction
			if(currentDirection == CardinalDirection.West) {
				tempx--;
			}
			else if(currentDirection == CardinalDirection.East) {
				tempx++;
			}
			else if(currentDirection == CardinalDirection.North) {
				tempy--;
			}
			else if(currentDirection == CardinalDirection.South) {
				tempy++;
			}
			
			//if the current position is outside of the maze then we know the exit is in sight
			//so we return the max integer possible to signify this
			if(tempx < 0 || tempy < 0 || tempx >= mazeConfig.getWidth() || tempy >= mazeConfig.getHeight()) {
				return Integer.MAX_VALUE;
			}
			distance++;
		}
		return distance;
	}

	
	@Override
	public boolean hasDistanceSensor(Direction direction) {
		//switch statements to determine which side has a distance sensor (in this case, only front and left)
		switch(direction) {
		case LEFT:
			return this.leftSensor;
		case FORWARD:
			return this.frontSensor;
		case RIGHT:
			return this.rightSensor;
		case BACKWARD:
			return this.backwardSensor;
		}
		return false;
	}
	

}