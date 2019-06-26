package ddd.logic.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeadOfficeInstance {
	
	@Autowired
	private HeadOfficeRepository headOfficeRepository;
	private static long headOfficeId = 1;
	
	private HeadOfficeDto getInstance() {
		return headOfficeRepository.findById(headOfficeId).orElse(null);
	}
}
