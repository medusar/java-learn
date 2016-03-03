package pipeline;

public class CalculateTaskStage implements TaskStage {
	private String data;

	public CalculateTaskStage(String data) {
		super();
		this.data = data;
	}

	@Override
	public TaskStage process() {
		try {
			Thread.sleep(1000 * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String result = calculate(data);
		System.out.println(result);
		return new RpcTaskStage(result);
	}

	private String calculate(String data2) {
		return data2 + ",CaculateTaskStage";
	}

}
