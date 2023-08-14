package com.power.Balcony.power.station.Powerstation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/balconypowerstation")
public class PowerstationController {

    private final PowerstationService powerstationService;

    @Autowired
    public PowerstationController(PowerstationService powerstationService) {
        this.powerstationService = powerstationService;
    }


    @GetMapping
    public List<Powerstation> getPowerstations() {
        return powerstationService.getPowerStations();
    }

    @PostMapping
    public void registerNewPowerstation(@RequestBody Powerstation powerstation) {
        powerstationService.addNewPowerStation(powerstation);
    }

    @DeleteMapping(path = "{powerStationId}")
    public void deletePowerstation(
            @PathVariable("powerStationId") Long powerStationId) {
        powerstationService.deletePowerStation(powerStationId);
    }

    @PutMapping(path = "{powerStationId}")
    public void updatePowerstation(
            @PathVariable("powerStationId") Long powerStationId,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) Integer peakPanelPower,
            @RequestParam(required = false) Integer peakInverterPowerLimit,
            @RequestParam(required = false) LocalDate dayOfRegistration){
        powerstationService.updatePowerstation(powerStationId, manufacturer, peakPanelPower, peakInverterPowerLimit, dayOfRegistration);
    }


}
