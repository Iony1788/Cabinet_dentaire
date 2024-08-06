/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dentisterie2.dentisterie2.repository;

import com.dentisterie2.dentisterie2.model.Dent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author TOAVINA
 */
@Repository
public interface DentRepository extends JpaRepository<Dent, Long>{
    
}
