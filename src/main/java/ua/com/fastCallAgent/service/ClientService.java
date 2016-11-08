package ua.com.fastCallAgent.service;

import ua.com.fastCallAgent.entity.Client;

import java.util.List;

/**
 * Created by koko on 31.08.16.
 */
public interface ClientService {

    void save(Client client);
    List<Client> findAll();
    Client findOne(int id);
    void delete(int id);

}
