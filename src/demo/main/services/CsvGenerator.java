package demo.main.services;

import demo.main.entities.Sell;
import demo.main.repositories.SellRepository;
import demo.main.repositories.SellRepositoryImpl;
import demo.main.xmlDataAccess.XMLFilePAth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 15.04.2017.
 */
public class CsvGenerator extends ReportGenerator {

    @Override
    public void generateReport(List<Sell> sells) throws IOException {

        String csvFile = "reports/csv/" + LocalDate.now().toString() + ".csv";
        File file = new File(csvFile);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            file.createNewFile();
        FileWriter writer = new FileWriter(csvFile);

        List<String> columnTitles = new ArrayList<>();
        columnTitles.add("Sell Id");
        columnTitles.add("Book Id");
        columnTitles.add("Quantity");
        CsvGeneratorUtils.writeLine(writer,columnTitles);

        for(int i=0; i<sells.size(); i++){
            List<String> fields = new ArrayList<>();
            fields.add(Integer.toString(sells.get(i).getId()));
            fields.add(Integer.toString(sells.get(i).getBookId()));
            fields.add(Integer.toString(sells.get(i).getQuantity()));
            CsvGeneratorUtils.writeLine(writer, fields);
        }
        writer.flush();
        writer.close();
    }
}
