package hw1;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // 1: 印出學號姓名
            System.out.println("班級: 資管碩一");
            System.out.println("學號: 412346098");
            System.out.println("姓名: 徐爾漢");
            
            // 讀取檔案
            String fileName = "C:\\Users\\hank1\\OneDrive\\桌面\\java-test\\src\\numberData.txt";
            int[] numArray = readData(fileName);

            // 2: 計算總和
            int sum = sum(numArray);
            System.out.println("總訪客數量: "+ sum);

            //3: 計算平均
            int avg = sum / numArray.length;
            System.out.println("平均訪客數量: " + avg);

            //4: 找出出現最多和最少的數字及次數和位置
            Result result = findMaxMin(numArray);
            System.out.println("最高的訪客數量: " + result.max);
            System.out.println("最高的訪客次數: " + result.maxCount);
            System.out.println("最高的訪客日: " + result.maxIndex);
            System.out.println("最低的訪客數量: " + result.min);
            System.out.println("最低的訪客次數: " + result.minCount);
            System.out.println("出現次數最高的訪客日: " + result.minIndex);
        
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    // 讀取檔案
    public static int[] readData(String fileName) throws Exception {
        List<Integer> numList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {    // 逐行讀取檔案內容
                // 空行跳過
                if (line.trim().isEmpty())
                    continue;
                String[] nums = line.split(" ");
                for (String num : nums)
                {
                    try {
                        System.out.println(num);
                        numList.add(Integer.parseInt(num)); // 將字串轉換為int後加入List
                    }
                    catch (Exception e) {

                    }
                }
            }
            // 將list轉為array
            int[] numArray = numList.stream().mapToInt(i -> i).toArray();
            // 釋放記憶體
            br.close();
            return numArray;
        }
        catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    // 1: 計算總和
    public static int sum(int[] numArray) {
        int sum = 0;
        for (int i = 0; i < numArray.length; i++) {
            sum += numArray[i];
        }
        return sum;
    }

    //2、3: 找出最大數和最小數及其次數和位置
    public static Result findMaxMin(int[] numArray) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int maxCount = 0;
    int minCount = 0;
    List<Integer> maxIndices = new ArrayList<>();
    List<Integer> minIndices = new ArrayList<>();

    for (int i = 0; i < numArray.length; i++) {
        if (numArray[i] > max) {
            max = numArray[i];
            maxCount = 1;
            maxIndices.clear();
            maxIndices.add(i + 1);
        } else if (numArray[i] == max) {
            maxCount++;
            maxIndices.add(i + 1);
        }

        if (numArray[i] < min) {
            min = numArray[i];
            minCount = 1;
            minIndices.clear();
            minIndices.add(i + 1);
        } else if (numArray[i] == min) {
            minCount++;
            minIndices.add(i + 1);
        }
    }

    return new Result(max, maxCount, maxIndices, min, minCount, minIndices);
}
    // 封裝結果
    static class Result {
        int max;
        int maxCount;
        List<Integer> maxIndex;
        int min;
        int minCount;
        List<Integer> minIndex;
        
        Result(int max, int maxCount, List<Integer> maxIndex, int min, int minCount, List<Integer> minIndex) {
            this.max = max;
            this.maxCount = maxCount;
            this.maxIndex = maxIndex;
            this.min = min;
            this.minCount = minCount;
            this.minIndex = minIndex;
        }
    }
}
