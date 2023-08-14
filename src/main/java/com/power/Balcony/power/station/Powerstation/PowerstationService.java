package com.power.Balcony.power.station.Powerstation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PowerstationService {
    private final PowerstationRepository powerstationRepository;

    @Autowired
    public PowerstationService(PowerstationRepository powerstationRepository) {
        this.powerstationRepository = powerstationRepository;
    }

    public List<Powerstation> getPowerStations() {
        return powerstationRepository.findAll();
    }

    public void addNewPowerStation(Powerstation powerstation) {
        Optional<Powerstation> powerStationOptional = powerstationRepository
                .findPowerstationByManufacturer(powerstation.getManufacturer());
        if(powerStationOptional.isPresent()) {
            throw new IllegalStateException("Cannot register more than one system for a specific manufacturer");
        }
        powerstationRepository.save(powerstation);
        System.out.println(powerstation);
    }

    public void deletePowerStation(Long powerStationId) {
        boolean exists = powerstationRepository.existsById(powerStationId);
        if(!exists) {
            throw new IllegalStateException("power station with id " + powerStationId + " does not exist");
        }
        powerstationRepository.deleteById(powerStationId);
    }

    @Transactional
    public void updatePowerstation(Long powerstationId,
                                   String manufacturer,
                                   Integer panelPeakPower,
                                   Integer inverterPowerLimit,
                                   LocalDate dayOfRegistration) {
        Powerstation powerstation = powerstationRepository.findById(powerstationId)
                .orElseThrow(() -> new IllegalStateException(
                        "powerstation with id " + powerstationId + "does not exist"));

        if(manufacturer != null &&
                !manufacturer.isEmpty() &&
                !Objects.equals(powerstation.getManufacturer(), manufacturer)) {

            Optional<Powerstation> powerstationOptional = powerstationRepository
                    .findPowerstationByManufacturer(manufacturer);
            if(powerstationOptional.isPresent()) {
                throw new IllegalStateException("manufacturer is already existing. You can add only one system from a specific manufacturer");
            }

            powerstation.setManufacturer(manufacturer);
        }

        if(panelPeakPower != null &&
                !Objects.equals(powerstation.getPanelPeakPowerOutput(), panelPeakPower)) {
            powerstation.setPanelPeakPowerOutput(panelPeakPower);
        }

        if(inverterPowerLimit != null &&
                !Objects.equals(powerstation.getInverterPowerLimit(), inverterPowerLimit)) {
            powerstation.setInverterPowerLimit(inverterPowerLimit);
        }

    }


}
