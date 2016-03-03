package pipeline;

/**
 * 查询数据库阶段
 */
public class DBQueryTaskStage implements TaskStage {

	@Override
	public TaskStage process() {
		String result = queryDB();
		System.out.println(result);
		return new CalculateTaskStage(result);
	}

	/**
	 * 模拟查询数据库过程
	 * 
	 * @return
	 */
	private String queryDB() {
		try {
			Thread.sleep(1000 * 20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "DBQueryTaskStage";
	}

}
