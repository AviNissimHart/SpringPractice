package com.qa.practicespring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.practicespring.dto.BandDTO;
import com.qa.practicespring.service.BandService;

// localhost:8901/band/<whatever-crud-function>

@RestController
@RequestMapping("/band")
public class BandController {

	// Re	- Representational
	// S	- State
	// T	- Transfer
	
	
	//@Autowired 
	// Field autowiring:
	//
	// spring reflects in a setter method which we cant see (e.g. setService() )
	// this is run AFTER the object gets created
	// if the setter method fails, we end up with a controller that isnt wired up
	// to the service right causing exceptions later on
	private BandService service;

	
	@Autowired
	// Constructor autowiring:
	//
	// Spring wires the controller up to the service at the moment the controller is created
	// if the autowiring fails, then our controller object never gets created!
	// this causes fewer exceptions - if we want to make sure our autowiring has worked,
	// all we need to do is check if the controller exists!
	
	public BandController(BandService service) {
		super();
		this.service = service;
	}
	
	
	// create
	@PostMapping("/create")
	public ResponseEntity<BandDTO> create(@RequestBody BandDTO bandDTO) {
		// creates the entitiy and passes it back (through Service to Repo to Domain to DB)
		BandDTO created = this.service.createBand(bandDTO);
		
		// returns the entitiy in a ResponseEntity format, which converts it to JSON
		// so we can see it
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		
	}
	
	// readAll
	@GetMapping("/readAll")
	public ResponseEntity<List<BandDTO>> getAllBands() {
		return ResponseEntity.ok(this.service.readAllBands());
	}
	
	// readById
	@GetMapping("/read/{id}")
	public ResponseEntity<BandDTO> getBandById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getBandById(id));
	}
	
	
	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<BandDTO> updateBandById(@PathVariable Long id,
			@RequestBody BandDTO bandDTO) {
		BandDTO updated = this.service.updateBandById(bandDTO, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	
	// delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BandDTO> deleteBandById(@PathVariable Long id) {
		// return the boolean result of the delete function
		// UNLESS the HTTP status returned is NO_CONTENT (Error 204), in which case throw 
		// an Internal Service Error (Error 500)
		return this.service.deleteBandById(id)
				? new ResponseEntity<>(HttpStatus.NO_CONTENT) // 204
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
	}
}
