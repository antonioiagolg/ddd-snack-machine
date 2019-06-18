package ddd.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snackmachines")
public class SnackMachineController {
	
	@Autowired
	SnackMachineRepository snackMachineRepository;
	
	@GetMapping
	public List<SnackMachineDto> getSnackMachines() {
		List<SnackMachineDto> list = new ArrayList<>();
		
		snackMachineRepository.findAll().forEach(list::add);
		
		return list;
	}
	
	@GetMapping("/{id}")
	public SnackMachineDto getSnackMachine(@PathVariable("id") long id) {
		return snackMachineRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/{id}/moneyInTransaction/{coinOrNote}")
	public void insertCoinOrNote(@PathVariable("id") long id, @PathVariable("coinOrNote") String coinOrNote) {
		
		SnackMachineDto snackMachineDto = snackMachineRepository.findById(id).orElse(null);
		SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
		
		if(coinOrNote.equalsIgnoreCase("Cent"))
			snackMachine.insertMoney(Money.Cent);
		else if(coinOrNote.equalsIgnoreCase("TenCent"))
			snackMachine.insertMoney(Money.TenCent);
		else if(coinOrNote.equalsIgnoreCase("Quarter"))
			snackMachine.insertMoney(Money.Quarter);
		else if(coinOrNote.equalsIgnoreCase("Dollar"))
			snackMachine.insertMoney(Money.Dollar);
		else if(coinOrNote.equalsIgnoreCase("FiveDollar"))
			snackMachine.insertMoney(Money.FiveDollar);
		else if(coinOrNote.equalsIgnoreCase("TwentyDollar"))
			snackMachine.insertMoney(Money.TwentyDollar);
		
		snackMachineDto = snackMachine.convertToSnackMachineDto();
		snackMachineRepository.save(snackMachineDto);
	}
	
	@PutMapping("/{id}/moneyInTransaction")
	public void returnMoney(@PathVariable("id") long id) {
		
		SnackMachineDto snackMachineDto = snackMachineRepository.findById(id).orElse(null);
		SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
		
		snackMachine.returnMoney();
		
		snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
	}
	
	@PutMapping("/{id}/{slotNumber}")
	public void buySnack(@PathVariable("id") long id, @PathVariable("slotNumber") int slotNumber) {
		SnackMachineDto snackMachineDto = snackMachineRepository.findById(id).orElse(null);
		SnackMachine snackMachine = snackMachineDto.convertToSnackMachine();
		snackMachine.buySnack(slotNumber);
		snackMachineRepository.save(snackMachine.convertToSnackMachineDto());
	}
}
