package pipeline;

public class RpcTaskStage implements TaskStage {

	private String data;

	public RpcTaskStage(String data) {
		super();
		this.data = data;
	}

	@Override
	public TaskStage process() {
		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(data + ",RpcTaskStage");
		return null;
	}

}
