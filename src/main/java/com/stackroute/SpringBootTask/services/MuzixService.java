package com.stackroute.SpringBootTask.services;

import com.stackroute.SpringBootTask.domain.Muzix;
import com.stackroute.SpringBootTask.exceptions.TrackAlreadyExistsException;
import com.stackroute.SpringBootTask.exceptions.TrackNotFoundException;


import java.util.List;

public interface MuzixService {

    public Muzix saveTrack(Muzix musix) throws TrackAlreadyExistsException;

    public List<Muzix> getAllTracks();

    public Muzix getTrackById(int id) throws TrackNotFoundException;

    public Muzix deleteTrackById(int id) throws TrackNotFoundException;

    public boolean updateTrackById(Muzix musix, int id);

    public List<Muzix> getTrackBYName(String name);


}
