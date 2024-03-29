/**
 * 
 */
package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.CardinalDirection;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.Cells;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.MazeConfiguration;
//import java.awt.Color;
//import java.awt.Graphics;

/**
 * This class encapsulates all functionality to draw a map of the overall maze, the set of visible walls, the solution.
 * The map is drawn on the screen in such a way that the current position remains at the center of the screen.
 * The current position is visualized as a red dot with an attached arc for its current direction.
 * The solution is visualized as a yellow line from the current position towards the exit of the map.
 * Walls that are currently visible in the first person view are drawn white, all other walls are drawn in grey.
 * It is possible to zoom in and out of the map.
 * 
 * This code is refactored code from Maze.java by Paul Falstad, www.falstad.com, Copyright (C) 1998, all rights reserved
 * Paul Falstad granted permission to modify and use code for teaching purposes.
 * Refactored by Peter Kemper
 *
 */
public class MapDrawer {

	// keep local copies of values determined for UI appearance
	int view_width = 400;
	int view_height = 400;
	int map_unit = 128;
	int map_scale = 10 ;
	int step_size = map_unit/4;
	/**
	 * Seencells contains information on walls that are seen from the current point of view.
	 * The field is set by the constructor. The referenced object is shared with
	 * the FirstPersonDrawer that writes content into it. The MapDrawer only
	 * reads content to decide which lines to draw and in which color.
	 */
	Cells seencells ;

	/**
	 * Contains all necessary information about current maze, i.e.
	 * cells: location of walls
	 * dists: distance to exit
	 * width and height of the maze
	 */
	MazeConfiguration mazeConfig ;

	/**
	 * The current state of the controller.
	 * The map view is only drawn on top of the
	 * first person view when the user plays the game.
	 * So the state must be StatePlaying
	 */
	StatePlaying controller2;
	
	/**
	 * Constructor
	 * @param width of display
	 * @param height of display
	 * @param map_unit
	 * @param step_size
	 * @param seencells
	 * @param map_scale
	 * @param c
	 */
	public MapDrawer(int width, int height, int map_unit, int step_size, Cells seencells, int map_scale, StatePlaying c){
		//System.out.println("MapDrawer: constructor called") ;
		view_width = width ;
		view_height = height ;
		this.map_unit = map_unit ;
		this.step_size = step_size ;
		this.seencells = seencells ;
		this.map_scale = map_scale ;
		controller2 = c ;
		mazeConfig = controller2.getMazeConfiguration() ;
		assert mazeConfig != null : "MapDrawer: maze configuration is null in given maze object!" ;
	}
	
	public void incrementMapScale() {
		if (null != controller2) {
			if (controller2.isInMapMode())
				map_scale += 1 ;
		}
	}
	
	public void decrementMapScale() {
		if (null != controller2) {
			if (controller2.isInMapMode())
			{
				map_scale -= 1 ;
				if (1 > map_scale)
					map_scale = 1 ;
			}
		}
	}

	
	public void redraw(MazePanel mazePanel, Constants.StateGUI state, int px, int py,
					   int view_dx, int view_dy, int walk_step, int view_offset,
					   RangeSet rset, int ang) {
		//dbg("redraw") ;
		if (state != Constants.StateGUI.STATE_PLAY)
			return ;
		if (null != controller2) {
			if (controller2.isInMapMode()) {
				draw_map(mazePanel, px, py, walk_step, view_dx, view_dy,
						controller2.isInShowMazeMode(), controller2.isInShowSolutionMode()) ;
				draw_currentlocation(mazePanel, view_dx, view_dy) ;
			}
		}
	}
	//////////////////////////////// private, internal methods //////////////////////////////
	/**
	 * Helper method for redraw, called if map_mode is true, i.e. the users wants to see the overall map.
	 * The map is drawn only on a small rectangle inside the maze area such that only a part of the map is actually shown.
	 * Of course a part covering the current location needs to be displayed.
	 * @param gc graphics handler to manipulate screen
	 */
	public void draw_map(MazePanel mazePanel, int px, int py, int walk_step,
			int view_dx, int view_dy, boolean showMaze, boolean showSolution) {
		// dimensions of the maze
		final int mazew = mazeConfig.getWidth() ;
		final int mazeh = mazeConfig.getHeight() ;
		
		mazePanel.setColor("white");
		
		// determine offsets for x and y
		int vx = px*map_unit+map_unit/2;
		vx += viewd_unscale(view_dx*(step_size*walk_step));
		int vy = py*map_unit+map_unit/2;
		vy += viewd_unscale(view_dy*(step_size*walk_step));
		int offx = -vx*map_scale/map_unit + view_width/2;
		int offy = -vy*map_scale/map_unit + view_height/2;
		
		// compute minimum for x,y
		int xmin = -offx/map_scale;
		int ymin = -offy/map_scale;
		if (xmin < 0) xmin = 0; 
		if (ymin < 0) ymin = 0;
		
		// compute maximum for x,y
		int xmax = (view_width -offx)/map_scale+1;
		int ymax = (view_height-offy)/map_scale+1;
		if (xmax >= mazew)  xmax = mazew;
		if (ymax >= mazeh)  ymax = mazeh;
		
		// iterate over integer grid between min and max of x,y
		for (int y = ymin; y <= ymax; y++)
			for (int x = xmin; x <= xmax; x++) {
				int nx1 = x*map_scale + offx;
				int ny1 = view_height-1-(y*map_scale + offy);
				int nx2 = nx1 + map_scale;
				int ny2 = ny1 - map_scale;
				boolean theCondition = (x >= mazew) ? false : ((y < mazeh) ?
						mazeConfig.hasWall(x,y, CardinalDirection.North) :
							mazeConfig.hasWall(x,y-1, CardinalDirection.South));

				mazePanel.setColor(seencells.hasWall(x,y, CardinalDirection.North) ? "white" : "gray");
				if ((seencells.hasWall(x,y, CardinalDirection.North) || showMaze) && theCondition)
					mazePanel.drawLine(nx1, ny1, nx2, ny1);
				
				theCondition = (y >= mazeh) ? false : ((x < mazew) ?
						mazeConfig.hasWall(x,y, CardinalDirection.West) :
							mazeConfig.hasWall((x-1),y, CardinalDirection.East));

				mazePanel.setColor(seencells.hasWall(x,y, CardinalDirection.West) ? "white" : "gray");
				if ((seencells.hasWall(x,y, CardinalDirection.West) || showMaze) && theCondition)
					mazePanel.drawLine(nx1, ny1, nx1, ny2);
			}
		
		if (showSolution) {
			draw_solution(mazePanel, offx, offy, px, py) ;
		}
	}
	/**
	 * Draws an oval red shape with and arrow for the current position 
	 * and direction on the maze.
	 * It always reside on the center of the screen. 
	 * The map drawing moves if the user changes location.
	 * @param mazePanel to draw on
	 */
	public void draw_currentlocation(MazePanel mazePanel, int view_dx, int view_dy) {
		mazePanel.setColor("red");
		// draw oval of appropriate size at the center of the screen
		int ctrx = view_width/2; // center x
		int ctry = view_height/2; // center y
		int cirsiz = map_scale/2; // circle size
		mazePanel.fillOval(ctrx-cirsiz/2, ctry-cirsiz/2, cirsiz, cirsiz);
		// draw a red arrow with the oval to indicate direction
		int arrlen = 7*map_scale/16; // arrow length
		int aptx = ctrx + ((arrlen * view_dx) >> 16);
		int apty = ctry - ((arrlen * view_dy) >> 16);
		int arrlen2 = map_scale/4;
		int aptx2 = ctrx + ((arrlen2 * view_dx) >> 16);
		int apty2 = ctry - ((arrlen2 * view_dy) >> 16);
		//int ptoflen = map_scale/8; // unused
		int ptofx = -( arrlen2 * view_dy) >> 16;
		int ptofy = -( arrlen2 * view_dx) >> 16;
		// now the drawing
		mazePanel.drawLine(ctrx, ctry, aptx, apty);
		mazePanel.drawLine(aptx, apty, aptx2 + ptofx, apty2 + ptofy);
		mazePanel.drawLine(aptx, apty, aptx2 - ptofx, apty2 - ptofy);
	}
	
