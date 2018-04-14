package org.softuni.accommodationreviews.areas.towns.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.softuni.accommodationreviews.areas.cloud.CloudImageUploader;
import org.softuni.accommodationreviews.areas.towns.Town;
import org.softuni.accommodationreviews.areas.towns.TownRepository;
import org.softuni.accommodationreviews.areas.towns.models.TownServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.softuni.accommodationreviews.areas.towns.townConstants.SVG_PATH;

@Service
@Transactional
public class TownWriterToJsonImpl implements TownWriterToJson {

    private static final String path = System.getProperty("user.dir");
    private static final String FILE_PATH = path + "\\src\\resources\\static\\json\\towns.json";
    private final TownRepository townRepository;
    private final ModelMapper mapper;
    private final Gson gson = new Gson();
    private final CloudImageUploader cloudImageUploader;

    @Autowired
    public TownWriterToJsonImpl(TownRepository townRepository, ModelMapper mapper, CloudImageUploader cloudImageUploader) {
        this.townRepository = townRepository;
        this.mapper = mapper;
        this.cloudImageUploader = cloudImageUploader;
    }

    @Override
    public void writeTownsInJson() {
        StringBuilder outputJsonText = new StringBuilder();
        List<Town> towns = this.townRepository.findAll();

        outputJsonText.append(this.initialMapFeedLoad());
        if (!towns.isEmpty()) {
            for (int i = 0; i < towns.size(); i++) {
                TownServiceModel town = this.mapper.map(towns.get(i), TownServiceModel.class);
                if (i == 0) {
                    outputJsonText.setLength(outputJsonText.length() - 3);
                    outputJsonText.append(",").append(System.getProperty("line.separator"));
                    outputJsonText.append("  \"images\": [ {").append(System.getProperty("line.separator"));
                }
                outputJsonText.append("    \"svgPath\": \"").append(SVG_PATH).append("\",").append(System.getProperty("line.separator"));
                outputJsonText.append("    \"zoomLevel\": 5,").append(System.getProperty("line.separator"));
                outputJsonText.append("    \"scale\": 0.5,").append(System.getProperty("line.separator"));
                outputJsonText.append("    \"title\": \"").append(town.getTitle()).append("\",").append(System.getProperty("line.separator"));
                outputJsonText.append("    \"latitude\": ").append(town.getLatitude()).append(",").append(System.getProperty("line.separator"));
                outputJsonText.append("    \"longitude\": ").append(town.getLongitude()).append(System.getProperty("line.separator"));
                outputJsonText.append("  }, {").append(System.getProperty("line.separator"));

                if (i == towns.size() - 1) {
                    outputJsonText.setLength(outputJsonText.length() - 8);
                    outputJsonText.append("  } ]").append(System.getProperty("line.separator"));
                    outputJsonText.append("}");
                }
            }
        }

        this.writeAndFlush(outputJsonText);
    }

    void writeAndFlush(StringBuilder sb) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            writer.write(sb.toString());
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String initialMapFeedLoad() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(System.getProperty("line.separator"));
        sb.append("  \"map\": \"bulgariaHigh\",").append(System.getProperty("line.separator"));
        sb.append("  \"areas\": [ {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-01\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Благоевград\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-02\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Бургас\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-03\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Варна\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-04\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Велико Търново\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-05\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Видин\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-06\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Враца\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-07\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Габрово\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-08\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"обаст Добрич\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-09\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Кърджали\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-10\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Кюстендил\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-11\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Ловеч\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-12\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Монтана\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-13\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Пазарджик\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-14\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област област Перник\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-15\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Плевен\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-16\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Пловдив\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-17\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Разград\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-18\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Русе\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-19\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Силистра\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-20\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Сливен\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-21\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Смолян\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-22\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област София-град\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-23\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област София област\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-24\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Стара Загора\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-25\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Търговище\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-26\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Хасково\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-27\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Шумен\"").append(System.getProperty("line.separator"));
        sb.append("  }, {").append(System.getProperty("line.separator"));
        sb.append("    \"id\": \"BG-28\",").append(System.getProperty("line.separator"));
        sb.append("    \"title\": \"област Ямбол\"").append(System.getProperty("line.separator"));
        sb.append("  } ]").append(System.getProperty("line.separator"));
        sb.append("}");

        return sb.toString();
    }
}
