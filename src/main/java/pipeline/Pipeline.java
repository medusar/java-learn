package pipeline;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 整条流水线
 */
public class Pipeline {

	// 用于存放任务的第一阶段
	private BlockingDeque<TaskStage> firstStageQueue = new LinkedBlockingDeque<TaskStage>();

	// 用于存储任务的第二阶段
	private BlockingDeque<TaskStage> secondStageQueue = new LinkedBlockingDeque<TaskStage>();

	// 用于存储任务的第三阶段
	private BlockingDeque<TaskStage> thirdStageQueue = new LinkedBlockingDeque<TaskStage>();

	public Pipeline() {
		super();

		// 启动三个线程，分别处理三个阶段的任务
		new Thread() {

			@Override
			public void run() {
				while (true) {
					TaskStage taskStage = null;
					try {
						taskStage = firstStageQueue.poll(80, TimeUnit.SECONDS);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					if (taskStage != null) {
						TaskStage nextStage = taskStage.process();
						try {
							secondStageQueue.put(nextStage);
						} catch (InterruptedException e) {
						}
					} else { // 取出来的数据为空，则终止（这种情况可能会有问题，这里做简单演示）
						break;
					}
				}
			}
		}.start();

		new Thread() {

			@Override
			public void run() {
				while (true) {
					TaskStage taskStage = null;
					try {
						taskStage = secondStageQueue.poll(80, TimeUnit.SECONDS);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					if (taskStage != null) {
						TaskStage nextStage = taskStage.process();
						try {
							thirdStageQueue.put(nextStage);
						} catch (InterruptedException e) {
						}
					} else {
						break;
					}
				}
			}
		}.start();

		new Thread() {

			@Override
			public void run() {
				while (true) {
					TaskStage taskStage = null;
					try {
						taskStage = thirdStageQueue.poll(80, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (taskStage != null) {
						taskStage.process();
					} else {
						break;
					}
				}
			}
		}.start();
	}

	public void process(TaskStage firstStage) {
		try {
			firstStageQueue.put(firstStage);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