	/**
	 * Draws a yellow line to show the solution on the overall map. 
	 * Method is only called if in state playing and map_mode 
	 * and showSolution are true.
	 * Since the current position is fixed at the center of the screen, 
	 * all lines on the map are drawn with some offset.
	 * @param mazePanel to draw lines on
	 * @param offx
	 * @param offy
	 */
	public void draw_solution(MazePanel mazePanel, int offx, int offy, int px, int py) {

		if (!mazeConfig.isValidPosition(px, py)) {
			dbg(" Parameter error: position out of bounds: (" + px + "," + py + ") for maze of size " + mazeConfig.getWidth() + "," + mazeConfig.getHeight()) ;
			return ;
		}
		// current position on the solution path (sx,sy)
		int sx = px;
		int sy = py;
		int distance = mazeConfig.getDistanceToExit(sx, sy);
		
		mazePanel.setColor("yellow");
		
		// while we are more than 1 step away from the final position
		while (distance > 1) {
			// find neighbor closer to exit (with no wall in between)
			int[] neighbor = mazeConfig.getNeighborCloserToExit(sx, sy) ;
			if (null == neighbor)
				return ; // error
			// scale coordinates, original calculation:
			// x-coordinates
			// nx1     == sx*map_scale + offx + map_scale/2;
			// nx1+ndx == sx*map_scale + offx + map_scale/2 + dx*map_scale == (sx+dx)*map_scale + offx + map_scale/2;
			// y-coordinates
			// ny1     == view_height-1-(sy*map_scale + offy) - map_scale/2;
			// ny1+ndy == view_height-1-(sy*map_scale + offy) - map_scale/2 + -dy * map_scale == view_height-1 -((sy+dy)*map_scale + offy) - map_scale/2
			// current position coordinates
			int nx1 = sx*map_scale + offx + map_scale/2;
			int ny1 = view_height-1-(sy*map_scale + offy) - map_scale/2;
			// neighbor position coordinates
			int nx2 = neighbor[0]*map_scale + offx + map_scale/2;
			int ny2 = view_height-1-(neighbor[1]*map_scale + offy) - map_scale/2;
			mazePanel.drawLine(nx1, ny1, nx2, ny2);
			
			// update loop variables for current position (sx,sy) and distance d for next iteration
			sx = neighbor[0];
			sy = neighbor[1];
			distance = mazeConfig.getDistanceToExit(sx, sy) ;

		}
	}
	/**
	 * Unscale value
	 * @param x
	 * @return
	 */
	final int viewd_unscale(int x) {
		return x >> 16;
	}
	/**
	 * Debug output
	 * @param str
	 */
	private void dbg(String str) {
		// TODO: change this to a logger
		System.out.println("MapDrawer:"+ str);
	}
}
