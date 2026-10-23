package com.devsuperior.clientscrud.repositories;

import com.devsuperior.clientscrud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
