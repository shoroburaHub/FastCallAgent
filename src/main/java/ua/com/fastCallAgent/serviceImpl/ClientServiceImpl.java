package ua.com.fastCallAgent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.fastCallAgent.entity.Client;
import ua.com.fastCallAgent.repository.ClientRepository;
import ua.com.fastCallAgent.service.ClientService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by koko on 31.08.16.
 */
@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository сlientRepository;

    @Override
    public void save(Client client) {
        сlientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = сlientRepository.findAll();

        LocalDate tmp =null;
        for (Client client : clients) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate dateTime =
                    LocalDate.parse(client.getDateTime()
                            .substring(6),
                            formatter);
            if(tmp!=null){
                if(tmp.compareTo(dateTime)!=0) {
                    client.setLast(true);
                }
            }
            tmp = dateTime;
        }
        return clients;
    }

    @Override
    public Client findOne(int id) {
        return сlientRepository.findOne(id);
    }

    @Override
    public void delete(int id) {
        сlientRepository.delete(id);
    }
}
