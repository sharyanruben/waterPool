package impl;

import exception.WaterPoolOutOfIndexException;

public class WaterPool {
    public static void main(String[] args) {
        WaterPool pool = new WaterPool();
        int [] landscape = {9};
        System.out.println(pool.calculateWaterAmount(landscape));
    }

    public long calculateWaterAmount(int[] landscape){
        if (landscape.length > 32000){
            throw new WaterPoolOutOfIndexException("the length of the landscape must not be more than 32000 ");
        }
        return calculateWaterAmount(landscape,0, landscape.length - 1);
    }

    private int calculateWaterAmount(int[] height, int startIndex, int endIndex){

        int firstHighestIndex = findTheHighestFirstIndex(height, startIndex, endIndex);
        int lastHighestIndex = findTheHighestLastIndex(height, startIndex, endIndex);
        if(startIndex >= endIndex){
            return 0;
        }
        if((startIndex == firstHighestIndex || startIndex == lastHighestIndex) && (endIndex == firstHighestIndex || endIndex == lastHighestIndex)){
            int small = Math.max(height[startIndex], height[endIndex]);
            int result = (endIndex-startIndex - 1) * small;
            for (int i = startIndex + 1; i < endIndex; i++) {
                result -= height[i];
            }
            return result;
        }
        if(firstHighestIndex == lastHighestIndex){
            height[firstHighestIndex]--;
            int first = calculateWaterAmount(height, startIndex, firstHighestIndex );
            int second = calculateWaterAmount(height, lastHighestIndex, endIndex);
            height[firstHighestIndex]++;

            return first + second;

        } else {
            int first = calculateWaterAmount(height, startIndex, firstHighestIndex);
            int second = calculateWaterAmount(height, firstHighestIndex, lastHighestIndex);
            int third = calculateWaterAmount(height, lastHighestIndex, endIndex);
            return first + second + third;
        }

    }

    private int findTheHighestFirstIndex(int[] height, int startIndex, int endIndex){
        int firstHighestIndex = startIndex;
        int max = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            if(max < height[i]){
                max = height[i];
                firstHighestIndex = i;
            }
        }
        return firstHighestIndex;
    }


    private int findTheHighestLastIndex (int[] height, int startIndex, int endIndex){
        int lastHighestIndex = endIndex;
        int max = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            if(max <= height[i]){
                max = height[i];
                lastHighestIndex = i;
            }
        }
        return lastHighestIndex;
    }
}
