package com.lottery.experiment;

import com.lottery.product.Roll;
import com.lottery.utils.AwardLevel;
import com.lottery.utils.FileWriter;
import com.lottery.utils.JSArray;
import com.lottery.utils.JSArrayCallback;

/**
 * Created by Andy-Super on 2019/2/19.
 *
 */
public class RollFromAvg {

    private Integer[] simp;

    public RollFromAvg(Integer[] _simp){
        simp = _simp;
    }

    public void run(){

        String filePath = "src/main/java/com/lottery/db/pro/experiment/";
        FileWriter fw = new FileWriter(filePath + "pro.log");
        JSArray terms = new JSArray(String.class);

        Roll r = new Roll();
        for(int i = 0;i<=1000000;i++){
            JSArray aTerm = r.productATerm();

//            if(i>=53310 && i<= 53327 || i>=222214 && i<=222260){
//                terms.push(aTerm.join("-"));
//
//                fw.writeLine(aTerm.join(","));
//            }

            terms.push(aTerm.join("-"));
        }
        fw.end();


        terms.forEach(new JSArrayCallback() {
            @Override
            public void entries(Object item, Integer index) {
                AwardLevel al = new AwardLevel(simp);
                String[] targetStr = item.toString().split("-");
                JSArray targetArr = new JSArray(Integer.class);
                for (String str:targetStr) {
                    targetArr.push(Integer.valueOf(str));
                }
                if(al.match(targetArr) > 0){
                    System.out.println("中奖等级：" + al.match(targetArr));
                }
            }
        });

    }
}
