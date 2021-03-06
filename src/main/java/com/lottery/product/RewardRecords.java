package com.lottery.product;

import com.common.utils.MyFileWriter;

/**
 * Created by Andy-Super on 2019/2/12.
 * 记录中奖次数
 */
public class RewardRecords {
//    private int rollTimes = 0;

    private Integer[] awardTarget;
    private int[] no1,no2,no3;
    private MyFileWriter fw1,fw2,fw3;
    private String fileName = "";
    private String filePath = "";
    public RewardRecords(){
    }

    private void initStorage(){
        no1 = new int[2];
        no2 = new int[2];
        no3 = new int[2];
    }

    private void initWriter(){
        fw1 = new MyFileWriter(filePath,fileName + "_no1.log");
        fw2 = new MyFileWriter(filePath,fileName + "_no2.log");
        fw3 = new MyFileWriter(filePath,fileName + "_no3.log");
    }

    public void defineAwardTarget(Integer[] designated){
        awardTarget = designated;
        initStorage();
    }

    public void openInputStream(String _filePath){
        fileName = "";
        for (Integer num:awardTarget) {
            fileName += num + ",";
        }
        filePath = _filePath;
        fileName = fileName.substring(0,fileName.length() - 1);
        initWriter();
    }

    //14,19,23,27,34,#06,#12
    private void write(int[] rollTimesStorage, MyFileWriter writer, int curLoop){
        if(rollTimesStorage[0] == 0){
            rollTimesStorage[0] = curLoop;
            writer.writeLine("sum:" + curLoop + ",dvd:" + 0);
        }else{
            rollTimesStorage[1] = curLoop;
            int prev = rollTimesStorage[0];
            int latest = rollTimesStorage[1];
            writer.writeLine("sum:" + curLoop + ",dvd:" + (latest - prev));

            // 用完后替换
            rollTimesStorage[0] = rollTimesStorage[1];
            rollTimesStorage[1] = 0;
        }
    }

    private void rollTimeGrowUp(){
//        rollTimes ++;
    }

    public void end(){
        fw1.end();
        fw2.end();
        fw3.end();
        reset();
    }

    private void reset(){
//        rollTimes = 0;
        fileName = "";
        filePath = "";
    }

    public void record(Integer[] rollTerm,int curLoop){
//        rollTimeGrowUp();
        matchTarget(rollTerm,curLoop);
    }

    private int matchTarget(Integer[] rollTerm,int curLoop){
        int frontMatchedTimes = matchFront(rollTerm);
        int behindMatchedTimes = matchBehind(rollTerm);
        if(frontMatchedTimes == 5 && behindMatchedTimes == 2){
            write(no1,fw1,curLoop);
        }
        if(frontMatchedTimes == 5 && behindMatchedTimes == 1){
            write(no2,fw2,curLoop);
        }
        if(frontMatchedTimes == 5 && behindMatchedTimes == 0
            || frontMatchedTimes == 4 && behindMatchedTimes == 2){
            write(no3,fw3,curLoop);
        }
//        if(frontMatchedTimes == 4 && behindMatchedTimes == 1
//            || frontMatchedTimes == 3 && behindMatchedTimes == 2){
//            write(no4,fw4);
//        }
        return 0;
    }

    private int matchFront(Integer[] rollTerm){
        int matchTimes = 0;
        for(int i = 0; i< 5;i++){
            if(rollTerm[i].equals(awardTarget[i])){
                matchTimes ++;
            }
        }
        return matchTimes;
    }

    private int matchBehind(Integer[] rollTerm){
        int matchTimes = 0;
        for(int i = 4; i < 6;i++){

            if(rollTerm[i].equals(awardTarget[i])){
                matchTimes ++;
            }

        }
        return matchTimes;
    }

}
