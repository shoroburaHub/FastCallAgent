package ua.com.fastCallAgent.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.fastCallAgent.entity.Client;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by koko on 30.08.16.
 */
public class FastCallAgentParser {


    public List<Client> parse(String email, String password) throws IOException {

        Connection.Response loginForm = Jsoup.connect("http://fastcallagent.com.ua/login/")
                .data("cookieexists", "false")
                .data("email", email)
                .data("password", password)
                //.cookies(loginCookies)
                .method(Connection.Method.POST)
                .execute();

        //here `loginForm` connected to server with your credentials
        //and server returned response with cookies containing informations
        //required to continue session so you should store them
        //and reuse to access farther pages

        Map<String, String> loginCookies = loginForm.cookies();

        Document document2 = Jsoup.connect("http://fastcallagent.com.ua/history/?site=240")
                .cookies(loginCookies)
                .get();

        List<Client> clients = new ArrayList<>();


        //data_time call
        String [] data = new String[document2.select("td[style=text-align:center]").size()];

        for (int i = 0; i < document2.select("td[style=text-align:center]").size(); i++) {
            data[i] = document2.select("td[style=text-align:center]").get(i).text().split("\n")[0];
        }
        int index = 0;
        for (int i = 0; i < data.length; i+=2) {
            Client client = new Client();
            client.setDateTime(data[i]);
            clients.add(client);
            index++;
        }

        // phone call

        int count = 0;
        for (int i = 0; i < document2.select("td[style=white-space:nowrap;]").size(); i++) {
            String phone = document2.select("td[style=white-space:nowrap;]").get(i).text().split(" ")[0];

          if(phone.isEmpty()){
          }else{
                clients.get(count).setPhone(phone);
                 count++;
           }
         }



            // get status about the call
       Element element =null;

        for(int i = 0; i < document2.select("tbody tr td.call-status").size(); i++){
            element = document2.select("tbody tr td.call-status").get(i);

            clients.get(i).setStatus(element.text());
        }

        List<Client>clientList = new ArrayList<>();

        for (int i = 0; i < clients.size(); i++) {
            for (int j = i+1; j < clients.size(); j++) {
                if(clients.get(i).getPhone().equals(clients.get(j).getPhone())){
                    clients.get(i).setCallCount(clients.get(i).getCallCount()+1);
//                    System.out.println("removed "+ clients.remove(j));
                }
            }
        }


//        System.out.println("sort list");
//        for (Client client : clients) {
//            System.out.println(client);
//        }

        return clients;

    }


}
