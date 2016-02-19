package random;

import java.util.Random;

/**
 * Date: 2016年1月22日 下午1:21:46 <br/>
 * 
 * @author medusar
 */
public class WeightRandom {

	public static int getByRandom(int[] weights) {
		int totalWeight = 0;

		for (int i = 0; i < weights.length; i++) {
			totalWeight += weights[i];
		}

		int offset = new Random().nextInt(totalWeight);
		for (int i = 0; i < weights.length; i++) {
			offset -= weights[i];
			if (offset < 0) {
				return weights[i];
			}
		}

		return weights[new Random().nextInt(weights.length)];
	}

	public static void main(String[] args) {
		int[] data = { 1,2,7 };
		int[] count = new int[10];
		for (int i = 0; i < 10000; i++) {
			int v = getByRandom(data);
			count[v]++;
		}
		for (int i = 0; i < count.length; i++) {
			System.out.println(i + ":" + count[i]);
		}
	}

}
