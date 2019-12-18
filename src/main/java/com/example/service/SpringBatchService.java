package com.example.service;

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
	//@Scheduled(initialDelay = 6000000, fixedRate = 5000)
	public void sampleBatch() {
		System.out.println("バッチ処理を行っています\n サーバー起動後、60秒経つと実行が開始し、その後は5秒ごとに実行されます。");
	}
}
