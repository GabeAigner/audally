package com.audally.backend.boundary;

import com.audally.backend.control.ProgressRepository;
import com.audally.backend.entity.Lesson;
import com.audally.backend.entity.Progress;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.time.Duration;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/progresses")
@Produces(APPLICATION_JSON)
@Transactional
@ApplicationScoped
public class ProgressResource {
    @Inject
    ProgressRepository progressRepository;
    @PUT
    @Path("{ProgressId}")
    public Response updateProgressToUser(@PathParam("ProgressId") Long pid, Progress progress){
        Progress found = progressRepository.findById(pid);
        found.getLesson();
        if(found == null){
            return Response
                    .status(204,"Progress was not found!")
                    .build();
        }
        Lesson l = found.getLesson();
        String[] parts = l.getDuration().toString().split(":");
        Duration duration = Duration
                .ofHours(Long.parseLong(parts[0]))
                .plusMinutes(Long.parseLong(parts[1]))
                .plusSeconds(Long.parseLong(parts[2]));
        if(!progress.isAlreadyListened()
                && progress.getProgressInSeconds()
                > (duration.getSeconds()-30)) {
            progress.setAlreadyListened(true);
        }
        if(found.isAlreadyListened()){
            found.copyProperties(progress);
            found.setAlreadyListened(true);
        }else found.copyProperties(progress);
        progressRepository.persist(found);
        return Response.ok(found).build();
    }
}
