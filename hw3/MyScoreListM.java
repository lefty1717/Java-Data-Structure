package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MyScoreListM {
    private List<ScoreM> scores;
    private Scanner scanner;

    public MyScoreListM() {
        scores = new LinkedList<>();
        scanner = new Scanner(System.in);
    }

    public void queryByName() {
        System.out.print("輸入要查詢的學生姓名: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (ScoreM score : scores) {
            if (score.getName().equalsIgnoreCase(name)) {
                System.out.println(score);
                System.out.println("index: " + scores.indexOf(score));
                scores.remove(score);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("資料中沒有找到該姓名的學生： " + name);
        }
    }

    public void loadData() {
        File file = new File("C:\\Users\\hank1\\OneDrive\\桌面\\java-test\\src\\hw3\\scoreData.txt"); // 指定文件路徑
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split("\\s+"); // 以空格分隔
                if (data.length == 3) {
                    String name = data[0];
                    int credit = Integer.parseInt(data[1]);
                    int score = Integer.parseInt(data[2]);
                    scores.add(new ScoreM(name, credit, score));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("解析編號錯誤: " + e.getMessage());
        }
    }

    public void AddScore() {
        System.out.print("輸入學生姓名: ");
        String name = scanner.nextLine();
        for (ScoreM score : scores) {
            if (score.getName().equalsIgnoreCase(name)) {
                System.out.print("輸入學分和成績: ");
                int credits = scanner.nextInt();
                int scoreValue = scanner.nextInt();
                score.updateScore(credits, scoreValue);
                scanner.nextLine(); // 清理輸入緩衝區
                return;
            }
        }
        System.out.print("輸入新學生的學分和成績: ");
        int credits = scanner.nextInt();
        int scoreValue = scanner.nextInt();
        scores.add(new ScoreM(name, credits, scoreValue));
        scanner.nextLine(); // 清理輸入緩衝區
    }

    public void deleteScore() {
        System.out.print("輸入學生姓名: ");
        String name = scanner.nextLine();
        boolean removed = scores.removeIf(s -> s.getName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println("該名學生已被成功刪除");
        } else {
            System.out.println("找不到該名學生");
        }
    }

    public void listScores() {
        if (scores.isEmpty()) {
            System.out.println("沒有可用成績");
        } else {
            for (ScoreM score : scores) {
                System.out.println(score);
            }
        }
    }

    public static void main(String[] args) {
        MyScoreListM myScoreList = new MyScoreListM();
        myScoreList.loadData();

        while (true) {
            System.out.println("=================================================");
            System.out.println("1. 查詢\n2. 新增\n3. 刪除\n4. 列出全部學生成績\n5. 結束");
            System.out.println("請依所欲使用的功能輸入對應的數字1、2、3、4或5: ");
            
            int choice = myScoreList.scanner.nextInt();
            myScoreList.scanner.nextLine(); // 清理輸入緩衝區

            switch (choice) {
                case 1:
                    myScoreList.queryByName();
                    break;
                case 2:
                    myScoreList.AddScore();
                    break;
                case 3:
                    myScoreList.deleteScore();
                    break;
                case 4:
                    myScoreList.listScores();
                    break;
                case 5:
                    System.out.println("退出系統");
                    return;
                default:
                    System.out.println("無效選擇。請輸入 1 到 5 之間的數字。");
            }
        }
    }
}
