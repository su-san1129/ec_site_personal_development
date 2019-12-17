package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * バッチ処理を実装するクラス.
 * 
 * @author takahiro.suzuki
 *
 */
@Service
public class SpringBatchService {

	/**
	 * タスクのバッチ処理用.
	 * 
	 * initialDelay = 指定時間後に最初のtaskを開始する。 fixedRate =
	 * taskの実行開始時点から指定時間後に次のtaskを実行する. 単位はms.
	 */
	@Scheduled(initialDelay = 60000, fixedRate = 5000)
	public void sampleBatch() {
		System.out.println("Hello Batch!");
	}
}
