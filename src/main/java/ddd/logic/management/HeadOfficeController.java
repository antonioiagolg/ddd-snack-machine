package ddd.logic.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ddd.logic.atm.Atm;
import ddd.logic.atm.AtmDto;
import ddd.logic.atm.AtmRepository;
import ddd.logic.snackmachine.SnackMachine;
import ddd.logic.snackmachine.SnackMachineDto;
import ddd.logic.snackmachine.SnackMachineRepository;

@RestController
@RequestMapping("/headoffice")
public class HeadOfficeController {
	
	@Autowired
	private SnackMachineRepository snackMachineRepository;
	@Autowired
	private AtmRepository atmRepository;
	@Autowired
	private HeadOfficeRepository headOfficeRepository;
	@Autowired
	private HeadOfficeInstance headOfficeInstance;
	
	@GetMapping
	public HeadOfficeDto getHeadOffice() {
		return headOfficeInstance.getInstance();
	}
	
	@PutMapping("/{atmId}/loadcash")
	public void loadCashToAtm(@PathVariable("atmId") long atmId) {
		AtmDto atmDto = atmRepository.findById(atmId).orElse(null);
		HeadOfficeDto headOfficeDto = headOfficeInstance.getInstance();
		
		Atm atm = atmDto.convertToAtm();
		HeadOffice headOffice = headOfficeDto.convertToHeadOffice();
		headOffice.loadCashToAtm(atm);
		atmRepository.save(atm.convertToAtmDto());
		headOfficeRepository.save(headOffice.convertToHeadOfficeDto());
	}
	
	@PutMapping("/{snackMachineId}/unloadcash")
	public void unloadCash(@PathVariable("snackMachineId") long snackMachineId) {
		SnackMachineDto snackMachineDto = snackMachineRepository.findById(snackMachineId).orElse(null);
		HeadOfficeDto headOfficeDto = headOfficeInstance.getInstance();
		
		SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
		HeadOffice headOffice = headOfficeDto.convertToHeadOffice();
		
		headOffice.unloadCashFromSnackMachine(snackMachine);
		snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
		headOfficeRepository.save(headOffice.convertToHeadOfficeDto());
	}
	
}
