package com.power.Balcony.power.station.Powerstation;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Entity
@Table
public class Powerstation {

    private static final Integer WARRANTYPERIODINYEARS = 15;

    @Id
    @SequenceGenerator(
            name = "powerstation_sequence",
            sequenceName = "powerstation_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "powerstation_sequence"
    )

    private Long id;
    private String manufacturer;
    private Integer panelPeakPowerOutput;
    private Integer inverterPowerLimit;
    private LocalDate dayOfRegistration;

    @Transient
    private Integer daysSinceRegistration;
    @Transient
    private Boolean underWarranty;


    public Powerstation() {
    }

    public Powerstation(Long id,
                        String manufacturer,
                        Integer panelPeakPowerOutput,
                        Integer inverterPowerLimit,
                        LocalDate dayOfRegistration) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.panelPeakPowerOutput = panelPeakPowerOutput;
        this.inverterPowerLimit = inverterPowerLimit;
        this.dayOfRegistration = dayOfRegistration;
    }

    public Powerstation(String manufacturer,
                        Integer panelPeakPowerOutput,
                        Integer inverterPowerLimit,
                        LocalDate dayOfRegistration) {
        this.manufacturer = manufacturer;
        this.panelPeakPowerOutput = panelPeakPowerOutput;
        this.inverterPowerLimit = inverterPowerLimit;
        this.dayOfRegistration = dayOfRegistration;
    }

    public Boolean getUnderWarranty() {
        return this.dayOfRegistration != null && Period.between(this.dayOfRegistration, LocalDate.now()).getYears() < WARRANTYPERIODINYEARS;
    }

    public void setUnderWarranty(Boolean underWarranty) {
        this.underWarranty = underWarranty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getPanelPeakPowerOutput() {
        return panelPeakPowerOutput;
    }

    public void setPanelPeakPowerOutput(Integer panelPeakPowerOutput) {
        this.panelPeakPowerOutput = panelPeakPowerOutput;
    }

    public Integer getInverterPowerLimit() {
        return inverterPowerLimit;
    }

    public void setInverterPowerLimit(Integer inverterPowerLimit) {
        this.inverterPowerLimit = inverterPowerLimit;
    }

    public LocalDate getDayOfRegistration() {
        return dayOfRegistration;
    }

    public void setDayOfRegistration(LocalDate dayOfRegistration) {
        this.dayOfRegistration = dayOfRegistration;
    }

    public Long getDaysSinceRegistration() {
        return ChronoUnit.DAYS.between(this.dayOfRegistration, LocalDate.now());
    }

    public void setDaysSinceRegistration(Integer daysSinceRegistration) {
        this.daysSinceRegistration = daysSinceRegistration;
    }

    @Override
    public String toString() {
        return "Powerstation{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", panelPeakPowerOutput='" + panelPeakPowerOutput + '\'' +
                ", inverterPowerLimit='" + inverterPowerLimit + '\'' +
                ", dayOfRegistration=" + dayOfRegistration +
                ", daysSinceRegistration=" + daysSinceRegistration +
                '}';
    }
}
