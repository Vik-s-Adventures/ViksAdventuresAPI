package com.upc.ViksAdventures.first_world.api.rest;

import com.upc.ViksAdventures.first_world.domain.model.Tip;
import com.upc.ViksAdventures.first_world.domain.service.TipService;
import com.upc.ViksAdventures.first_world.mapping.TipMapper;
import com.upc.ViksAdventures.first_world.resource.TipResource;
import com.upc.ViksAdventures.first_world.resource.CreateTipResource;
import com.upc.ViksAdventures.first_world.resource.UpdateTipResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tips", produces = "application/json")
public class TipController {

    private final TipService tipService;
    private final TipMapper mapper;

    @Autowired
    public TipController(TipService tipService, TipMapper mapper) {
        this.tipService = tipService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TipResource>> getAllTips() {
        List<Tip> tips = tipService.getAll();
        List<TipResource> resources = mapper.toResourceList(tips);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipResource> getTipById(@PathVariable Long id) {
        Tip tip = tipService.getById(id);
        TipResource resource = mapper.toResource(tip);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TipResource> createTip(@RequestBody CreateTipResource resource) {
        Tip tip = mapper.toModel(resource);
        Tip savedTip = tipService.create(tip);
        return new ResponseEntity<>(mapper.toResource(savedTip), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipResource> updateTip(@PathVariable Long id, @RequestBody UpdateTipResource resource) {
        Tip tip = mapper.toModel(resource);
        Tip updatedTip = tipService.update(id, tip);
        TipResource tipResource = mapper.toResource(updatedTip);
        return new ResponseEntity<>(tipResource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTip(@PathVariable Long id) {
        tipService.delete(id);
        return ResponseEntity.ok().build();
    }
}
