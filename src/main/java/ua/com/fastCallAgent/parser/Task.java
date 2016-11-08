package ua.com.fastCallAgent.parser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ua.com.fastCallAgent.entity.Client;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by koko on 18.08.16.
 */
public class Task {

    static FastCallAgentParser fastCallAgentParser = new FastCallAgentParser();

    public static void execute() {

        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost/fastCallAgent?useUnicode=yes&characterEncoding=utf8", "root", "root");

            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into Client(status,phone,dateTime,callCount) VALUES (?,?,?,?)");


            List<Client> clientsFromLink = fastCallAgentParser.parse("tirseo2015@gmail.com", "kop198923");

            ResultSet resultSet = preparedStatement.executeQuery("SELECT id, status, phone, dateTime, callCount FROM Client");

            List<Client> clientsFromDataBase = new ArrayList<>();

            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setStatus(resultSet.getString("status"));
                client.setPhone(resultSet.getString("phone"));
                client.setDateTime(resultSet.getString("dateTime"));
                client.setCallCount(resultSet.getInt("callCount"));

                clientsFromDataBase.add(client);
            }

            for (Client client : clientsFromLink) {
                if (clientsFromDataBase.contains(client)) {

                } else {
                    clientsFromDataBase.add(client);
                }
            }

            for (Client client : clientsFromDataBase) {

                if (client.getId() == 0) {
                    System.out.println("new");
                    preparedStatement.setString(1, client.getStatus());
                    preparedStatement.setString(2, client.getPhone());
                    preparedStatement.setString(3, client.getDateTime());
                    preparedStatement.setInt(4, client.getCallCount());

                    preparedStatement.execute();
                }

            }
            clientsFromDataBase.clear();
            clientsFromLink.clear();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


