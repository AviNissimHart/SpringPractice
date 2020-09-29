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

import com.qa.practicespring.dto.GuitaristDTO;
import com.qa.practicespring.service.GuitaristService;

// localhost:8901/guitarist/<whatever-crud-function>

@RestController
@RequestMapping("/guitarist")
public class GuitaristController {

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
	private GuitaristService service;

	
	@Autowired
	// Constructor autowiring:
	//
	// Spring wires the controller up to the service at the moment the controller is created
	// if the autowiring fails, then our controller object never gets created!
	// this causes fewer exceptions - if we want to make sure our autowiring has worked,
	// all we need to do is check if the controller exists!
	
	public GuitaristController(GuitaristService service) {
		super();
		this.service = service;
	}
	
	
	// create
	@PostMapping("/create")
	public ResponseEntity<GuitaristDTO> create(@RequestBody GuitaristDTO guitaristDTO) {
		// creates the entitiy and passes it back (through Service to Repo to Domain to DB)
		GuitaristDTO created = this.service.createGuitarist(guitaristDTO);
		
		// returns the entitiy in a ResponseEntity format, which converts it to JSON
		// so we can see it
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		
	}
	
	// readAll
	@GetMapping("/readAll")
	public ResponseEntity<List<GuitaristDTO>> getAllGuitarists() {
		return ResponseEntity.ok(this.service.readAllGuitarists());
	}
	
	// readById
	@GetMapping("/read/{id}")
	public ResponseEntity<GuitaristDTO> getGuitaristById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getGuitaristById(id));
	}
	
	
	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<GuitaristDTO> updateGuitaristById(@PathVariable Long id,
			@RequestBody GuitaristDTO guitaristDTO) {
		GuitaristDTO updated = this.service.updateGuitaristById(guitaristDTO, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	
	// delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GuitaristDTO> deleteGuitaristById(@PathVariable Long id) {
		// return the boolean result of the delete function
		// UNLESS the HTTP status returned is NO_CONTENT (Error 204), in which case throw 
		// an Internal Service Error (Error 500)
		return this.service.deleteGuitaristById(id)
				? new ResponseEntity<>(HttpStatus.NO_CONTENT) // 204
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
	}
}
