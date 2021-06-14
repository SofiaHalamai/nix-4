package ua.com.alevel.jdbc.util;

import com.opencsv.CSVWriter;
import ua.com.alevel.jdbc.entity.AccountStatement;
import ua.com.alevel.jdbc.entity.TableCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static ua.com.alevel.jdbc.service.impl.JDBCCommonServiceImpl.logger;

public class StatementCreator {

    public TableCSV creatingContentForFile (List<AccountStatement> statementList, Integer totalIncome, Integer balance){
        String [] headers = {"Account ID", "DATE", "AMOUNT", "CATEGORY"};
        String [][] cells = new String [statementList.size()+headers.length-1][headers.length];
        for(int i=0; i<statementList.size(); i++){
            cells[i][0] = statementList.get(i).getAccountId().toString();
            cells[i][1] = statementList.get(i).getDate();
            cells[i][2] = statementList.get(i).getAmount().toString();
            cells[i][3] = statementList.get(i).getCategories().toString();
        }
        for(int i=0; i<headers.length; i++)
            cells[statementList.size()][i] = "     ";

        cells[statementList.size()+1][0] = "TOTAL income";
        cells[statementList.size()+1][1] = "BALANCE";
        cells[statementList.size()+1][2] = "     ";
        cells[statementList.size()+1][3] = "     ";
        cells[statementList.size()+2][0] = "      "+ totalIncome;
        cells[statementList.size()+2][1] = String.valueOf(balance);
        cells[statementList.size()+2][2] = "     ";
        cells[statementList.size()+2][3] = "     ";
        logger.info("Result statement account");
        for(int i=0; i<statementList.size()+3; i++){
            for(int j=0; j<headers.length; j++){
                logger.info("  "+cells[i][j]);
            }
            System.out.println();
        }

        return new TableCSV(headers, cells);
    }

    public void writeIntoFile(TableCSV tableCSV, String path) {
        String [] headers = tableCSV.getHeaders();
        String[][] cells = tableCSV.getCells();

        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeAll(Collections.singletonList(headers));
            for (String[] cell : cells) {
                writer.writeNext(cell);
            }
        } catch (IOException e) {
            logger.error ("Invalid file path");
        }
    }
}
