/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hu.fnt.gsos.rest;

/**
 *
 * @author Jeroen
 */
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import nl.hu.fnt.gsos.service.Track;
import nl.hu.fnt.gsos.service.TrackServiceImpl;
 
@Path("/trackservice")
public class TrackService {
 
	@GET
	@Path("/tracks")
	public Response getTracks() 
        {
		String output = "List of all tracks: ";
                TrackServiceImpl tsi = new TrackServiceImpl();
                for(Track t : tsi.getTracks())
                {
                    output = output + " /n t" + t.toString();
                }
		return Response.status(200).entity(output).build();
	}
 
        @GET
	@Path("/tracks/count")
	public Response getTrackcount() 
        {
                TrackServiceImpl tsi = new TrackServiceImpl();  
                String output = "currently there are " + tsi.getTracks().size() + " tracks.";
		return Response.status(200).entity(output).build();
	}
        
        @GET
        @Path("/tracks/{id}")
        public Response getTrackById(@PathParam("id")int id)
        {
            TrackServiceImpl tsi = new TrackServiceImpl();
            String output = tsi.getTrackById(id).toString();
            return Response.status(200).entity(output).build();
        }
        
        @PUT
        @Path("/tracks/{id}/{artist}")
        public Response setTrackArtist(@PathParam("id")int id, @PathParam("artist")String artist)
        {
            TrackServiceImpl tsi = new TrackServiceImpl();
            
            tsi.getTrackById(id).setArtist(artist);
            
            String output = tsi.getTrackById(id).toString();
            return Response.status(200).entity(output).build();
        }
        
        @DELETE
        @Path("/tracks/{id}")
        public Response deleteTrackById(@PathParam("id")int id)
        {
            TrackServiceImpl tsi = new TrackServiceImpl();
            tsi.remove(id);
            String output = "Track " + id + "is removed";
            return Response.status(200).entity(output).build();
        }
}
