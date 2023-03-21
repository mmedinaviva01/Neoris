package com.neoris.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neoris.app.entity.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long>{
	  @Query(value = "SELECT m.* FROM movement m inner join account a on m.idAccount = a.id inner join customer c on c.id = a.idCustomer where c.id = ?1 and m.date <= ?2", nativeQuery = true)
	  public List<Movement> getMovementByDate(Long id, Date date);
}
