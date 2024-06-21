package hw3;

import java.io.BufferedReader;
import java.io.FileReader;

import hw3.Score;

public class MyScoreList {
    static Score head;

    public MyScoreList() {
        head = null;
    }

    public static boolean isEmpty() {
        boolean res = (head == null);
        if (res) {
            System.out.println("List is empty");
        }
        return res;
    }

    // read data
    public static void readData(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {    // 逐行讀取檔案內容
                // 空行跳過
                if (line.trim().isEmpty())
                    continue;
                String[] tokenLine = line.split("  ");
                // System.out.println(tokenLine[0] + ' ' + Integer.parseInt(tokenLine[1]) + ' ' + Integer.parseInt(tokenLine[2]));
                addData(tokenLine[0], Integer.parseInt(tokenLine[1]), Integer.parseInt(tokenLine[2]), false);
            }
            // 釋放記憶體
            br.close();
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    // add data
    public static void addData(String name, int credit, int score, boolean isExist) {
        if (isExist) {
            updateData(name, credit, score);
        }
        else {
            Score newData = new Score(name, credit, score);
            if (head == null) {
                head = newData;
            }
            else {
                Score current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newData;
            }
        }
    }

    // print all
    public static void printAll() {
        if (isEmpty()) {
            return;
        }

        Score current = head;
        while (current != null) {
            System.out.println(current.name + ' ' + current.credit + ' ' + current.avg);
            current = current.next;
        }
    }

    // search data
    public static boolean searchData(String targetName) {
        if (isEmpty()) {
            return false;
        }
        else {
            Score current = head;
            while (current !=null && !current.name.equals(targetName)) {
                current = current.next;
            }
            if (current == null) {
                System.out.println("沒有找到對應資料");
                return false;
            }
            else {
                System.out.println(current.name + ' ' + current.credit + ' ' + current.avg);
                return true;
            }
        }
    }

    public static void updateData(String name, int credit, int score) {
        Score current = head;
        while (current != null && !current.name.equals(name)) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("沒有找到對應資料");
            return;
        }
        else {
            score = (int)(Math.round((current.credit * current.avg + credit * score) / (current.credit + credit)));
            current.credit = credit;
            current.avg = score;
        }
    }

    public static void deleteData(String targetName) {
        if (isEmpty()) {
            return;
        }

        Score current = head;
        Score prev = null;

        // 找目標
        while (current != null && !current.name.equals(targetName)) {
            prev = current;
            current = current.next;
        }
        // 沒找到
        if (current == null) {
            System.out.println("沒有找到對應資料");
            return;
        }
        // 找到頭
        if (prev == null) {
            head = current.next;
        }
        else {
            prev.next = current.next;
        }
        // 刪除資料
        current = null;
    }

    public static void main(String[] args) {
        String fileName = "C:\\Users\\hank1\\OneDrive\\桌面\\java-test\\src\\hw3\\scoreData.txt";
        readData(fileName);

        while (true) {
            System.out.println();
            System.out.println("=================================================");
            System.out.println("1. 查詢\n2. 新增\n3. 刪除\n4. 列出全部學生成績\n5. 結束");
            System.out.println("請依所欲使用的功能輸入對應的數字1、2、3、4或5: ");

            int choice = Integer.parseInt(System.console().readLine());
            String name;

            switch (choice) {
                // 查詢
                case 1:
                    System.out.print("輸入學生姓名: ");
                    name = System.console().readLine();
                    searchData(name);
                    break;
                // 新增
                case 2:
                    System.out.print("輸入學生姓名: ");
                    name = System.console().readLine();

                    boolean isExist = searchData(name);
                    if (isExist) {
                        System.out.println("學生存在，更新資料");
                    }

                    System.out.print("輸入學分: ");
                    int credits = Integer.parseInt(System.console().readLine());
                    System.out.print("輸入成績: ");
                    int avg = Integer.parseInt(System.console().readLine());

                    addData(name, credits, avg, isExist);

                    break;
                // 刪除
                case 3:
                    System.out.print("輸入學生姓名: ");
                    name = System.console().readLine();
                    deleteData(name);
                    break;
                // 列出全部學生成績
                case 4:
                    printAll();
                    break;
                // 結束
                case 5:
                    System.out.println("退出系統");
                    return;
                default:
                    System.out.println("無效選擇。請輸入 1 到 5 之間的數字。");
            }
        }
    }
}
