package br.com.alissoncostax.agibank.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServices {
	
	private static final String CRON_PROCESS_ANALYZE_FILE_DATA = "0/15 * * 1/1 * ?";
	
	@Scheduled(cron = CRON_PROCESS_ANALYZE_FILE_DATA)
	public void scheduleProcessingAnalyzeFileData() {
		DataAnalyzeAgibankFileService dataAnalyzeFileService = new DataAnalyzeAgibankFileService();
		dataAnalyzeFileService.init();
	}
	
}
