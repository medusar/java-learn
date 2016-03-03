package pipeline;

/**
 * 任务阶段，比如一个任务分为三个阶段，每一个阶段都有一个TaskStage与之对应
 */
public interface TaskStage {

	/**
	 * 处理任务，返回值为该任务阶段的下一个阶段
	 */
	public TaskStage process();
}
