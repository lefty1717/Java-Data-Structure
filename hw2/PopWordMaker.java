package hw2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import hw2.MyStack;

public class PopWordMaker {
    public static void main(String[] args) {
        // 1: 印出學號姓名
        System.out.println("班級: 資管碩一");
        System.out.println("學號: 412346098");
        System.out.println("姓名: 徐爾漢");
        
        // 讀取檔案
        String fileName = "C:\\Users\\hank1\\OneDrive\\桌面\\java-test\\src\\hw2\\data.txt";
        List<String> tokenList = readData(fileName);

        for (String token : tokenList) {
            switch (token) {
                case "%":
                    MyStack.pop();
                    break;
                case "*":
                    System.out.println();
                    break;
                default:
                    MyStack.push(token);
                    break;
            }
        }
        for (String token : hw2.MyStack.stackArray) {
            if (token == null)
                break;
            else
                System.out.print(token);
        }
    }
    
    public static List<String> readData(String path) {
        List<String> tokenList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {    // 逐行讀取檔案內容
                // 空行跳過
                if (line.trim().isEmpty())
                    continue;
                String[] tokenLine = line.split("");
                for (String token : tokenLine)
                    tokenList.add(token.trim());
            }
            // 釋放記憶體
            br.close();
            return tokenList;
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }
}
