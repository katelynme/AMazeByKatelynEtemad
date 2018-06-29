package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation;

import java.util.ArrayList;

public class MazeBuilderKruskal extends MazeBuilder implements Runnable {
	private int[][] id;
	
	/**
	 * Constructor with no parameters.
	 */
	public MazeBuilderKruskal() {
		super();
		System.out.println("MazeBuilderKruskal uses Kruskal's algorithm to generate maze.");
	}
	
	/**
	 * Constructor for a deterministic maze
	 * @param det
	 */
	public MazeBuilderKruskal(boolean det) {
		super(det);
		System.out.println("MazeBuilderKruskal uses Kruskal's algorithm to generate maze.");
	}

	/**
	 * This method generates the pathways using Kruskal's algorithm and an unweighted graph.
	 * Each cell is given an unique id to keep track of which ones are part of the same set.
	 * We then loop over the edges and randomly pick one. We compare it to its neighbor, and if
	 * the neighbor has a different id, we erase the wall and set the neighbors id to the current id.
	 * Otherwise, if the neighbor has the same id, then we leave the wall there.
	 */
	@Override
	protected void generatePathways() {
		//list of walls to possibly be removed.
		final ArrayList<Wall> candidates = new ArrayList<Wall>();
		updateListOfWalls(candidates);
		
		id = new int[width][height];			//create an array to store unique id values
		int value = 0;							//specific value (id) to store for each position
		for(int r = 0; r < width; r++) {
			for(int c = 0; c < height; c++) {
				id[r][c] = value;
				value++;
			}
		}
		
		//use a while loop to go through the list of candidates
		while(!candidates.isEmpty()) {
			//randomly choose a wall
			Wall currWall = extractWallFromCandidateSetRandomly(candidates);
			int currXCoord = currWall.getX();
			int currYCoord = currWall.getY();

			//check to see if the neighbor is within the bounds of the maze
			int neighborXCoord = currWall.getNeighborX();
			int neighborYCoord = currWall.getNeighborY();
			if((neighborXCoord >= 0 && neighborXCoord < width) && (neighborYCoord >= 0 && neighborYCoord < height)) {
				//if it is, and the id's of the current cell and it's neighbor are not equal,
				//update the neighbors id to the one of the current cell
				if(id[currXCoord][currYCoord] != id[neighborXCoord][neighborYCoord]) {
					updateNeighbor(currWall, currXCoord, currYCoord, neighborXCoord, neighborYCoord);
				}
			}
			
		}
	}
	
	/**
	 * This method updates the neighbor's id to the current id.
	 * It deletes the wall between the cell and its neighbor, and updates
	 * the board so that the id's are the same for the existing path.
	 * @param wall
	 * @param currX
	 * @param currY
	 * @param neighborX
	 * @param neighborY
	 */
	private void updateNeighbor(Wall wall, int currX, int currY, int neighborX, int neighborY) {
		cells.deleteWall(wall);
		int neighborId = id[neighborX][neighborY];
		int currId = id[currX][currY];
		for(int r = 0; r < width; r++) {
			for(int c = 0; c < height; c++) {
				if(id[r][c] == neighborId) {
					id[r][c] = currId;
				}
			}
		}
	}
	
	/**
	 * (Same as Prim's)
	 * Pick a random position in the list of candidates, remove the candidate from the list and return it
	 * @param candidates
	 * @return candidate from the list, randomly chosen
	 */
	private Wall extractWallFromCandidateSetRandomly(final ArrayList<Wall> candidates) {
		return candidates.remove(random.nextIntWithinInterval(0, candidates.size()-1)); 
	}
	
	/**
	 * Unlike prim's algorithm, this method goes over all the possible walls of the map.
	 * @param walls
	 */
	private void updateListOfWalls(ArrayList<Wall> walls) {
		for(int r = 0; r < width; r++) {
			for(int c = 0; c < height; c++) {
				for(CardinalDirection cd: CardinalDirection.values()) {
					Wall wall = new Wall(r, c, cd);
					if(cells.canGo(wall)) {
						walls.add(wall);
					}					
				}
			}
		}
	}

}