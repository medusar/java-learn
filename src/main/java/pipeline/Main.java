package pipeline;

import java.util.concurrent.locks.LockSupport;

public class Main {
	public static void main(String[] args) {
		Pipeline line = new Pipeline();

		for (int i = 0; i < 10; i++) {
			line.process(new DBQueryTaskStage());
		}

		LockSupport.park();
	}
}
