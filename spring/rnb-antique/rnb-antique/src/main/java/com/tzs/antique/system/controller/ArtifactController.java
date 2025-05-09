package com.tzs.antique.system.controller;

import com.tzs.antique.system.entity.Artifact;
import com.tzs.antique.system.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artifacts")
public class ArtifactController {

    @Autowired
    private ArtifactService artifactService;

    @GetMapping
    public List<Artifact> listArtifacts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String period,
            @RequestParam(required = false) String museum,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        return artifactService.findAll(search, type, period, museum, sortBy, order);
    }
}
