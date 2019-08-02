package com.stackroute.SpringBootTask.controller;

import com.stackroute.SpringBootTask.domain.Muzix;
import com.stackroute.SpringBootTask.exceptions.TrackAlreadyExistsException;
import com.stackroute.SpringBootTask.exceptions.TrackNotFoundException;
import com.stackroute.SpringBootTask.services.MuzixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MuzixController {

    MuzixService muzixService;

    @Autowired
    public MuzixController(MuzixService muzixService){
        this.muzixService = muzixService;
    }


    @PostMapping("/muzix")

    public ResponseEntity<?> saveTrack(@RequestBody Muzix muzix) throws TrackAlreadyExistsException {
        Muzix savedMuzix = null;

      try {
        savedMuzix = muzixService.saveTrack(muzix);
        }
        catch (TrackAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(savedMuzix, HttpStatus.CREATED);
    }

    /* This method will retrive musix by using Query parameter */

    @GetMapping("/muzix")

    public ResponseEntity<List<Muzix>> getAllTracks() {

        List<Muzix> musixes = muzixService.getAllTracks();
        return new ResponseEntity<List<Muzix>>(musixes, HttpStatus.OK);


    }

    @GetMapping("/muzix/{id}")

    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
        Muzix muzix = null;

       try {
        muzix = muzixService.getTrackById(id);
         }
        catch(TrackNotFoundException t){
            return new ResponseEntity<>(t.getMessage(),HttpStatus.CONFLICT);
        }

        return new ResponseEntity<Muzix>(muzix, HttpStatus.OK);
    }

    /*
     This method will delete data by id
     */

    @DeleteMapping("/muzix/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) throws TrackNotFoundException {
      Muzix muzix;
      muzix = muzixService.getTrackById(id);
        muzixService.deleteTrackById(id);
        return new ResponseEntity(muzix, HttpStatus.OK);
    }

    /*
    This method will update data by id
     */

    @PutMapping("/muzix/{id}")
    public ResponseEntity<Muzix> updateTrackById(@RequestBody Muzix muzix, @PathVariable int id) {

        if (muzixService.updateTrackById(muzix, id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Muzix>> getTrackByname(@PathVariable String name) {
        List<Muzix> musix = muzixService.getTrackBYName(name);
        return new ResponseEntity<List<Muzix>>(musix, HttpStatus.CREATED);
    }

}

