package com.example.heroes.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.heroes.model.Heroe;
import com.example.heroes.repository.HeroeDao;

@RestController
@RequestMapping({"/heroes"})
public class HeroeController {

	@Autowired
	HeroeDao heroedao;
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<Object> findAll(){
		System.out.println("paso por findAll");
		
		List<Heroe> heroesFound = heroedao.findAll();

		if (heroesFound.size()>0) {	
			
			 for(Heroe her: heroesFound)
		     {
				 System.out.println("name:"+her.getNombre()+ her.getCasa());
		     }
		     
			 JSONObject obj = new JSONObject();
			 

		      obj.put("error", 0);
		      obj.put("results", heroesFound);
		      
		      
		      

		return ResponseEntity.ok().body(obj.toString());
		}else {
			JSONObject obj = new JSONObject();
			 
		   
			obj.put("error", 1);
			obj.put("description", "No data found");
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj.toString());
		}
//	  return daocontact.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = {"/nombre/{nom}"})
	public ResponseEntity<Object> findbyName(@PathVariable String nom){
		System.out.println("paso por findbyName");
		
		List<Heroe> heroesFound = heroedao.findByNombre(nom);

		if (heroesFound.size()>0) {	
			
			 for(Heroe her: heroesFound)
		     {
				 System.out.println("name:"+her.getNombre()+ her.getCasa());
		     }
		     
			 JSONObject obj = new JSONObject();
			 

		      obj.put("error", 0);
		      obj.put("results", heroesFound);
		      
		      
		      

		return ResponseEntity.ok().body(obj.toString());
		}else {
			JSONObject obj = new JSONObject();
			 
		   
			obj.put("error", 1);
			obj.put("description", "No data found");
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj.toString());
		}
//	  return daocontact.findAll();
	}
	
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Heroe> findById(@PathVariable long id){
		System.out.println("paso por /id");
		
	Heroe her = heroedao.findById(id).orElse(null);

	

	if (her!=null) {
	 System.out.println("encontro:"+her.getNombre()+ her.getCasa());
	
	
	return ResponseEntity.ok().body(her);
	}else return ResponseEntity.notFound().build();
		

}

	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public Heroe create(@RequestBody Heroe contact){
	    return heroedao.save(contact);
	    
	}
	
//	@PostMapping(path = {"/auth"})
//	public Contact create(@RequestHeader(name = "Authorization", required = true) String headerPersist, 
//			              @RequestBody Heroe heroe){
//	    return daoheroe.save(heroe);
	    
//	}
	
	 
	@PutMapping(value="/{id}")
	  public ResponseEntity<Heroe> update(@PathVariable("id") long id,
	                                        @RequestBody Heroe heroe){
		
		return heroedao.findById(id)
		    .map(record -> {
		    	record.setNombre(heroe.getNombre());
		    	record.setBio(heroe.getBio());
		    	record.setImg(heroe.getImg());
		    	record.setAparicion(heroe.getAparicion());
		    	record.setCasa(heroe.getCasa());
		       
		    	Heroe updated = heroedao.save(record);
		       
		        return ResponseEntity.ok().body(updated);
		   	
		    }).orElse(ResponseEntity.notFound().build());

	  }
	
	@DeleteMapping(path ={"/{id}"})
	  public ResponseEntity<?> delete(@PathVariable("id") long id) {
	    return heroedao.findById(id)
	        .map(record -> {
	            heroedao.deleteById(id);
	            return ResponseEntity.ok().build();
	        }).orElse(ResponseEntity.notFound().build());
	  }

}
