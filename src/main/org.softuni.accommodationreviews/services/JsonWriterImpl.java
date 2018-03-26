package org.softuni.accommodationreviews.services;

import com.google.gson.Gson;
import org.softuni.accommodationreviews.entities.Town;
import org.softuni.accommodationreviews.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class JsonWriterImpl implements JsonWriter {

    private static final String path = System.getProperty("user.dir");
    private static final String FILE_PATH = path + "\\src\\resources\\static\\json\\towns.json";
    private final TownRepository townRepository;

    Gson gson = new Gson();

    @Autowired
    public JsonWriterImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public String jsonInString() {
        StringBuilder sb = new StringBuilder();
        String json = "";
        List<Town> towns = this.townRepository.findAll();

        for (int i = 0; i < towns.size(); i++) {
             Town town = towns.get(i);
             json = gson.toJson(town);
             sb.append(json);
             sb.append(System.getProperty("line.separator"));
        }
        System.out.println(path);
        System.out.println(sb.toString());

        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            writer.write(sb.toString());
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
