package com.power.Balcony.power.station.Powerstation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PowerstationRepository extends JpaRepository<Powerstation, Long> {

    // @Query("SELECT s FROM Powerstation s WHERE s.manufacturer = ?1")

    // SELECT * FROM balconyPowerStation WHERE email = ?

    Optional<Powerstation> findPowerstationByManufacturer(String manufacturer);
}
