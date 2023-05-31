package com.emsi.tache_manage.services.files;

import com.emsi.tache_manage.entities.Employe;
import com.emsi.tache_manage.entities.Tache;
import com.emsi.tache_manage.enums.Priority;
import com.emsi.tache_manage.enums.Statut;
import com.emsi.tache_manage.services.TacheService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TacheFile {
    static TacheService tacheService = new TacheService();

    public static boolean ImportTacheExcel(){
        List<Tache> taches = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("C:/Users/Asus VivoBook 14/Desktop/Tache_Management/src/main/resources/com/emsi/tache_manage/TacheExcel.xlsx");
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String nom;
                if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                    nom = String.valueOf(row.getCell(1).getNumericCellValue());
                } else {
                    nom = row.getCell(1).getStringCellValue();
                }
                String description = row.getCell(2).getStringCellValue();
                Statut statut;
                Cell statutCell = row.getCell(3);
                if (statutCell.getCellType() == CellType.STRING) {
                    statut = Statut.valueOf(statutCell.getStringCellValue());
                } else {
                    statut = Statut.valueOf(statutCell.toString());
                }
                Priority priority;
                Cell priorityCell = row.getCell(4);
                if (priorityCell.getCellType() == CellType.STRING) {
                    priority = Priority.valueOf(priorityCell.getStringCellValue());
                } else {
                    priority = Priority.valueOf(priorityCell.toString());
                }
                Tache tache = new Tache(nom,description,statut,priority);
                taches.add(tache);
            }

            for(Tache tache:taches){
                tacheService.save(tache);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean ExportTacheExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Tâches");
        List<Tache> listTaches = tacheService.findAll();

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(1).setCellValue("Nom");
        headerRow.createCell(2).setCellValue("Description");
        headerRow.createCell(3).setCellValue("Statut");
        headerRow.createCell(4).setCellValue("Priorite");

        int rowNum = 1;
        for (Tache tache : listTaches) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(tache.getId());
            row.createCell(1).setCellValue(tache.getNom());
            row.createCell(2).setCellValue(tache.getDescription());
            row.createCell(3).setCellValue(String.valueOf(tache.getStatut()));
            row.createCell(4).setCellValue(String.valueOf(tache.getPriority()));
        }
        try (FileOutputStream outputStream = new FileOutputStream("C:/Users/Asus VivoBook 14/Desktop/Tache_Management/src/main/resources/com/emsi/tache_manage/TacheExcel.xlsx")) {
            workbook.write(outputStream);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    public void ImportTacheJson() throws IOException {
        try {
            List<String> lines = Files.readAllLines(Paths.get("C:/Users/Asus VivoBook 14/Desktop/Tache_Management/src/main/resources/com/emsi/tache_manage/TacheJson.json"));
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line);
            }
            String data = sb.toString();
            String jsonData = data;
            JSONArray tacheArray = new JSONArray(jsonData);

            for (int i = 0; i < tacheArray.length(); i++) {
                JSONObject tacheObj = tacheArray.getJSONObject(i);
                Integer id = tacheObj.getInt("id");
                String nom = tacheObj.getString("nom");
                String description = tacheObj.getString("description");
                String statutStr = tacheObj.getString("statut");
                Statut statut = Statut.valueOf(statutStr);
                String priorityStr = tacheObj.getString("priorite");
                Priority priority = Priority.valueOf(priorityStr);

                Tache tache = new Tache(nom, description, statut, priority);

                /*for (Tache tache1:tacheService.findAll()){
                    if(!tache.getNom().equals(tache1.getNom()) && !tache.getDescription().equals(tache1.getDescription()) && !tache.getStatut().equals(tache1.getStatut()) && !tache.getPriority().equals(tache1.getPriority())){
                        tacheService.save(tache);
                    }
                }*/

                tacheService.save(tache);
            }
            ExportTacheJson();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean ExportTacheJson() {
        List<Tache> taches = tacheService.findAll();
        JSONArray tachesJsonArray = new JSONArray();
        for (Tache tache : taches) {
            JSONObject projetJson = new JSONObject();
            projetJson.put("id", tache.getId());
            projetJson.put("nom", tache.getNom());
            projetJson.put("description", tache.getDescription());
            projetJson.put("statut", tache.getStatut());
            projetJson.put("priorite", tache.getPriority());
            tachesJsonArray.put(projetJson);
        }

        String jsonString = tachesJsonArray.toString();
        try (FileWriter fileWriter = new FileWriter("C:/Users/Asus VivoBook 14/Desktop/Tache_Management/src/main/resources/com/emsi/tache_manage/TacheJson.json")) {
            fileWriter.write(jsonString);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }

    }

    public void ImportTacheTxt() {
        try {
            List<Tache> tacheList = new ArrayList<>();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Words");
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Asus VivoBook 14/Desktop/Tache_Management/src/main/resources/com/emsi/tache_manage/TacheTxt.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(";");
                if (words.length == 5) {
                    String nom = words[1];
                    String description = words[2];
                    String statutval = words[3];
                    Statut statut = Statut.valueOf(statutval);
                    String priorityval = words[4];
                    Priority priority = Priority.valueOf(priorityval);
                    Tache tache = new Tache(nom, description, statut, priority);
                    /*for (Tache tache1:tacheService.findAll()){
                        if(!tache.getNom().equals(tache1.getNom()) && !tache.getDescription().equals(tache1.getDescription()) && !tache.getStatut().equals(tache1.getStatut()) && !tache.getPriority().equals(tache1.getPriority())){
                            tacheService.save(tache);
                        }
                    }*/
                    tacheService.save(tache);
                }
            }
            reader.close();
            ExportTacheTxt();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public boolean ExportTacheTxt(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/Asus VivoBook 14/Desktop/Tache_Management/src/main/resources/com/emsi/tache_manage/TacheTxt.txt"))) {
            for (Tache tache : tacheService.findAll()) {
                writer.write(tache.getId()+";"+tache.getNom()+";"+tache.getDescription()+";"+tache.getStatut()+";"+tache.getPriority());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        return false;
    }



    public void viderandreinitialiser(){
        String url = "jdbc:mysql://localhost:3306/tachedb";
        String utilisateur = "root";
        String motDePasse = "";

        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
             Statement statement = connexion.createStatement()) {

            String sqlDelete = "TRUNCATE TABLE TACHE";
            statement.executeUpdate(sqlDelete);

            String sqlResetAutoIncrement = "ALTER TABLE TACHE AUTO_INCREMENT = 1";
            statement.executeUpdate(sqlResetAutoIncrement);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String ResultImportJson() throws IOException {
        List<Tache> taches = tacheService.findAll();
        JSONArray tachesJsonArray = new JSONArray();
        for (Tache tache : taches) {
            JSONObject projetJson = new JSONObject();
            projetJson.put("id", tache.getId());
            projetJson.put("nom", tache.getNom());
            projetJson.put("description", tache.getDescription());
            projetJson.put("statut", tache.getStatut());
            projetJson.put("priorite", tache.getPriority());
            tachesJsonArray.put(projetJson);
        }

        String jsonString = tachesJsonArray.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Object jsonObject = objectMapper.readValue(jsonString, Object.class);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        String formattedJson = objectWriter.writeValueAsString(jsonObject);

        return formattedJson;
    }


    public String ResultImportTxt() {
        String text1 = "";
        StringBuilder stringBuilder = new StringBuilder(text1);
        for(Tache tache: tacheService.findAll()){
            String text2 = tache.getId()+";"+tache.getNom()+";"+tache.getDescription()+";"+tache.getStatut().name()+";"+tache.getPriority().name();
            stringBuilder.append(text2+"\n");
        }
        String content = stringBuilder.toString();
        return new String(content);
    }

}
