package com.tieto.geekoff.library.dao;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class httpPost {

    public static void main(String[] args) throws IOException {

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            HttpPost request = new HttpPost("http://localhost:5000/face");
            request.setHeader("User-Agent", "Java client");


            List<NameValuePair> params = new ArrayList<>();

            // Image from database
            byte[] fileContent = FileUtils.readFileToByteArray(new File("/Users/raul/IdeaProjects/TeamNumber2/src/main/java/com/tieto/geekoff/library/dao/reet.jpg"));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            System.out.println(encodedString);
            // Image from login
            byte[] fileContentLogin = FileUtils.readFileToByteArray(new File("/Users/raul/IdeaProjects/TeamNumber2/src/main/java/com/tieto/geekoff/library/dao/raul.jpg"));
            String encodedStringLogin = Base64.getEncoder().encodeToString(fileContentLogin);
            

            params.add(new BasicNameValuePair("database", encodedString));
            params.add(new BasicNameValuePair("login", encodedStringLogin));
            request.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse response = client.execute(request);

            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = bufReader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }

            System.out.println(builder);
        }
    }
}



