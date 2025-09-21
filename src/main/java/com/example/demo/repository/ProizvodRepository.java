package com.example.demo.repository;

import com.example.demo.model.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {

    @Modifying
    @Query("UPDATE Proizvod p SET p.kolicina = :kolicina WHERE p.id = :id")
    void updateKolicinaById(@Param("id") Long id, @Param("kolicina") int kolicina);

}
