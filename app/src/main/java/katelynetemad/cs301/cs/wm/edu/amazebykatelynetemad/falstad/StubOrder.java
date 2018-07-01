package katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.falstad;

import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.MazeConfiguration;
import katelynetemad.cs301.cs.wm.edu.amazebykatelynetemad.generation.Order;

/**
 * 
 * @author Katelyn Etemad
 * This class implements Order for the purpose of testing.
 *
 */
public class StubOrder implements Order {
	private int skill;
	private Builder builder;
	private boolean perfect;
	private MazeConfiguration mazeConfiguration;
	public int percentage;

	/**
	 * Constructor method that receives the skill, builder, and boolean perfect as parameters
	 */
	public StubOrder(int skill, Builder builder, boolean perfect) {
		this.skill = skill;
		this.builder = builder;
		this.perfect = perfect;
	}
	
	@Override
	public int getSkillLevel() {
		return skill;
	}

	@Override
	public Builder getBuilder() {
		return builder;
	}
	
	public MazeConfiguration getMazeConfiguration() {
		return mazeConfiguration;
	}

	@Override
	public boolean isPerfect() {
		return perfect;
	}

	@Override
	public void deliver(MazeConfiguration mazeConfig) {
		this.mazeConfiguration = mazeConfig;
	}

	@Override
	public void updateProgress(int percentage) {
		this.percentage = percentage;
	}

	

}