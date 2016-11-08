package ua.com.fastCallAgent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.fastCallAgent.entity.Client;

/**
 * Created by koko on 30.08.16.
 */

public interface ClientRepository extends JpaRepository<Client, Integer> {



}
