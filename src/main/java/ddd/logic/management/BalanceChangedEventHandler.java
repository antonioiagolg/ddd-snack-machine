package ddd.logic.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import ddd.logic.atm.BalanceChangedEvent;

@Component
public class BalanceChangedEventHandler implements ApplicationListener<BalanceChangedEvent>{
	
	@Autowired
	private HeadOfficeInstance headOfficeInstance;
	@Autowired
	private HeadOfficeRepository headOfficeRepository;

	@Override
	public void onApplicationEvent(BalanceChangedEvent event) {
		HeadOfficeDto headOfficeDto = headOfficeInstance.getInstance();
		HeadOffice headOffice = headOfficeDto.convertToHeadOffice();
		headOffice.changeBalance(event.getDelta());
		headOfficeRepository.save(headOffice.convertToHeadOfficeDto());
	}

}
